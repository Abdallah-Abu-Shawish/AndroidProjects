package com.example.mydatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;


public class MyDataBase extends SQLiteOpenHelper {


    public final static String DB_NAME = "UserData";
    public final static int DB_VERSION = 1;
    public final static String TABLE_USER = "user";
    public final static String COLUMN_ID = "id";
    public final static String COLUMN_NAME = "name";
    public final static String COLUMN_PHONE = "phone";
    public final static String COLUMN_EMAIL = "email";
    public final static String COLUMN_PASSWORD = "password";
    public final static String COLUMN_COUNTRY = "country";
    public final static String COLUMN_GENDER = "gender";


    public MyDataBase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String table_user = "create table "+TABLE_USER+"("+
                COLUMN_ID+" integer primary key autoincrement,"+
                COLUMN_NAME+" text not null,"+
                COLUMN_PHONE+" text not null,"+
                COLUMN_EMAIL+" text not null,"+
                COLUMN_PASSWORD+" text not null,"+
                COLUMN_COUNTRY+" text not null,"+
                COLUMN_GENDER+" text not null"+")";
        sqLiteDatabase.execSQL(table_user);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabaseUpgrade, int oldVersion, int newVersion) {

        sqLiteDatabaseUpgrade.execSQL("drop table if exists "+TABLE_USER );
    }

    public boolean insertUser(User user){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,user.getName());
        values.put(COLUMN_PHONE,user.getPhone());
        values.put(COLUMN_EMAIL,user.getEmail());
        values.put(COLUMN_PASSWORD,user.getPassword());
        values.put(COLUMN_COUNTRY,user.getCountry());
        values.put(COLUMN_GENDER,user.getGender());
        long result = database.insert(TABLE_USER,null,values);
        return result != -1;
    }
    public boolean updateUser(User user){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,user.getName());
        values.put(COLUMN_PHONE,user.getPhone());
        values.put(COLUMN_EMAIL,user.getEmail());
        values.put(COLUMN_PASSWORD,user.getPassword());
        values.put(COLUMN_COUNTRY,user.getCountry());
        values.put(COLUMN_GENDER,user.getGender());
        String[] args = {user.getId()+""};
        int result = database.update(TABLE_USER,values,"id=?",args);
        return result > 0;
    }

    public boolean deleteUser(User user){
        SQLiteDatabase database = this.getWritableDatabase();
        String[] args = {user.getId()+""};
        int result = database.delete(TABLE_USER,COLUMN_ID+"=?",args);
        return result > 0;
    }


    public Cursor getUsers(){
        SQLiteDatabase database = this.getReadableDatabase();
        return database.rawQuery("select * from "+TABLE_USER,null);
    }

    public ArrayList<User> getAllUsers(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE_USER,null);
        ArrayList<User> userList = new ArrayList<>();
        while (cursor.moveToNext()){
            User user = new User();
            user.setName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)));
            user.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL)));
            user.setPhone(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PHONE)));
            user.setPassword(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PASSWORD)));
            user.setCountry(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COUNTRY)));
            user.setGender(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_GENDER)));
            userList.add(user);
        }
        cursor.close();
        return userList;
    }



}