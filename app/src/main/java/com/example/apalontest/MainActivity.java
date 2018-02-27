package com.example.apalontest;


import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.apalontest.cards.CardFragment;
import com.example.apalontest.note.NoteEditorFragment;
import com.example.apalontest.note.NoteViewerFragment;

public class MainActivity extends AppCompatActivity {

    public final static String cardsTag = "cards";
    public final static String noteEditorTag = "editNote";
    public final static String noteViewerTag = "viewNote";
    private Fragment mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mainFragment = new CardFragment();
        changeFragment(mainFragment,cardsTag);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabCard);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(NoteEditorFragment.newInstance(null, null, null), noteEditorTag);
            }
        });

    }
    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStackImmediate();
        } else {
            finish();
        }

    }

    public void changeFragment(Fragment fragment, String tag){
        FragmentManager manager = getSupportFragmentManager();
        Fragment exist = manager.findFragmentByTag(tag);
        if( exist == null){
            exist = fragment;
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.draw_container, exist, tag)
                .addToBackStack(null).commit();
    }

    public void onCardClick(View view){
        String tittle = ((TextView)view.findViewById(R.id.card_name)).getText().toString();
        String message = ((TextView)view.findViewById(R.id.card_text)).getText().toString();
        String id = ((TextView)view.findViewById(R.id.card_id)).getText().toString();
        String date = ((TextView)view.findViewById(R.id.card_date)).getText().toString();

        Fragment fragment = NoteViewerFragment.newInstance(tittle, message, id);
        changeFragment(fragment, noteViewerTag);
    }

}
