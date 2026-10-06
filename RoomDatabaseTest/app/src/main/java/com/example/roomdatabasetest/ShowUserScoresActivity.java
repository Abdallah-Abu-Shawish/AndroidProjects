package com.example.roomdatabasetest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.roomdatabasetest.databinding.ActivityShowUserScoresBinding;

import java.util.List;

public class ShowUserScoresActivity extends AppCompatActivity {

    ActivityShowUserScoresBinding binding;
    AppRoomDataBase dataBase;

    UserScoreAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityShowUserScoresBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dataBase = AppRoomDataBase.getDatabase(this);
        UserIn userIn = dataBase.userInDao().getAllUsersIn();
        List<UserScore> myScores = dataBase.userScoreDao().getUserScore(userIn.getUserIn_id());
        adapter = new UserScoreAdapter(this,myScores,userIn.getUserIn_id());
        binding.rvMyScores.setLayoutManager(new LinearLayoutManager(this));
        binding.rvMyScores.setAdapter(adapter);


        binding.btnBackToProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });

    }
}