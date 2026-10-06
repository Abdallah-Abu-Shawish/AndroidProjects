package com.example.recicleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.recicleview.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivityH extends AppCompatActivity{

    ActivityMainBinding binding;
    RecicleAdabterH adabter;
    private ArrayList<Game2> games;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        games = new ArrayList<>();
        games.add(new Game2("%20off","Apple","Watch",R.drawable.apple_watch1));
        games.add(new Game2("%30off","Apple","Watch",R.drawable.apple_watch2));
        games.add(new Game2("%35off","Apple","Watch",R.drawable.apple_watch3));

        adabter = new RecicleAdabterH(this,games);
        binding.gamesList.setAdapter(adabter);
      //  LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
       LinearLayoutManager linearLayoutManagerH = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
     //   GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        binding.gamesList.setLayoutManager(linearLayoutManagerH);



    }

}