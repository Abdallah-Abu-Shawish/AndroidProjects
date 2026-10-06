package com.example.roomdatabasetest;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.roomdatabasetest.databinding.UserItemBinding;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<User> users;
    private UserItemBinding binding;

    private UserScoreAdapter adapter;
    private AppRoomDataBase dataBase;

    boolean flag = true;
    public UserAdapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;
        dataBase = AppRoomDataBase.getDatabase(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = UserItemBinding.inflate(LayoutInflater.from(context), parent, false);
        return new UserHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        UserHolder myHolder = (UserHolder) holder;
        myHolder.binding.tvName.setText(users.get(position).getName());
        myHolder.binding.tvUsername.setText(users.get(position).getUsername());

        List<UserScore> userScores = dataBase.userScoreDao().getUserScore(users.get(position).getId());
        long userInId = dataBase.userInDao().getAllUsersIn().getUserIn_id();

        adapter = new UserScoreAdapter(context,userScores,userInId);

        myHolder.binding.rvUserScores.setLayoutManager(new LinearLayoutManager(context));
        myHolder.binding.rvUserScores.setAdapter(adapter);
        if (users.get(position).getId() == userInId){
            myHolder.binding.cardUserItem.setCardBackgroundColor(Color.parseColor("#FFF196"));
        }
        myHolder.binding.dropdownScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag){
                    myHolder.binding.rvUserScores.setVisibility(View.VISIBLE);
                    myHolder.binding.dropdownScores.setImageResource(R.drawable.baseline_arrow_circle_up_24);
                    flag = false;
                }else {
                    myHolder.binding.rvUserScores.setVisibility(View.GONE);
                    myHolder.binding.dropdownScores.setImageResource(R.drawable.baseline_arrow_circle_down_24);
                    flag = true;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class UserHolder extends RecyclerView.ViewHolder {
        UserItemBinding binding;
        public UserHolder(UserItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}

