package com.example.recicleview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recicleview.databinding.CardView2Binding;


import java.util.ArrayList;

public class RecicleAdabterH extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    @NonNull


    private Context context;
    private ArrayList<Game2> games;
    private CardView2Binding binding;


    public RecicleAdabterH(@NonNull Context context, ArrayList<Game2> games) {
        this.context = context;
        this.games = games;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       binding = CardView2Binding.inflate(LayoutInflater.from(context),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
       ViewHolder viewHolder = (ViewHolder) holder;

    }

    @Override
    public int getItemCount() {
        return games.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CardView2Binding binding;

        public ViewHolder(@NonNull CardView2Binding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }




}
