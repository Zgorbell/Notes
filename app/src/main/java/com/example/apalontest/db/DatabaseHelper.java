package com.example.apalontest.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "apalonNotes.db";
    private static final int SHEMA = 1;
    public static final String TABLE = "notes";

    public static final String COLUMN_ID = "Id";
    public static final String COLUMN_TITLE = "Tittle";
    public static final String COLUMN_DATE = "Date";
    public static final String COLUMN_MESSAGE = "Message";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, SHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(String.format("CREATE TABLE %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s INTEGER, %s TEXT );",
                TABLE, COLUMN_ID, COLUMN_TITLE, COLUMN_DATE, COLUMN_MESSAGE));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersiom){

    }
}
