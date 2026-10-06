package com.example.androidfinalexamapplecation;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidfinalexamapplecation.databinding.SingInBinding;
import com.example.androidfinalexamapplecation.noteapp.MainActivity;


public class SingInActivity extends AppCompatActivity {

    SingInBinding binding;
    MyDataBase dataBase;
    int flag = 0;
    String email;
    String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = SingInBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);

        SharedPreferences preferences = getSharedPreferences("remember",MODE_PRIVATE);
        boolean b = preferences.getBoolean("isRemember",false);
        if (b){
            startActivity(new Intent(SingInActivity.this,HomeActivity.class));
        }
        setContentView(binding.getRoot());
        dataBase = new MyDataBase(this);

        Intent intent = getIntent();
        int userID = intent.getIntExtra("userID",-1);

        binding.tvChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(SingInActivity.this,ChangePasswordActivity.class);
                intent.putExtra("user_id",userID);
                startActivity(intent1);

            }
        });


        binding.singIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 0;


                if (binding.emailIn.getText().toString().isEmpty()){
                    binding.emailIn.setError("You should fill username");
                    flag = 1;
                }
                if (binding.passwordIn.getText().toString().isEmpty()){
                    binding.passwordIn.setError("You should fill password");
                    flag = 1;
                } if (binding.passwordIn.getText().toString().equals("a")) {
                    if (binding.emailIn.getText().toString().equals("a")) {
                        flag = 2;
//                        Intent intent = new Intent(SingInActivity.this, AdminInterfasesActivity.class);
//                        startActivity(intent);
//                        finish();
                    }
                }
                if (flag == 0){
                     email = binding.emailIn.getText().toString();
                     password = binding.passwordIn.getText().toString();
                    if (checkLogin(email,password)){
                        Toast.makeText(SingInActivity.this, "done", Toast.LENGTH_SHORT).show();
                        Intent intentSingIn  = new Intent(SingInActivity.this, MainActivity.class);
                        intentSingIn.putExtra("email",email);
                        intentSingIn.putExtra("password",password);
                        startActivity(intentSingIn);
                        finish();
                      }else
                        Toast.makeText(SingInActivity.this, "Problem", Toast.LENGTH_SHORT).show();

                    }else if(flag == 1){
                    ShowAlertDialgo();
                }else {
                    ShowAlertDialgoUserNotFound();
                }

                    }



        });


        binding.rememberMe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences preferences = getSharedPreferences("remember",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("isRemember",isChecked);
                if (email.isEmpty()){
                    ShowAlertDialgo();
                    if (password.isEmpty()){
                        ShowAlertDialgo();
                    }
                }else if (password.isEmpty()){
                    ShowAlertDialgo();
              } //else
//                    editor.putInt("userId",getUserID(email,password));
//                editor.apply();



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
        builder.setMessage("You should fill both email and password");
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
        builder.setMessage("email OR password are not correct");
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
    private boolean checkLogin(String email , String password){
        Cursor cursor = dataBase.getUsers();
        while (cursor.moveToNext()){
            if (cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_EMAIL)).equals(email)){
                if (cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_PASSWORD)).equals(password)){
                    return true;
                }
                return false;
            }
        }
        return false;
    }



}