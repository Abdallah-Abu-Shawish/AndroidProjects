package com.example.test;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.example.test.databinding.ActivityProfileBinding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ProfileActivity extends AppCompatActivity {

    ActivityProfileBinding binding;
MyDataBase dataBase;
    Bitmap bitmap;
    int a = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        SharedPreferences preferences = getSharedPreferences("remember",MODE_PRIVATE);
        int userId = preferences.getInt("userId",-1);
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        a =1;
        dataBase = new MyDataBase(this);
        Intent profileIntent = getIntent();

//هدول عشان لما يرجع من الاديت يعرض البيانات المعدلة

        binding.profileName.setText(dataBase.getUsersByUserName(dataBase.getUserTemp()).getName());
        binding.profileEmail.setText(dataBase.getUsersByUserName(dataBase.getUserTemp()).getEmail());
        binding.profileUsername.setText(dataBase.getUsersByUserName(dataBase.getUserTemp()).getUserName());
        binding.profilePassword.setText(dataBase.getUsersByUserName(dataBase.getUserTemp()).getPassword());
        binding.profileCountry.setText(dataBase.getUsersByUserName(dataBase.getUserTemp()).getCountry());
        binding.profileGender.setText(dataBase.getUsersByUserName(dataBase.getUserTemp()).getGender());
        binding.profileImg.setImageBitmap(dataBase.getUsersByUserName(dataBase.getUserTemp()).getImage());
           // binding.profileName.setText(dataBase.getUsersByUserName(dataBase.getUserTemp()).getName());

        /*binding.profileName.setText(getUserByID(userId).getName());
        binding.profileUsername.setText(getUserByID(userId).getUserName());
        binding.profilePassword.setText(getUserByID(userId).getPassword());
        binding.profileEmail.setText(getUserByID(userId).getEmail());
        binding.profileCountry.setText(getUserByID(userId).getCountry());
        binding.profileGender.setText(getUserByID(userId).getGender());*/

        //حط هدول ب onresume
//        binding.profileName.setText(getUser(dataBase.getUserTemp().get(0),dataBase.getUserTemp().get(1)).getName());
//        binding.profilePassword.setText(getUser(dataBase.getUserTemp().get(0),dataBase.getUserTemp().get(1)).getPassword());
//        binding.profileEmail.setText(getUser(dataBase.getUserTemp().get(0),dataBase.getUserTemp().get(1)).getEmail());
//        binding.profileCountry.setText(getUser(dataBase.getUserTemp().get(0),dataBase.getUserTemp().get(1)).getCountry());
//        binding.profileUsername.setText(getUser(dataBase.getUserTemp().get(0),dataBase.getUserTemp().get(1)).getUserName());
//        binding.profileGender.setText(getUser(dataBase.getUserTemp().get(0),dataBase.getUserTemp().get(1)).getGender());
//        binding.profileImg.setImageBitmap(getUser(dataBase.getUserTemp().get(0),dataBase.getUserTemp().get(1)).getImage());

//        ActivityResultLauncher<Intent> lancher2 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
//                new ActivityResultCallback<ActivityResult>() {
//            @Override
//            public void onActivityResult(ActivityResult result) {
//                Intent intent = result.getData();
//                Uri img = intent.getData();
//                try {
//                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), img);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                binding.profileImg.setImageURI(img);
//            }
//        });


//        if (bitmap == null){
//            Toast.makeText(this, "Dont forget to set a photo for you", Toast.LENGTH_SHORT).show();
//        }else {
//            User user = getUserFromShow();
//            user.setImage(bitmap);
//            if (dataBase.updateUserImage(user)){
//                Toast.makeText(this, "done", Toast.LENGTH_SHORT).show();
//            }else {
//                Toast.makeText(this, "Problem", Toast.LENGTH_SHORT).show();
//            }
//
//        }




        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               SharedPreferences.Editor editor = preferences.edit();
               editor.remove("isRemember");
               editor.apply();
            }
        });


        binding.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentToEdit = new Intent(ProfileActivity.this,EditProfileActivity.class);
                startActivity(intentToEdit);
                finish();
            }
        });

    }

    @Override
    protected void onResume() {

        binding.profileName.setText(dataBase.getUsersByUserName(dataBase.getUserTemp()).getName());
        binding.profileEmail.setText(dataBase.getUsersByUserName(dataBase.getUserTemp()).getEmail());
        binding.profileUsername.setText(dataBase.getUsersByUserName(dataBase.getUserTemp()).getUserName());
        binding.profilePassword.setText(dataBase.getUsersByUserName(dataBase.getUserTemp()).getPassword());
        binding.profileCountry.setText(dataBase.getUsersByUserName(dataBase.getUserTemp()).getCountry());
        binding.profileGender.setText(dataBase.getUsersByUserName(dataBase.getUserTemp()).getGender());
        binding.profileImg.setImageBitmap(dataBase.getUsersByUserName(dataBase.getUserTemp()).getImage());

        super.onResume();


    }


    //    @SuppressLint("Range")
