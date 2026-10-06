package com.example.instegramproject;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "db.instegram";
    private static final int DATABASE_VERSION = 4;


    public static final String USER_TABLE = "user_table";
    public static final String COLUMN_USER_ID = "id";
    public static final String COLUMN_USER_NAME = "name";
    public static final String COLUMN_USER_IMG = "img";
    public static final String COLUMN_USER_USERNAME = "username";
    public static final String COLUMN_USER_PASSWORD = "password";


    public static final String POST_TABLE = "post_table";
    public static final String COLUMN_POST_ID = "post_id";
    public static final String COLUMN_POST_TITLE = "post_title";
    public static final String COLUMN_POST_DETAILE = "post_detaile";
    public static final String COLUMN_POST_USER_NAME = "post_user_name";
    public static final String COLUMN_POST_USER_USERNAME = "post_user_username";
    public static final String COLUMN_POST_USER_IMG = "post_user_img";
    public static final String COLUMN_POST_IMG = "post_img";


    public static final String STORY_TABLE = "story_table";
    public static final String COLUMN_STORY_ID = "story_id";
    public static final String COLUMN_STORY_USERNAME = "story_username";
    public static final String COLUMN_STORY_IMG = "story_img";


    public static final String INTER_USER_TABLE = "inter_user_table";
    public static final String COLUMN_INTER_USER_ID = "inter_user_id";
    public static final String COLUMN_INTER_USER_NAME_IN = "inter_user_name_in";
    public static final String COLUMN_INTER_USER_USERNAME_IN = "inter_user_username_in";
    public static final String COLUMN_INTER_USER_IMG_IN = "inter_user_img_in";



    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String user_table = "create table "+USER_TABLE+" ( " +
                COLUMN_USER_ID+" integer primary key autoincrement,"+
                COLUMN_USER_NAME+" text not null,"+
                COLUMN_USER_USERNAME+" text not null,"+
                COLUMN_USER_PASSWORD+" text not null,"+
                COLUMN_USER_IMG+" blob "+" )";

        String post_table = "create table "+POST_TABLE+" ( "+
                COLUMN_POST_ID+" integer primary key autoincrement,"+
                COLUMN_POST_TITLE+" text not null,"+
                COLUMN_POST_DETAILE+" text not null,"+
                COLUMN_POST_USER_USERNAME+" text not null,"+
                COLUMN_POST_IMG+" blob,"+
                COLUMN_POST_USER_NAME+" text not null,"+
                COLUMN_POST_USER_IMG+" blob " + " ) ";

        String story_table = "create table "+STORY_TABLE+" ( "+
                COLUMN_STORY_ID+" integer primary key autoincrement,"+
                COLUMN_STORY_USERNAME+" text not null,"+
                COLUMN_STORY_IMG+" blob"+ " ) ";

        String user_in_table = "create table "+INTER_USER_TABLE+" ( " +
                COLUMN_INTER_USER_ID+" integer primary key autoincrement,"+
                COLUMN_INTER_USER_USERNAME_IN+" text not null,"+
                COLUMN_INTER_USER_NAME_IN+" text not null,"+
                COLUMN_INTER_USER_IMG_IN+" blob "+ " )";

        db.execSQL(user_table);
        db.execSQL(user_in_table);
        db.execSQL(post_table);
        db.execSQL(story_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists "+USER_TABLE);
        db.execSQL("drop table if exists "+POST_TABLE);
        db.execSQL("drop table if exists "+STORY_TABLE);
        onCreate(db);
    }





    public boolean insertUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME,user.getName());
        values.put(COLUMN_USER_USERNAME,user.getUsername());
        values.put(COLUMN_USER_PASSWORD,user.getPassword());
        values.put(COLUMN_USER_IMG,getByteArray(user.getImg()));

        long result = db.insert(USER_TABLE,null,values);
        return result != 0;
    }

    public boolean deleteUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] args = {user.getId()+""};
        int result = db.delete(USER_TABLE,COLUMN_USER_ID+" =?",args);
        return result > 0;
    }

    public boolean updateUser(User user ,int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME,user.getName());
        values.put(COLUMN_USER_USERNAME,user.getUsername());
        values.put(COLUMN_USER_PASSWORD,user.getPassword());
        values.put(COLUMN_USER_IMG,getByteArray(user.getImg()));
        String[] args = {id+""};
        int result = db.update(USER_TABLE,values,COLUMN_USER_ID+" =?",args);
        return result != 0;
    }


    @SuppressLint("Range")
    public User getLoginUser(String userName , String password){
        SQLiteDatabase db = this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("select * from "+INTER_USER_TABLE,null);
        User user = new User();
        while (cursor.moveToNext()){
            if (cursor.getString(cursor.getColumnIndex(Database.COLUMN_USER_USERNAME)).equals(userName)){
                if (cursor.getString(cursor.getColumnIndex(Database.COLUMN_USER_PASSWORD)).equals(password)){
                    user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_INTER_USER_NAME_IN)));
                    byte[] bytes = cursor.getBlob(cursor.getColumnIndex(COLUMN_INTER_USER_IMG_IN));
                    user.setImg(getBitmap(bytes));
                }
            }
        }
        return user;
    }

    @SuppressLint("Range")
    public User getUserByUsername(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("select * from "+USER_TABLE,null);
        User user = new User();
        while (cursor.moveToNext()){
            if (cursor.getString(cursor.getColumnIndex(Database.COLUMN_USER_USERNAME)).equals(username)){
                    user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
                    user.setUsername(cursor.getString(cursor.getColumnIndex(COLUMN_USER_USERNAME)));
                    byte[] bytes = cursor.getBlob(cursor.getColumnIndex(COLUMN_USER_IMG));
                    user.setImg(getBitmap(bytes));
                }
            }

        return user;
    }


    public Cursor getUsers(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("select * from "+USER_TABLE,null);
    }


    @SuppressLint("Range")
    public ArrayList<User> getAllUsers(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+USER_TABLE,null);
        ArrayList<User> users = new ArrayList<>();
        while (cursor.moveToNext()){
            User user = new User();
            user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
            user.setUsername(cursor.getString(cursor.getColumnIndex(COLUMN_USER_USERNAME)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));
            byte[] bytes = cursor.getBlob(cursor.getColumnIndex(COLUMN_USER_IMG));
            user.setImg(getBitmap(bytes));
            users.add(user);
        }
        cursor.close();
        return users;
    }


    public boolean insertInUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_INTER_USER_NAME_IN,user.getName());
        values.put(COLUMN_INTER_USER_USERNAME_IN,user.getUsername());
        values.put(COLUMN_INTER_USER_IMG_IN,getByteArray(user.getImg()));
        long result = db.insert(INTER_USER_TABLE,null,values);
        return result != 0;
    }


    public boolean updateInUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_INTER_USER_ID,user.getId());
        values.put(COLUMN_INTER_USER_NAME_IN,user.getName());
        values.put(COLUMN_INTER_USER_IMG_IN,getByteArray(user.getImg()));
        String[] args = {user.getId()+""};
        int result = db.update(INTER_USER_TABLE,values,COLUMN_INTER_USER_ID+" =?",args);
        return result != 0;
    }


    public boolean insertStory(Story story){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_STORY_IMG,getByteArray(story.getStoryImg()));
        values.put(COLUMN_STORY_USERNAME,story.getStoryUsername());
        long result = db.insert(STORY_TABLE,null,values);
        return result != 0;
    }


    @SuppressLint("Range")
    public ArrayList<Story> getAllStories(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+STORY_TABLE,null);
        ArrayList<Story> stories = new ArrayList<>();
        while (cursor.moveToNext()){
            Story story = new Story();
            story.setStoryId(cursor.getInt(cursor.getColumnIndex(COLUMN_STORY_ID)));
            story.setStoryUsername(cursor.getString(cursor.getColumnIndex(COLUMN_STORY_USERNAME)));
            byte[] bytes = cursor.getBlob(cursor.getColumnIndex(COLUMN_STORY_IMG));
            story.setStoryImg(getBitmap(bytes));
            stories.add(story);
        }
        cursor.close();
        return stories;
    }

    @SuppressLint("Range")
    public Story getStoryById(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+STORY_TABLE,null);
        while (cursor.moveToNext()){
            Story story = new Story();
            story.setStoryId(cursor.getInt(cursor.getColumnIndex(COLUMN_STORY_ID)));
            if (story.getStoryId() == id) {
                story.setStoryUsername(cursor.getString(cursor.getColumnIndex(COLUMN_STORY_USERNAME)));
                byte[] bytes = cursor.getBlob(cursor.getColumnIndex(COLUMN_STORY_IMG));
                story.setStoryImg(getBitmap(bytes));
                return story;
            }
        }
        cursor.close();
        return null;
    }


    @SuppressLint("Range")
    public User getInnerUser() {
        SQLiteDatabase db = getReadableDatabase();
        User user = null;
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("Select * from " + INTER_USER_TABLE,null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_INTER_USER_ID)));
                user.setUsername(cursor.getString(cursor.getColumnIndex(COLUMN_INTER_USER_USERNAME_IN)));
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_INTER_USER_NAME_IN)));
                byte[] bytes = cursor.getBlob(cursor.getColumnIndex(COLUMN_INTER_USER_IMG_IN));
                user.setImg(getBitmap(bytes));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return user ;
    }




    public boolean insertPost(Post post){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_POST_TITLE,post.getTitle());
        values.put(COLUMN_POST_DETAILE,post.getDetaile());
        values.put(COLUMN_POST_USER_USERNAME,post.getUserName());
        values.put(COLUMN_POST_USER_NAME,post.getName());
        values.put(COLUMN_POST_IMG,getByteArray(post.getPostImg()));
        values.put(COLUMN_POST_USER_IMG,getByteArray(post.getUserImg()));

        long result = db.insert(POST_TABLE,null,values);
        return result != 0;
    }

    @SuppressLint("Range")
    public ArrayList<Post> getAllPosts(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+POST_TABLE,null);
        ArrayList<Post> posts = new ArrayList<>();
        while (cursor.moveToNext()){
            Post post = new Post();
            post.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_POST_TITLE)));
            post.setDetaile(cursor.getString(cursor.getColumnIndex(COLUMN_POST_DETAILE)));
            post.setUserName(cursor.getString(cursor.getColumnIndex(COLUMN_POST_USER_USERNAME)));
            post.setName(cursor.getString(cursor.getColumnIndex(COLUMN_POST_USER_NAME)));
            byte[] bytes1 = cursor.getBlob(cursor.getColumnIndex(COLUMN_POST_IMG));
            post.setPostImg(getBitmap(bytes1));
            byte[] bytes2 = cursor.getBlob(cursor.getColumnIndex(COLUMN_POST_USER_IMG));
            post.setUserImg(getBitmap(bytes2));
            posts.add(post);
        }
        cursor.close();
        return posts;
    }



    private byte[] getByteArray(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 0, stream);
        return stream.toByteArray();
    }

    private Bitmap getBitmap(byte[] bytes){
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }


}
