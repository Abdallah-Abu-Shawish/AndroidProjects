package com.example.roomdatabasetest;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserScoreDao {


    @Insert
    long insertUserScore(UserScore score);

    @Update
    void updateUserScore(UserScore score);

    @Delete
    int deleteUserScore(UserScore score);

    @Query("SELECT * FROM UserScore")
    List<UserScore> getAllUsersScores();


//    @Query("SELECT score,name,image FROM UserScore JOIN User ON user_id = :id")
//    List<ShowUser> getShowUser(long id);

//    @Query("SELECT User.username as _username, User.name as _name, UserScore.score as _score FROM UserScore JOIN User ON UserScore.user_id = User.id")
//    List<ShowUser> getShowUser();
    @Query("SELECT * FROM UserScore WHERE user_id = :id")
    List<UserScore> getUserScore(long id);
}
