package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.test.databinding.ActivityReceiptBinding;

public class ReceiptActivity extends AppCompatActivity {

    ActivityReceiptBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityReceiptBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        binding.showQuntity.setText(intent.getIntExtra("quntity",0));
        binding.showUnitPrice.setText(intent.getIntExtra("price",0)+"$");
//        binding.showTotal.setText((Integer.parseInt(intent.getStringExtra("quntity"))*Integer.parseInt(intent.getStringExtra("price")))+"$");

        binding.btnThanks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}