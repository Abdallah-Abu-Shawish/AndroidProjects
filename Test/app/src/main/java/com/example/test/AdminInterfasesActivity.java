package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.test.databinding.ActivityAdminInterfasesBinding;

public class AdminInterfasesActivity extends AppCompatActivity {

    ActivityAdminInterfasesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        binding = ActivityAdminInterfasesBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        binding.editCardProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminInterfasesActivity.this, ProfileActivity.class);
                startActivity(intent);


            }
        });


        binding.editCardProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminInterfasesActivity.this, ViewAllProductActivity.class);
                startActivity(intent);


            }
        });


        binding.addProductAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminInterfasesActivity.this, AddWatchesActivity.class);
                startActivity(intent);


            }

        });

    }
}