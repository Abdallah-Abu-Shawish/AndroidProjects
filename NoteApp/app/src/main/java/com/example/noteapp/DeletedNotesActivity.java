package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.noteapp.databinding.ActivityDeletedNotesBinding;
import com.example.noteapp.databinding.ActivityMainBinding;

public class DeletedNotesActivity extends AppCompatActivity {

    ActivityDeletedNotesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDeletedNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



    }
}