package com.example.apalontest.db;

import android.database.Cursor;

import com.example.apalontest.cards.Card;

import java.util.ArrayList;
import java.util.List;



public class CursorAdapter {

    public static List cursorToList(Cursor cursor){
        List<Card> list = new ArrayList<>();
        String tittle;
        long date;
        String message;
        int id;
        for(cursor.moveToLast(); !cursor.isBeforeFirst(); cursor.moveToPrevious()){
            id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID));
            tittle = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TITLE));
            date = cursor.getLong(cursor.getColumnIndex(DatabaseHelper.COLUMN_DATE));
            message = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_MESSAGE));
            list.add(new Card(id, tittle, message, date));
        }
        return  list;
    }
}
