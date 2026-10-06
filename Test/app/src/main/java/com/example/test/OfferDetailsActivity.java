package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.test.databinding.ActivityOfferDetailsBinding;

public class OfferDetailsActivity extends AppCompatActivity {

    ActivityOfferDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityOfferDetailsBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        Intent intentOfferDetails = getIntent();

        binding.imageOfferShowDetails.setImageResource(intentOfferDetails.getIntExtra("imgOffer",-1));
        binding.tvOfferDiscountDetail.setText(intentOfferDetails.getStringExtra("tvOfferDiscount"));
        binding.tvOfferStatusDetail.setText(intentOfferDetails.getStringExtra("tvDiscription"));
        binding.tvOfferTypeDetail.setText(intentOfferDetails.getStringExtra("tvOfferProductType"));


    }
}