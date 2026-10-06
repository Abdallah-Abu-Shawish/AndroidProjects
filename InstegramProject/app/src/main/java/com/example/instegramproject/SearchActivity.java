package com.example.instegramproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.instegramproject.databinding.ActivitySearchBinding;
import com.google.android.material.navigation.NavigationBarView;

public class SearchActivity extends AppCompatActivity {

    ActivitySearchBinding binding;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        context = this.getApplicationContext();

        binding.navBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override

            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.home:
                        Intent intent = new Intent(context ,MainActivity.class);
                        startActivity(intent);
                        return true;

                    case R.id.profile:
                        Intent intent2 = new Intent(context ,ProfileActivity.class);
                        startActivity(intent2);
                        return true;

                    case R.id.add_post:
                        Intent intent3 = new Intent(context ,AddPostActivity.class);
                        startActivity(intent3);
                        return true;

                    case R.id.favorite:
                        Intent intent4 = new Intent(context ,FavoriteActivity.class);
                        startActivity(intent4);
                        return true;


                    case R.id.search:
                        return true;

                }
                return false;
            }
        });



    }
}