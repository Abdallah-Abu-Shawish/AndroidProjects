package com.example.dev_quiz1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.dev_quiz1.databinding.ActivityMain2Binding;
import com.example.dev_quiz1.databinding.ActivityMainBinding;

public class MainActivity2 extends AppCompatActivity {
    ActivityMain2Binding binding;
    String con_phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        con_phone = intent.getStringExtra("phone");




        binding.btnSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.edPhoneConf.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity2.this, "Enter the phone number", Toast.LENGTH_SHORT).show();
                }else {
                    if (binding.edPhoneConf.getText().toString().equals(con_phone)) {
                        Intent intent1 = new Intent(MainActivity2.this, MainActivity3.class);
                        startActivity(intent1);
                    }else{
                        Toast.makeText(MainActivity2.this, "You should enter the same phone number", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });





    }
}