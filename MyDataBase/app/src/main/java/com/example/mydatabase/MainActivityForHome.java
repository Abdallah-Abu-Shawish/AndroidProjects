package com.example.mydatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.mydatabase.databinding.ActivityMainForHomeBinding;

import java.util.ArrayList;

public class MainActivityForHome extends AppCompatActivity implements RecicleAdabter.ClickItem {

    ActivityMainForHomeBinding binding;
RecicleAdabter adabter;
MyDataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainForHomeBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        dataBase = new MyDataBase(this);

       ArrayList<User> users =  dataBase.getAllUsers();

        adabter = new RecicleAdabter(this,users,this::clickOnItem);
        binding.viewUsers.setAdapter(adabter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.viewUsers.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void clickOnItem(int position) {

    }
}