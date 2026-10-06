package com.example.test.recicleview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.example.test.R;
import com.example.test.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivityH extends AppCompatActivity implements RecicleAdabterH.ClickOffer {

    ActivityMainBinding binding;
    RecicleAdabterH adabter;
    private ArrayList<Game2> games;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        games = new ArrayList<>();
        games.add(new Game2("%20off","Apple","Watch", R.drawable.apple_watch1));
        games.add(new Game2("%30off","Apple","Watch",R.drawable.apple_watch2));
        games.add(new Game2("%35off","Apple","Watch",R.drawable.apple_watch3));

        adabter = new RecicleAdabterH(this,games,this::clickOnOffer);
        binding.HOffers.setAdapter(adabter);
      //  LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
       LinearLayoutManager linearLayoutManagerH = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
     //   GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        binding.HOffers.setLayoutManager(linearLayoutManagerH);



    }


    @Override
    public void clickOnOffer(int position) {

    }
}