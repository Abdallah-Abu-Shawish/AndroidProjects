package com.example.roomdatabasetest;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    long insertUser(User user);

    @Update
    void updateUser(User user);

    @Delete
    int deleteUser(User user);

    @Query("SELECT * FROM User")
    List<User> getAllUsers();


    @Query("SELECT * FROM User WHERE id = :id")
    User getUser(long id);

    @Query("SELECT * FROM User WHERE username = :username")
    User getUserByUsername(String username);

    @Query("SELECT * FROM User WHERE username =:username AND password =:password")
    User getUser(String username ,String password);

}
