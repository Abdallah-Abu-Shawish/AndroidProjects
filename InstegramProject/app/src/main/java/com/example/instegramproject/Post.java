package com.example.instegramproject;

import android.graphics.Bitmap;

public class Post {

    private int postId;
    private String title;
    private String userName;
    private String name;
    private String detaile;
    private Bitmap postImg;
    private int userId;
    private Bitmap userImg;

    public Post() {
    }

    public Post(String title, String userName, String name, String detaile, Bitmap postImg, Bitmap userImg) {
        this.title = title;
        this.userName = userName;
        this.name = name;
        this.detaile = detaile;
        this.postImg = postImg;
        this.userImg = userImg;
    }

    public Post(String title, String userName, String detaile, Bitmap postImg, Bitmap userImg) {
        this.title = title;
        this.userName = userName;
        this.detaile = detaile;
        this.postImg = postImg;
        this.userImg = userImg;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Post(String title, String userName, String detaile, Bitmap postImg, int userId, Bitmap userImg) {
        this.title = title;
        this.userName = userName;
        this.detaile = detaile;
        this.postImg = postImg;
        this.userId = userId;
        this.userImg = userImg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
