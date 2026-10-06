package com.example.instegramprojectroom;

import android.graphics.Bitmap;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.instegramprojectroom.RoomDatabase.ImageConvertor;


@Entity(foreignKeys = {@ForeignKey(entity = User.class,parentColumns = {"id"},
        childColumns = {"userId"},onUpdate = ForeignKey.CASCADE,onDelete = ForeignKey.CASCADE)})
@TypeConverters(ImageConvertor.class)
public class Post {

    @PrimaryKey(autoGenerate = true)
    private long postId;
    private String title;
    private long userId;
    private String userName;
    private String detaile;
    private Bitmap postImg;
    private Bitmap userImg;


    public Post() {
    }

    public Post(String title, String userName, String detaile, Bitmap postImg, Bitmap userImg) {
        this.title = title;
        this.userName = userName;
        this.detaile = detaile;
        this.postImg = postImg;
        this.userImg = userImg;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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
}
