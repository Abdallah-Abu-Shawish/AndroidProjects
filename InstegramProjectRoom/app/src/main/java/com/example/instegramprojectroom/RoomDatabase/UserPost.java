package com.example.instegramprojectroom.RoomDatabase;

import android.graphics.Bitmap;

public class UserPost {

    private String title;
    private long userId;
    private String userName;
    private String detaile;
    private Bitmap postImg;
    private Bitmap userImg;

    public UserPost(String title, long userId, String userName, String detaile, Bitmap postImg, Bitmap userImg) {
        this.title = title;
        this.userId = userId;
        this.userName = userName;
        this.detaile = detaile;
        this.postImg = postImg;
        this.userImg = userImg;
    }

    public UserPost() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDetaile() {
        return detaile;
    }

    public void setDetaile(String detaile) {
        this.detaile = detaile;
    }

    public Bitmap getPostImg() {
        return postImg;
    }

    public void setPostImg(Bitmap postImg) {
        this.postImg = postImg;
    }

    public Bitmap getUserImg() {
        return userImg;
    }

    public void setUserImg(Bitmap userImg) {
        this.userImg = userImg;
    }
}
