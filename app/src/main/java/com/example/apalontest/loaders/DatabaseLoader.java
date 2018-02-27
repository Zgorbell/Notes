package com.example.apalontest.loaders;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.content.AsyncTaskLoader;
import android.widget.Toast;

import com.example.apalontest.db.DB;
import com.example.apalontest.db.DatabaseHelper;



public class DatabaseLoader extends AsyncTaskLoader<Cursor> {

    public DatabaseLoader(Context context){
        super(context);
    }

    @Override
    public void onStartLoading(){
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public Cursor loadInBackground(){
        DB db = new DB(getContext());
        db.open();
        return db.readAllData();
    }


}
