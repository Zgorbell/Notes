package com.example.apalontest.cards;

import java.text.SimpleDateFormat;
import java.util.Date;



public class Card {
    public final static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy HH:mm ");
    private final int id;
    private final String title;
    private final String message;
    private final Date date;

    public Card(int id, String title, String message, long date){
        this.id = id;
        this.title = title;
        this.message = message;
        this.date = new Date(date);
    }

    public String getTitle(){
        return title;
    }

    public String getMessage(){
        return message;
    }

    public Date getDate(){
        return date;
    }

    public String dateToString(){
        return sdf.format(date);
    }

    public int getId() {return id;}
}
