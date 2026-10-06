package com.example.instegramproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.instegramproject.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = new Database(LoginActivity.this);



        binding.tvSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this ,SingUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = binding.edPassword.getText().toString();
                String username = binding.edUsername.getText().toString();
                if (checkLogin(username,password)){
                        if (db.insertInUser(db.getUserByUsername(username))){
                            Toast.makeText(LoginActivity.this, "User in inserted", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this ,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(LoginActivity.this, "user in is not inserted", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                    Toast.makeText(LoginActivity.this, "Wrong username or password", Toast.LENGTH_SHORT).show();
                    }

                }
            });





    }

    @SuppressLint("Range")
    private boolean checkLogin(String userName , String password){
        Cursor cursor = db.getUsers();
        while (cursor.moveToNext()){
            if (cursor.getString(cursor.getColumnIndex(Database.COLUMN_USER_USERNAME)).equals(userName)){
                if (cursor.getString(cursor.getColumnIndex(Database.COLUMN_USER_PASSWORD)).equals(password)){
                    return true;
                }
                return false;
            }
        }
        return false;
    }



}