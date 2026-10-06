package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.databinding.SingInBinding;

public class SingInActivity extends AppCompatActivity {

    SingInBinding binding;
    MyDataBase dataBase;
    int flag = 0;
    String username;
    String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = SingInBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);

        SharedPreferences preferences = getSharedPreferences("remember",MODE_PRIVATE);
        boolean b = preferences.getBoolean("isRemember",false);
        if (b){
            startActivity(new Intent(SingInActivity.this,MainActivityForHome.class));
        }
        setContentView(binding.getRoot());
        dataBase = new MyDataBase(this);


        binding.singIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 0;


                if (binding.usernameIn.getText().toString().isEmpty()){
                    binding.usernameIn.setError("You should fill username");
                    flag = 1;
                }
                if (binding.passwordIn.getText().toString().isEmpty()){
                    binding.passwordIn.setError("You should fill password");
                    flag = 1;
                } if (binding.passwordIn.getText().toString().equals("a")) {
                    if (binding.usernameIn.getText().toString().equals("a")) {
                        flag = 2;
                        Intent intent = new Intent(SingInActivity.this, AdminInterfasesActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
                if (flag == 0){
                     username = binding.usernameIn.getText().toString();
                     password = binding.passwordIn.getText().toString();
                    if (checkLogin(username,password)){
                        //االيوزر نيم والباسورد اللي بدي أمسكهن عشان البروفايل
                      if (dataBase.AddUserTemp(username,password)){
                          if (dataBase.getUserTemp()== null){
                              dataBase.AddUserTemp(username,password);
                              Toast.makeText(SingInActivity.this, "Added", Toast.LENGTH_SHORT).show();
                          }else {
                              dataBase.updateUserTemp(username,password);
                              Toast.makeText(SingInActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                          }
                          Toast.makeText(SingInActivity.this, "done", Toast.LENGTH_SHORT).show();
                      }else
                          Toast.makeText(SingInActivity.this, "Problem", Toast.LENGTH_SHORT).show();
                        Intent intentSingIn  = new Intent(SingInActivity.this,MainActivityForHome.class);
                        intentSingIn.putExtra("username",username);
                        intentSingIn.putExtra("password",password);
                        startActivity(intentSingIn);
                        finish();
                    }else {
                        ShowAlertDialgoUserNotFound();
                    }

                } else {
                    ShowAlertDialgo();
                }
              }

        });


        binding.rememberMe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences preferences = getSharedPreferences("remember",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("isRemember",isChecked);
                if (username.isEmpty()){
                    ShowAlertDialgo();
                    if (password.isEmpty()){
                        ShowAlertDialgo();
                    }
                }else if (password.isEmpty()){
                    ShowAlertDialgo();
                } else
                    editor.putInt("userId",getUserID(username,password));
                editor.apply();



            }
        });



        binding.tvSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentFromSingIn = new Intent(SingInActivity.this,SingUpActivity.class);
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
    private void ShowAlertDialgoUserNotFound(){


        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Warning");
        builder.setMessage("username OR password are not correct");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
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
            if (cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_USERNAME)).equals(userName)){
                if (cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_PASSWORD)).equals(password)){
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    @SuppressLint("Range")
    private int getUserID(String userName , String password){
        Cursor cursor = dataBase.getUsers();
        User user = new User();
        while (cursor.moveToNext()){
            if (cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_USERNAME)).equals(userName)){
                if (cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_PASSWORD)).equals(password)){
                   user.setUserName(cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_USERNAME)));
                }
            }
        }
        return user.getId();
    }

}