package com.example.instegramproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.instegramproject.databinding.ActivityShowStoryBinding;

public class ShowStoryActivity extends AppCompatActivity {

    ActivityShowStoryBinding binding;
    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowStoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = new Database(this);
        Intent intent = getIntent();
        int storyId = intent.getIntExtra("storyId",-1);
        Story story = db.getStoryById(storyId);
        if (story == null){
            Toast.makeText(ShowStoryActivity.this, "null", Toast.LENGTH_SHORT).show();
        }else {
            binding.imgShowStory.setImageBitmap(story.getStoryImg());

        }
        Handler mHandler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (true) {
                    try {
                        Thread.sleep(10000);
                        mHandler.post(new Runnable() {

                            @Override
                            public void run() {
                                // TODO Auto-generated method stub
                                // Write your code here to update the UI.
                                finish();
                            }

                        });
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
            }}).start();




    }
}