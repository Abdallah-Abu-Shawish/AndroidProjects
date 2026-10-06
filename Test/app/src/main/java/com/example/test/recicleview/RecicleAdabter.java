package com.example.test.recicleview;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.test.MainActivityForHome;
import com.example.test.PdoductDetailsActivity;
import com.example.test.databinding.CardViewBinding;

import java.util.ArrayList;

public class RecicleAdabter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    @NonNull


    private Context context;
    private ArrayList<Game> games;
    private CardViewBinding binding;
    private ClickItem clickItem;


    public RecicleAdabter(@NonNull Context context, ArrayList<Game> games,ClickItem clickItem) {
        this.context = context;
        this.games = games;
        this.clickItem = clickItem;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       binding = CardViewBinding.inflate(LayoutInflater.from(context),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
       ViewHolder viewHolder = (ViewHolder) holder;
       viewHolder.binding.tvName.setText(games.get(position).getName());
       viewHolder.binding.tvPrice.setText(games.get(position).getRate()+"$");
       viewHolder.binding.tvSpecialName.setText(games.get(position).getSpecialName());
       viewHolder.binding.samsungWatch.setImageResource(games.get(position).getImage());
       viewHolder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               clickItem.clickOnItem(position);
           }
       });

    }

    @Override
    public int getItemCount() {
        return games.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CardViewBinding binding;

        public ViewHolder(@NonNull CardViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }



    public interface ClickItem{

        void clickOnItem(int position);

    }


}
