package com.example.myapplicationforfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplicationforfinal.databinding.ActivityMainBinding;
import com.example.myapplicationforfinal.databinding.CustomToastActivityBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    CustomToastActivityBinding binding2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        binding2 = CustomToastActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast myToast = new Toast(MainActivity.this);
                myToast.setDuration(Toast.LENGTH_LONG);
                myToast.setView(binding2.root);

                myToast.show();
            }
        });



    }




}