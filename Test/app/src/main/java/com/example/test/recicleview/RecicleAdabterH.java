package com.example.test.recicleview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.test.databinding.CardView2Binding;

import java.util.ArrayList;

public class RecicleAdabterH extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    @NonNull


    private Context context;
    private ArrayList<Game2> games;
    private CardView2Binding binding;
    private ClickOffer clickOffer;

    public RecicleAdabterH(@NonNull Context context, ArrayList<Game2> games ,ClickOffer clickOffer ) {
        this.context = context;
        this.games = games;
        this.clickOffer = clickOffer;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       binding = CardView2Binding.inflate(LayoutInflater.from(context),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
       ViewHolder viewHolder = (ViewHolder) holder;
       viewHolder.binding.offerDiscount.setText(games.get(position).getName());
       viewHolder.binding.tvWatchWord.setText(games.get(position).getRate());
       viewHolder.binding.tvWatchType.setText(games.get(position).getRate2());
       viewHolder.binding.offerImg.setImageResource(games.get(position).getImage());
       viewHolder.binding.tvNewBrandWord.setText(games.get(position).getNewBrand());
       viewHolder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               clickOffer.clickOnOffer(position);
           }
       });

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



    public interface ClickOffer{

        void clickOnOffer(int position);

    }


}
