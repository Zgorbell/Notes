package com.example.apalontest.cards;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.apalontest.R;
import com.example.apalontest.db.CursorAdapter;
import com.example.apalontest.loaders.DatabaseLoader;

import java.util.ArrayList;
import java.util.List;


public class CardFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final int CARD_FRAGMENT_LOADER_ID = 1;

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
    }

    private RecyclerView cardRecycler;
    private CardsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.cards_layout, container, false);

        AppBarLayout layout = (AppBarLayout) getActivity().findViewById(R.id.appbar);
        layout.setExpanded(true, true);

        cardRecycler = (RecyclerView) view.findViewById(R.id.card_recycler);
        cardRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        adapter = new CardsAdapter();
        cardRecycler.setAdapter(adapter);

        getLoaderManager().initLoader(0,null,this);


        getActivity().findViewById(R.id.fabCard).setVisibility(View.VISIBLE);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        setHasOptionsMenu(true);
        return view;
    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu_bar, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sort:
                Card card = new Card(0, "Super", "Puper", 11111111);
                List<Card> list = new ArrayList<Card>();
                list.add(card);
                adapter.rotateCards();
                adapter.notifyDataSetChanged();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle instanceState) {

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new DatabaseLoader(getContext());
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        adapter.deleteAll();
        adapter.addCards(CursorAdapter.cursorToList(cursor));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
    }

}
