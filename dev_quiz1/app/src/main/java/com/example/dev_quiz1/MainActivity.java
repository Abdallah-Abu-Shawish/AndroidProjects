package com.example.dev_quiz1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.dev_quiz1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.btnSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.ed1.getText().toString().isEmpty()||binding.ed2.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Enter all the data", Toast.LENGTH_SHORT).show();
                } else{
                    Intent intent = new Intent(MainActivity.this , MainActivity2.class);
                    intent.putExtra("name", binding.ed1.getText().toString());
                    intent.putExtra("phone", binding.ed2.getText().toString());
                    startActivity(intent);
                }

            }
        });











    }
}