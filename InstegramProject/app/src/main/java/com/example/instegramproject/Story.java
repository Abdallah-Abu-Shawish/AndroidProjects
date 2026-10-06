package com.example.instegramproject;

import android.graphics.Bitmap;

public class Story {

    private int storyId;
    private String storyUsername;
    private Bitmap storyImg;

    public Story() {
    }

    public Story(String storyUsername, Bitmap storyImg) {
        this.storyUsername = storyUsername;
        this.storyImg = storyImg;
    }

    public String getStoryUsername() {
        return storyUsername;
    }

    public void setStoryUsername(String storyUsername) {
        this.storyUsername = storyUsername;
    }

    public Story(Bitmap storyImg) {
        this.storyImg = storyImg;
    }

    public int getStoryId() {
        return storyId;
    }

    public void setStoryId(int storyId) {
        this.storyId = storyId;
    }

    public Bitmap getStoryImg() {
        return storyImg;
    }

    public void setStoryImg(Bitmap storyImg) {
        this.storyImg = storyImg;
    }
}
