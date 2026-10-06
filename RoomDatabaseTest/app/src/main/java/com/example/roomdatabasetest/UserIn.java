package com.example.roomdatabasetest;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = User.class, parentColumns = {"id"}, childColumns = {"userIn_id"}))
public class UserIn {
    @PrimaryKey(autoGenerate = true)
    private long _id;
    @NonNull
    private long userIn_id;
    @NonNull
    private String userIn_username;


    public UserIn(@NonNull long userIn_id, @NonNull String userIn_username) {
        this.userIn_id = userIn_id;
        this.userIn_username = userIn_username;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public long getUserIn_id() {
        return userIn_id;
    }


    public void setUserIn_id(long userIn_id) {
        this.userIn_id = userIn_id;
    }

    public String getUserIn_username() {
        return userIn_username;
    }

    public void setUserIn_username(String userIn_username) {
        this.userIn_username = userIn_username;
    }
}
