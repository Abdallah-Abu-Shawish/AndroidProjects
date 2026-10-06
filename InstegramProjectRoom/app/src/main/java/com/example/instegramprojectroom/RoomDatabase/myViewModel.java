package com.example.instegramprojectroom.RoomDatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.instegramprojectroom.Post;

import java.util.List;

public class myViewModel extends AndroidViewModel {
    private MyRepository repository;
    private LiveData<List<Post>> allPosts;

    public myViewModel(@NonNull Application application) {
        super(application);
        repository = new MyRepository(application);
        allPosts = repository.getAllPosts();
    }

    public void insertPost(Post post) {
      repository.insertPost(post);
    }

    public void updatePost(Post post) {
        repository.updatePost(post);
    }

    public void deletePost(Post post) {
       repository.deletePost(post);
    }

    public void deleteAllPosts() {
       repository.deleteAllPosts();
    }

    public LiveData<List<Post>> getAllPosts() {
        return allPosts;
    }




}
