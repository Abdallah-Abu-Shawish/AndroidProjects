package com.example.roomdatabasetest;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


@Dao
public interface UserInDao {

    @Insert
    long insertUserIn(UserIn userIn);

    @Update
    void updateUserIn(UserIn userIn);

    @Delete
    int deleteUserIn(UserIn userIn);


//    @Query("UPDATE UserIn SET userIn_id =:id AND userIn_username =:username WHERE _id =:_id")
//    long updateUserIn2(long _id, long id, String username);
    @Query("SELECT * FROM UserIn ORDER BY _id ASC")
    UserIn getAllUsersIn();

    @Query("SELECT * FROM UserIn ORDER BY _id ASC")
    UserIn getUsersIn();
    @Query("SELECT * FROM UserIn WHERE _id = :id")
    UserIn getUserInById(long id);

    @Query("SELECT * FROM UserIn WHERE userIn_id = :id")
    UserIn getUserInByUserId(long id);
    @Query("SELECT * FROM UserIn WHERE userIn_username = :username")
    UserIn getUserInByUsername(String username);



}
