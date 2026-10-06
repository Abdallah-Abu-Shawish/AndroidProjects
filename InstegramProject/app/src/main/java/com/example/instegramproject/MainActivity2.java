package com.example.instegramproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.instegramproject.databinding.ActivityMain2Binding;

public class MainActivity2 extends AppCompatActivity {

    ActivityMain2Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());




    }
}