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
public class Story {

    @PrimaryKey
    private long storyId;
    private long userId;
    private Bitmap storyImg;

    public Story(Bitmap storyImg) {
        this.storyImg = storyImg;
    }

    public Bitmap getStoryImg() {
        return storyImg;
    }

    public void setStoryImg(Bitmap storyImg) {
        this.storyImg = storyImg;
    }

    public long getStoryId() {
        return storyId;
    }

    public void setStoryId(long storyId) {
        this.storyId = storyId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
