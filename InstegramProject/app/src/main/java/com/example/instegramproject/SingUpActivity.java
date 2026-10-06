package com.example.instegramproject;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.example.instegramproject.databinding.ActivitySingUpBinding;

import java.io.IOException;

public class SingUpActivity extends AppCompatActivity {

    ActivitySingUpBinding binding;
    Bitmap bitmap;
    Uri uri;
    Database db = new Database(SingUpActivity.this);
    int a = 1;
    boolean flag = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySingUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        ActivityResultLauncher<Intent> launcher2 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Intent intent = result.getData();
                        uri = intent.getData();
                        try {
                            bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), uri);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        binding.imgUser.setImageBitmap(bitmap);
                    }
                });
        binding.btnAddUserImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent();
                intent2.setAction(Intent.ACTION_GET_CONTENT);
                intent2.setType("image/*");
                launcher2.launch(intent2);
            }
        });

        binding.tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingUpActivity.this ,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        binding.btnSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.edName.getText().toString();
                String username = binding.edUsername.getText().toString();
                String password = binding.edPassword.getText().toString();
                User user = new User(name,username,password,bitmap);
                if (!checkUsername(username)){
                    if (db.insertUser(user)){
                        Intent intent = new Intent(SingUpActivity.this ,LoginActivity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(SingUpActivity.this, "User inserted", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(SingUpActivity.this, "User is not inserted", Toast.LENGTH_SHORT).show();
                    }
                } else{
                    binding.edUsername.setError("Username is already exists");
                    Toast.makeText(SingUpActivity.this, "Username is already exists", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @SuppressLint("Range")
    private boolean checkUsername(String username){
        Cursor cursor = db.getUsers();
        while (cursor.moveToNext()){
            if (cursor.getString(cursor.getColumnIndex(Database.COLUMN_USER_USERNAME)).equals(username)){
                    return true;
            }
        }
        return false;
    }
}