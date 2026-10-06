package com.example.roomdatabasetest;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdatabasetest.databinding.EmptyStateBinding;
import com.example.roomdatabasetest.databinding.ScoreItemBinding;

import java.util.List;

public class UserScoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<UserScore> usersScores;
    private long userId;
    private ScoreItemBinding binding;
    private EmptyStateBinding binding2;
    private AppRoomDataBase dataBase;

    public UserScoreAdapter(Context context, List<UserScore> usersScores, long userId) {
        this.context = context;
        this.usersScores = usersScores;
        this.userId = userId;
        dataBase = AppRoomDataBase.getDatabase(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (usersScores == null || usersScores.isEmpty()){
            binding2 = EmptyStateBinding.inflate(LayoutInflater.from(context), parent, false);
            return new UserScoreHolder(binding2);
        }else {
            binding = ScoreItemBinding.inflate(LayoutInflater.from(context), parent, false);
            return new UserScoreHolder(binding);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (usersScores != null && !usersScores.isEmpty()){
            long userInId = dataBase.userInDao().getAllUsersIn().getUserIn_id();
            UserScoreHolder myHolder = (UserScoreHolder) holder;
//            if (userId == userInId){
//                myHolder.binding.getRoot().setCardBackgroundColor(Color.parseColor("#FFF196"));
//            }
            myHolder.binding.tvExam.setText("Exam"+(position+1));
            myHolder.binding.tvScore.setText("score: "+usersScores.get(position).getScore()+"/20");
        }

    }

    @Override
    public int getItemCount() {
        return usersScores.size();
    }

    public class UserScoreHolder extends RecyclerView.ViewHolder {
        ScoreItemBinding binding;
        EmptyStateBinding binding2;

        public UserScoreHolder(ScoreItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        public UserScoreHolder(EmptyStateBinding binding) {
            super(binding.getRoot());
            this.binding2 = binding;

        }

    }
}
