package com.example.middtermquestoin1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.middtermquestoin1.databinding.ActivityFinancialBinding;
import com.example.middtermquestoin1.databinding.ActivityHobbiiesBinding;

public class HobbiiesActivity extends AppCompatActivity {

    ActivityHobbiiesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityHobbiiesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());






    }
}