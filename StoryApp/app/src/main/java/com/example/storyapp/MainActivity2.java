package com.example.storyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.storyapp.databinding.ActivityMainBinding;

public class MainActivity2 extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());







    }
}