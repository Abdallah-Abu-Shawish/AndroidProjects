package com.example.mydatabase;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydatabase.databinding.CardViewBinding;


import java.util.ArrayList;

public class RecicleAdabter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    @NonNull


    private Context context;
    private ArrayList<User> users;
    private CardViewBinding binding;
    private ClickItem clickItem;


    public RecicleAdabter(@NonNull Context context, ArrayList<User> users, ClickItem clickItem) {
        this.context = context;
        this.users = users;
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
       viewHolder.binding.tvName.setText(users.get(position).getName());
       viewHolder.binding.tvEmail.setText(users.get(position).getEmail());
       viewHolder.binding.tvPhone.setText(users.get(position).getPhone());
       viewHolder.binding.tvPassword.setText(users.get(position).getPassword());
        viewHolder.binding.tvCountry.setText(users.get(position).getCountry());
        viewHolder.binding.tvGender.setText(users.get(position).getGender());
       viewHolder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               clickItem.clickOnItem(position);
           }
       });

    }

    @Override
    public int getItemCount() {
        return users.size();
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
