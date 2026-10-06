package com.example.instegramproject;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.instegramproject.databinding.ActivityAddPostBinding;
import com.example.instegramproject.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;

import java.io.IOException;

public class AddPostActivity extends AppCompatActivity {

    ActivityAddPostBinding binding;
    Database db;
    Uri uri;
    Bitmap bitmap;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = new Database(AddPostActivity.this) ;
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
                        Intent intent2 = new Intent(context ,ProfileActivity.class);
                        startActivity(intent2);
                        return true;

                    case R.id.add_post:
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





        ActivityResultLauncher<Intent> launcher2 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Intent intent = result.getData();
                        uri = intent.getData();
                        try {
                            bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), uri);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        binding.imgPost.setImageBitmap(bitmap);
                    }
                });

        binding.btnAddImgPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent();
                intent2.setAction(Intent.ACTION_GET_CONTENT);
                intent2.setType("image/*");
                launcher2.launch(intent2);
            }
        });


        binding.btnDoPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = binding.edPostTitle.getText().toString();
                String detaile = binding.edPostDetaile.getText().toString();
                String username = db.getInnerUser().getUsername();
                User user = db.getUserByUsername(username);
                Bitmap userImg = user.getImg();
                if (userImg == null)
                    Toast.makeText(AddPostActivity.this, "no img null", Toast.LENGTH_SHORT).show();
                String name = user.getName();


                Post post = new Post(title,username,name,detaile,bitmap,userImg);

                if (db.insertPost(post)){
                    Toast.makeText(AddPostActivity.this, "Post inserted", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(AddPostActivity.this, "Post not inserted", Toast.LENGTH_SHORT).show();
                }

            }
        });






    }
}