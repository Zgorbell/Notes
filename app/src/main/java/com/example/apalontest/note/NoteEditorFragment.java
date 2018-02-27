package com.example.apalontest.note;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apalontest.MainActivity;
import com.example.apalontest.R;
import com.example.apalontest.cards.Card;
import com.example.apalontest.db.DB;
import com.example.apalontest.db.DatabaseHelper;

import java.util.Date;

public class NoteEditorFragment extends Fragment{

    private EditText editTittle;
    private EditText editMessage;
    private TextView id;

    public static NoteEditorFragment newInstance(String tittle, String message, String id) {

        Bundle args = new Bundle();
        args.putString(DatabaseHelper.COLUMN_TITLE, tittle);
        args.putString(DatabaseHelper.COLUMN_MESSAGE, message);
        args.putString(DatabaseHelper.COLUMN_ID, id);

        NoteEditorFragment fragment = new NoteEditorFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceStat) {
        AppBarLayout layout = (AppBarLayout) getActivity().findViewById(R.id.appbar);
        layout.setExpanded(false, true);

        View view = inflater.inflate(R.layout.note_edit_layout, container, false);
        getActivity().findViewById(R.id.fabCard).setVisibility(View.GONE);

        editTittle = view.findViewById(R.id.edit_tittle);
        editMessage = view.findViewById(R.id.edit_message);
        id = view.findViewById(R.id.edit_note_id);


        editTittle.setText(getArguments().getString(DatabaseHelper.COLUMN_TITLE));
        editMessage.setText(getArguments().getString(DatabaseHelper.COLUMN_MESSAGE));
        id.setText(getArguments().getString(DatabaseHelper.COLUMN_ID));


        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        setHasOptionsMenu(true);
        return view;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.note_edit_menu_bar,menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()) {
            case R.id.action_done:
                dispatchTouchEvent();
                insertDb(editTittle.getText().toString(),
                        editMessage.getText().toString());

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void insertDb(String tittle, String message){
        if(!(message == null || message.equals(""))) {
            DB db = new DB(getContext());
            String idArg = getArguments().getString(DatabaseHelper.COLUMN_ID);
            db.open();
            if( idArg == null){
                db.insert(tittle, message);
            }else{
                db.update(tittle, message, idArg);
                getActivity().getSupportFragmentManager()
                        .findFragmentByTag(MainActivity.noteViewerTag)
                        .setArguments(createBundle(tittle, message, idArg));
            }
            getActivity().onBackPressed();
        }else{
            Snackbar.make(getView(),"Please, enter message.",Snackbar.LENGTH_LONG).show();
        }
    }

    public void dispatchTouchEvent() {

        View v = getActivity().getCurrentFocus();
        if (v instanceof EditText) {
            v.clearFocus();
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

        }
    }

    public Bundle createBundle(String tittle, String message, String id){
        Bundle args = new Bundle();
        args.putString(DatabaseHelper.COLUMN_TITLE, tittle);
        args.putString(DatabaseHelper.COLUMN_MESSAGE, message);
        args.putString(DatabaseHelper.COLUMN_ID, id);
        return args;
    }
}
