package com.example.roomdatabasetest;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = User.class, parentColumns = {"id"}, childColumns = {"user_id"}))
public class UserScore {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "s_id")
    private long id;

    @NonNull
    private int score;
    @ColumnInfo(name = "user_id")
    private long user_score_id;
    public UserScore(int score, long user_score_id) {
        this.score = score;
        this.user_score_id = user_score_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public long getUser_score_id() {
        return user_score_id;
    }

    public void setUser_score_id(long user_score_id) {
        this.user_score_id = user_score_id;
    }
}
