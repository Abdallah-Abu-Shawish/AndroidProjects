package com.example.storyapp;

public class Post {

    private String title;
    private String name;
    private String userImg;
    private String postImg;

    public Post(String title, String name, String userImg, String postImg) {
        this.title = title;
        this.name = name;
        this.userImg = userImg;
        this.postImg = postImg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getPostImg() {
        return postImg;
    }

    public void setPostImg(String postImg) {
        this.postImg = postImg;
    }
}
