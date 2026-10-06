package com.example.instegramprojectroom;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.instegramprojectroom.databinding.ActivityAddPostBinding;
import com.example.instegramprojectroom.databinding.ActivityMainBinding;

public class AddPostActivity extends AppCompatActivity {

   ActivityAddPostBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




    }
}