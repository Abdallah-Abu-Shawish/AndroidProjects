package com.example.instegramproject;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

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

import com.example.instegramproject.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    Uri uri;
    Bitmap bitmap;
    Database db;
    StoryAdapter storyAdapter;
    PostAdapter postAdapter;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = new Database(MainActivity.this);
        context = this.getApplicationContext();
        binding.userInName.setText(db.getUserByUsername(db.getInnerUser().getUsername()).getName());

        binding.navBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                    switch (item.getItemId()){

                        case R.id.home:
                           return true;

                        case R.id.profile:
                            Intent intent = new Intent(context ,ProfileActivity.class);
                            startActivity(intent);
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




        ArrayList<Story> stories = db.getAllStories();
        ArrayList<Post> posts = db.getAllPosts();
        storyAdapter = new StoryAdapter(stories, MainActivity.this, new StoryAdapter.onStoryClickable() {
            @Override
            public void getClickedStory(int position) {
                Intent intent = new Intent(MainActivity.this ,ShowStoryActivity.class);
                intent.putExtra("storyId",stories.get(position).getStoryId());
                startActivity(intent);
            }
        });
        binding.rvStory.setAdapter(storyAdapter);
        binding.rvStory.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        postAdapter = new PostAdapter(MainActivity.this,posts);
        binding.rvPost.setAdapter(postAdapter);
        binding.rvPost.setLayoutManager(new LinearLayoutManager(this));

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
                        if (bitmap == null){
                            Toast.makeText(MainActivity.this, "problem photo", Toast.LENGTH_SHORT).show();
                        }else{
                            String username = db.getInnerUser().getUsername();
                            Story story = new Story(username,bitmap);
                            db.insertStory(story);
                            binding.rvStory.setAdapter(storyAdapter);
                            binding.rvStory.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false));
                        }
                        // add story to rv_story

                    }
                });
        binding.btnAddStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent();
                intent2.setAction(Intent.ACTION_GET_CONTENT);
                intent2.setType("image/*");
                launcher2.launch(intent2);
            }
        });




   }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<Story> stories = db.getAllStories();
        ArrayList<Post> posts = db.getAllPosts();
        storyAdapter = new StoryAdapter(stories, MainActivity.this, new StoryAdapter.onStoryClickable() {
            @Override
            public void getClickedStory(int position) {
                Intent intent = new Intent(MainActivity.this ,ShowStoryActivity.class);
                intent.putExtra("storyId",stories.get(position).getStoryId());
                startActivity(intent);
            }
        });
        binding.rvStory.setAdapter(storyAdapter);
        binding.rvStory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        postAdapter = new PostAdapter(MainActivity.this,posts);
        binding.rvPost.setAdapter(postAdapter);
        binding.rvPost.setLayoutManager(new LinearLayoutManager(this));
    }




}