package com.example.instegramprojectroom;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.instegramprojectroom.RoomDatabase.ImageConvertor;

@Entity
@TypeConverters(ImageConvertor.class)
public class User {
    @PrimaryKey
    private long id;
    @NonNull
    private String name;
    @NonNull
    private Bitmap img;
    @NonNull
    private String username;
    @NonNull
    private String password;

    public User(String name, Bitmap img, String username, String password) {
        this.name = name;
        this.img = img;
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }
}
