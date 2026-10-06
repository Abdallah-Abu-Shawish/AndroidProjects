package com.example.instegramproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.instegramproject.databinding.ActivityProfileBinding;
import com.google.android.material.navigation.NavigationBarView;

public class ProfileActivity extends AppCompatActivity {

    ActivityProfileBinding binding;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater()) ;
        setContentView(binding.getRoot());

        context = this.getApplicationContext();

        binding.navBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.home:
                        Intent intent = new Intent(context ,MainActivity.class);
                        startActivity(intent);
                        return true;

                    case R.id.profile:
                        return true;

                    case R.id.add_post:
                        Intent intent2 = new Intent(context ,AddPostActivity.class);
                        startActivity(intent2);
                        return true;

                    case R.id.favorite:
                        Intent intent3 = new Intent(context ,FavoriteActivity.class);
                        startActivity(intent3);
                        return true;


                    case R.id.search:
                        Intent intent4 = new Intent(context ,SearchActivity.class);
                        startActivity(intent4);
                        return true;

                }
                return false;
            }
        });




    }
}