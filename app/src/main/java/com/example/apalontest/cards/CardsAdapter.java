package com.example.apalontest.cards;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.apalontest.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;



public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.CardViewHolder> {

    private final List<Card> cards;

    public CardsAdapter(){
        cards = new ArrayList<>();
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewTypr){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_item, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position){
        Card card = cards.get(position);
        holder.id.setText(String.valueOf(card.getId()));
        holder.title.setText(card.getTitle());
        holder.message.setText(card.getMessage());
        holder.date.setText(card.dateToString());
    }

    @Override
    public int getItemCount(){
        return cards.size();
    }

    public void rotateCards(){
        List<Card> newList = new ArrayList<>();
        for(int i = 0; i < cards.size(); i++){
            newList.add(cards.get( cards.size() - i - 1));
        }
        cards.clear();
        addCards(newList);
    }

    public void deleteAll(){
        cards.clear();
    }


    public void addCards(List<Card> items){
        cards.addAll(items);
    }


    static class CardViewHolder extends RecyclerView.ViewHolder{

        TextView id;
        TextView title;
        TextView date;
        TextView message;

        CardViewHolder(View itemView){
            super(itemView);
            id = (TextView)itemView.findViewById(R.id.card_id);
            title = (TextView)itemView.findViewById(R.id.card_name);
            date = (TextView)itemView.findViewById(R.id.card_date);
            message = (TextView)itemView.findViewById(R.id.card_text);
        }


    }
}
