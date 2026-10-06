package com.example.roomdatabasetest;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    private long id;
    @NonNull
    private String name;

    private Uri image;
    @NonNull
    private String email;
    @NonNull
    private String username;
    @NonNull
    private String password;


//    public User(@NonNull String name, @NonNull String email, @NonNull String username, @NonNull String password) {
//        this.name = name;
//        this.email = email;
//        this.username = username;
//        this.password = password;
//    }

    public User(@NonNull String name, Uri image, @NonNull String email, @NonNull String username, @NonNull String password) {
        this.name = name;
        this.image = image;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    public Uri getImage() {
        return image;
    }

    public void setImage(Uri image) {
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
