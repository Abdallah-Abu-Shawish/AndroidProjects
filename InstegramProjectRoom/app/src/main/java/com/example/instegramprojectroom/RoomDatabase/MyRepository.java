package com.example.instegramprojectroom.RoomDatabase;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.instegramprojectroom.Post;

import java.util.List;

public class MyRepository {

    private PostDao postDao;
    LiveData<List<Post>> allPosts;

    public MyRepository(Application application) {
        InstaRoomDatabase postDatabase = InstaRoomDatabase.getDatabase(application);
        postDao = postDatabase.PostDao();
        allPosts = postDao.getAllPosts();
    }

    public void insertPost(Post post) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                postDao.insertPost(post);
            }
        }).start();
    }

    public void updatePost(Post post) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                postDao.updatePost(post);
            }
        }).start();
    }

    public void deletePost(Post post) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                postDao.deletePost(post);
            }
        }).start();
    }

    public void deleteAllPosts() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                postDao.deleteAllPosts();
            }
        }).start();
    }

    public LiveData<List<Post>> getAllPosts() {
        return allPosts;
    }






}