//    private User getUserWithoutImage(String userName , String password){
//        Cursor cursor = dataBase.getUsers();
//        User user = new User();
//        while (cursor.moveToNext()){
//            if (cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_USERNAME)).equals(userName)){
//                if (cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_PASSWORD)).equals(password)){
//                    user.setUserName(cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_USERNAME)));
//                    user.setPassword(cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_PASSWORD)));
//                    user.setName(cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_NAME)));
//                    user.setEmail(cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_EMAIL)));
//                    user.setCountry(cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_COUNTRY)));
//                    user.setGender(cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_GENDER)));
//                }
//
//            }
//        }
//        return user;
//    }
//
//    @SuppressLint("Range")
//    private User getUser(String userName , String password){
//        Cursor cursor = dataBase.getUsers();
//        User user = new User();
//        while (cursor.moveToNext()){
//            if (cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_USERNAME)).equals(userName)){
//                if (cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_PASSWORD)).equals(password)){
//                    user.setUserName(cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_USERNAME)));
//                    user.setPassword(cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_PASSWORD)));
//                    user.setName(cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_NAME)));
//                    user.setEmail(cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_EMAIL)));
//                    user.setCountry(cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_COUNTRY)));
//                    user.setGender(cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_GENDER)));
//                    byte[] bytes = cursor.getBlob(cursor.getColumnIndex(MyDataBase.COLUMN_IMAGE));
//                    user.setImage(getBitmap(bytes));
//                }
//
//            }
//        }
//        return user;
//    }

//    @SuppressLint("Range")
//    private User getUserFromShow(){
//        Cursor cursor = dataBase.getUsers();
//        Cursor cursor1 = dataBase.getShowUsers();
//        User user = new User();
//        while (cursor1.moveToNext()){
//          while (cursor.moveToNext()){
//                if (cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_USERNAME)).equals
//                      (cursor1.getString(cursor1.getColumnIndex(MyDataBase.COLUMN_USERNAME_SHOW)))&&
//                        cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_PASSWORD)).equals
//                        (cursor1.getString(cursor1.getColumnIndex(MyDataBase.COLUMN_PASSWORD_SHOW )))){
//                        user.setId(cursor.getInt(cursor.getColumnIndex(MyDataBase.COLUMN_ID)));
//                        user.setUserName(cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_USERNAME)));
//                        user.setPassword(cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_PASSWORD)));
//                        user.setName(cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_NAME)));
//                        user.setEmail(cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_EMAIL)));
//                        user.setCountry(cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_COUNTRY)));
//                        user.setGender(cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_GENDER)));
//                    byte[] bytes = cursor.getBlob(cursor.getColumnIndex(MyDataBase.COLUMN_IMAGE));
//                    user.setImage(getBitmap(bytes));
//                    }
//
//                }
//            }
//
//        return user;
//    }


//

}