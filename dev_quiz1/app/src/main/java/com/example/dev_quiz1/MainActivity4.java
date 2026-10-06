package com.example.dev_quiz1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.dev_quiz1.databinding.ActivityMain3Binding;
import com.example.dev_quiz1.databinding.ActivityMain4Binding;

public class MainActivity4 extends AppCompatActivity {
ActivityMain4Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMain4Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        binding.tvShowMass.setText(intent.getStringExtra("massage2"));

        binding.btnReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.putExtra("message", binding.edMassage2.getText().toString());
                setResult(100, intent);
                finish();

            }
            });

        }

    }




