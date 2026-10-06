package com.example.mydatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import com.example.mydatabase.databinding.ActivitySingInBinding;

public class SingInActivity extends AppCompatActivity {

    ActivitySingInBinding binding;
    MyDataBase dataBase;
    int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivitySingInBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        dataBase = new MyDataBase(this);








        binding.singIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = binding.phoneIn.getText().toString();
                String password = binding.passwordIn.getText().toString();
                flag = 0;
                if (binding.phoneIn.getText().toString().isEmpty()){
                    binding.phoneIn.setError("You should fill username");
                    flag = 1;
                }
                if (binding.passwordIn.getText().toString().isEmpty()){
                    binding.passwordIn.setError("You should fill password");
                    flag = 1;
                }

                if (flag == 0){
                    if (checkLogin(userName,password)){
                        Intent intentSingIn  = new Intent(SingInActivity.this,MainActivityForHome.class);
                        startActivity(intentSingIn);
                        finish();
                    }else {
                        AlertDialog.Builder builder=new AlertDialog.Builder(SingInActivity.this);
                        builder.setTitle("Warning").setMessage("The entered data don`t match with any account data");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }

                        });
                        AlertDialog dialog = builder.create();
                        dialog.setCancelable(false);
                        dialog.show();
                    }

                }else {
                    ShowAlertDialgo();
                }

            }
        });



        binding.tvSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentFromSingIn = new Intent(SingInActivity.this,MainActivity.class);
                startActivity(intentFromSingIn);
            }
        });





    }

    private void ShowAlertDialgo(){


        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Warning");
        builder.setMessage("You should fill both username and password");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }

        });

        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();


    }
    @SuppressLint("Range")
    private boolean checkLogin(String userName , String password){
        Cursor cursor = dataBase.getUsers();
        while (cursor.moveToNext()){
            if (cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_NAME)).equals(userName)){
                if (cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_PASSWORD)).equals(password)){
                    return true;
                }
                return false;
            }
          }
        return false;
    }

    @SuppressLint("Range")
    private User getUser(String userName , String password){
        Cursor cursor = dataBase.getUsers();
        User user = new User();
        while (cursor.moveToNext()){
            if (cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_NAME)).equals(userName)){
                if (cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_PASSWORD)).equals(password)){
                    user.setId(cursor.getInt(cursor.getColumnIndex(MyDataBase.COLUMN_ID)));
                    user.setName(cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_NAME)));
                    user.setPassword(cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_PASSWORD)));
                    user.setPhone(cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_PHONE)));
                    user.setEmail(cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_EMAIL)));
                    user.setCountry(cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_COUNTRY)));
                    user.setGender(cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_GENDER)));
                }

            }
        }
        return user;
    }
}