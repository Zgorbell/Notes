package com.example.apalontest.note;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apalontest.MainActivity;
import com.example.apalontest.R;
import com.example.apalontest.db.DB;
import com.example.apalontest.db.DatabaseHelper;



public class NoteViewerFragment extends Fragment {
    private TextView tittle;
    private TextView message;
    private TextView id;
    public static NoteViewerFragment newInstance(String tittle, String message, String id) {

        Bundle args = new Bundle();
        args.putString(DatabaseHelper.COLUMN_TITLE, tittle);
        args.putString(DatabaseHelper.COLUMN_MESSAGE, message);
        args.putString(DatabaseHelper.COLUMN_ID, id);

        NoteViewerFragment fragment = new NoteViewerFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        AppBarLayout layout = (AppBarLayout) getActivity().findViewById(R.id.appbar);
        layout.setExpanded(false,true);

        View view = inflater.inflate(R.layout.note_view_layout, container, false );
        getActivity().findViewById(R.id.fabCard).setVisibility(View.GONE);

        tittle = view.findViewById(R.id.textView_tittle);
        message = view.findViewById(R.id.textView_message);
        id = view.findViewById(R.id.view_note_id);

        tittle.setText(getArguments().getString(DatabaseHelper.COLUMN_TITLE));
        message.setText(getArguments().getString(DatabaseHelper.COLUMN_MESSAGE));
        id.setText(getArguments().getString(DatabaseHelper.COLUMN_ID));

        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        setHasOptionsMenu(true);
        return view;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.note_view_menu_bar,menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.action_edit:
                Fragment fragment = new NoteEditorFragment();

                fragment.setArguments(this.getArguments());
                changeFragment(fragment, MainActivity.noteEditorTag);
                return true;
            case R.id.action_remove:
                deleteFromDb();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void deleteFromDb() {

        new AlertDialog.Builder(getActivity())
                .setTitle("You wanna delete something!")
                .setMessage("Are you sure?")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        delete();
                        getActivity().onBackPressed();
                    }
                })
                .setNegativeButton("Maybe no?", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();


    }
    public void delete(){
        DB db = new DB(getContext());
        db.open();
        db.delete(id.getText().toString());
    }

    public void changeFragment(Fragment fragment, String tag) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        Fragment exist = manager.findFragmentByTag(tag);
        if (exist == null) {
            exist = fragment;
        }

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.draw_container, exist, tag).addToBackStack(null).commit();
    }
}
