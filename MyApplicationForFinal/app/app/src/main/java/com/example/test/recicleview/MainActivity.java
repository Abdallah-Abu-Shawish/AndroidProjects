package com.example.test.recicleview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.example.test.R;
import com.example.test.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecicleAdabter.ClickItem{

    ActivityMainBinding binding;
    RecicleAdabter adabter;
    private ArrayList<Game> games;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        games = new ArrayList<>();
        games.add(new Game("Sammsung Watch", "Gxt46","Price : 100$", R.drawable.apple_watch1_card1));
        games.add(new Game("Rolex watch","PgXc45" ,"Price : 1000$", R.drawable.rolex_watch1));
        games.add(new Game("Apple Watch","CVb9u" ,"Price : 140$", R.drawable.apple_watch6));
        games.add(new Game("Rolex Watch","Trhge234" ,"Price : 2500$", R.drawable.rolex_watch4));
        games.add(new Game("Casio Watch", "Caci63t","Price : 75$", R.drawable.casio_watch1));
        games.add(new Game("Sammsung Watch", "KGui037","Price : 450$", R.drawable.samsung_watch11));

        adabter = new RecicleAdabter(this,games,this::clickOnItem);
        binding.watchesList.setAdapter(adabter);
      //  LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
       LinearLayoutManager linearLayoutManagerH = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
      //  GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        binding.watchesList.setLayoutManager(linearLayoutManagerH);



    }


    @Override
    public void clickOnItem(int position) {

    }
}