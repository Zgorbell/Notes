package com.example.apalontest.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceActivity;
import android.widget.Toast;

/**
 * Created by Евгений on 30.01.2018.
 */

public class DB {

    private DatabaseHelper helper;
    private SQLiteDatabase db;
    private Context context;

    public DB(Context context){
        this.context = context;
    }

    public void open(){
        helper = new DatabaseHelper(context);
        db = helper.getReadableDatabase();
    }

    public Cursor readAllData() {
        return db.query(DatabaseHelper.TABLE,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    private ContentValues setValues(String tittle, String message){
        Toast.makeText(context, "Tut",Toast.LENGTH_LONG).show();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_TITLE, tittle);
        values.put(DatabaseHelper.COLUMN_MESSAGE, message);
        values.put(DatabaseHelper.COLUMN_DATE, System.currentTimeMillis());
        return values;
    }
    public void insert(String tittle, String message) {

        db.insert(DatabaseHelper.TABLE, null, setValues(tittle, message));
    }

    public void update(String tittle, String message, String idArg){
        String selection = DatabaseHelper.COLUMN_ID + " = ?";
        db.update(DatabaseHelper.TABLE, setValues(tittle, message),selection , new String[]{idArg});
    }

    public void delete(String id){
        String query = "DELETE FROM " + DatabaseHelper.TABLE + " WHERE ID = '" +
                id + "'";
        db.execSQL(query);
    }
}
