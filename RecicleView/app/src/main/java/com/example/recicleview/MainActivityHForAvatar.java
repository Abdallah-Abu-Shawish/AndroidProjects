package com.example.recicleview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.recicleview.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivityHForAvatar extends AppCompatActivity{

    ActivityMainBinding binding;
    RecicleAdabterForAvatar adabter;
    private ArrayList<Avatar> avatars;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        avatars = new ArrayList<>();
        avatars.add(new Avatar(R.drawable.profile));
        avatars.add(new Avatar(R.drawable.profile));
        avatars.add(new Avatar(R.drawable.profile));
        avatars.add(new Avatar(R.drawable.profile));
        adabter = new RecicleAdabterForAvatar(this,avatars);
        binding.gamesList.setAdapter(adabter);
      //  LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
       LinearLayoutManager linearLayoutManagerH = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
     //   GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        binding.gamesList.setLayoutManager(linearLayoutManagerH);



    }

}