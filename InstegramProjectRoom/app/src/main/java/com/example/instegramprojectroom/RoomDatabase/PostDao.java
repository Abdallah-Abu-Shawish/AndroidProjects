package com.example.instegramprojectroom.RoomDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.instegramprojectroom.Post;

import java.util.List;
@Dao
public interface PostDao {
    @Insert
    void insertPost(Post post);

    @Update
    void updatePost(Post post);

    @Delete
    void deletePost(Post post);

    @Query("delete from post")
    void deleteAllPosts();

    @Query("Select * from post order by userName desc")
    LiveData<List<Post>> getAllPosts();

    @SuppressWarnings("AndroidUnresolvedRoomSqlReference")
    @Query("select title,detaile,userName,userImg,postImg from post inner join User on userId=id group by name ")
    LiveData<List<UserPost>> getAllXPosts();



}
