package com.example.firstfirebaseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.firstfirebaseproject.databinding.ActivityMain2Binding;
import com.example.firstfirebaseproject.databinding.ActivityMainBinding;

public class MainActivity2 extends AppCompatActivity {
    ActivityMain2Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



    }
}