package com.example.instegramproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.instegramproject.databinding.ActivityWelcomeBinding;

public class WelcomeActivity extends AppCompatActivity {

    ActivityWelcomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityWelcomeBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        binding.btnGoToExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, MainActivity2.class));
            }
        });


    }
}