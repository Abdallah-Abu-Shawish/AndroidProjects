package com.example.recicleview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recicleview.databinding.CardView2Binding;
import com.example.recicleview.databinding.CircleImgBinding;

import java.util.ArrayList;

public class RecicleAdabterForAvatar extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    @NonNull


    private Context context;
    private ArrayList<Avatar> avatars;
    private CircleImgBinding binding;


    public RecicleAdabterForAvatar(@NonNull Context context, ArrayList<Avatar> avatars) {
        this.context = context;
        this.avatars = avatars;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       binding = CircleImgBinding.inflate(LayoutInflater.from(context),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
       ViewHolder viewHolder = (ViewHolder) holder;

       viewHolder.binding.personImg.setImageResource(avatars.get(position).getAvat());

    }

    @Override
    public int getItemCount() {
        return avatars.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CircleImgBinding binding;

        public ViewHolder(@NonNull CircleImgBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }




}
