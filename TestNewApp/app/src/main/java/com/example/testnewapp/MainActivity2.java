package com.example.testnewapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.testnewapp.databinding.ActivityMain2Binding;
import com.example.testnewapp.databinding.ActivityMainBinding;

public class MainActivity2 extends AppCompatActivity {

    ActivityMain2Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        binding.show.setText(getIntent().getStringExtra("data"));
    }
}