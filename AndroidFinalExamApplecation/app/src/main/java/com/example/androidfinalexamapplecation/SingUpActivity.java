package com.example.androidfinalexamapplecation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.RadioButton;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;


import com.example.androidfinalexamapplecation.databinding.SingUpBinding;

import java.io.IOException;

public class SingUpActivity extends AppCompatActivity {

    SingUpBinding binding;
    int flag = 0;
    int a = 0;
    Bitmap bitmap;
    Uri uri;
    MyDataBase dataBase=new MyDataBase(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = SingUpBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        a = 0;
        ActivityResultLauncher<Intent> lancher2 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Intent intent = result.getData();
                        assert intent != null;
                        uri = intent.getData();
                        try {
                            bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), uri);
                        } catch (IOException e) {
                            throw  new RuntimeException(e);
                        }

                    }
                });

        binding.selectImgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    a = 1;
                    Intent intent2=new Intent();
                    intent2.setAction(Intent.ACTION_GET_CONTENT);
                    intent2.setType("image/*");
                    lancher2.launch(intent2);
            }
        });


        binding.singUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton rb_gender;

                flag = 0;

                if (binding.enterNameUp.getText().toString().isEmpty()){
                    binding.enterNameUp.setError("You should fill this field");
                    flag = 1;
                }
                if (binding.emailUp.getText().toString().isEmpty()){
                    binding.emailUp.setError("You should fill this field");
                    flag = 1;
                }
                if (binding.passwordUp.getText().toString().isEmpty()){
                    binding.passwordUp.setError("You should fill this field");
                    flag = 1;
                }
                if (binding.confirmUp.getText().toString().isEmpty()){
                    binding.confirmUp.setError("You should fill this field");
                    flag = 1;
                }
                if (binding.enterCountryUp.getText().toString().isEmpty()){
                    binding.enterCountryUp.setError("You should fill this field");
                    flag = 1;
                }
                if (!binding.passwordUp.getText().toString().equals(binding.confirmUp.getText().toString())){
                    binding.confirmUp.setError("Password is not matching");
                    flag = 2;
                }
                if (binding.enterGenderUp.getCheckedRadioButtonId() == -1){
                    flag = 1;
                }if (!binding.emailUp.getText().toString().endsWith("@smail.ucas.edu.ps")){
                    flag = 3;
                }if (binding.passwordUp.getText().toString().length()<8){
                    flag = 4;
                }

                if (flag == 1){

                    ShowAlertDialgo();

                }else if (flag == 2){
                    ShowAlertDialgo2();
                }else if (flag == 3) {
                    ShowAlertDialgo3();
                }  else if (flag == 4) {
                    ShowAlertDialgo4();
                }

                else {
                    if (a == 1) {
                        rb_gender = findViewById(binding.enterGenderUp.getCheckedRadioButtonId());
                        String name = binding.enterNameUp.getText().toString();
                        String email = binding.emailUp.getText().toString();
                        String password = binding.passwordUp.getText().toString();
                        String country = binding.enterCountryUp.getText().toString();
                        String gender = rb_gender.getText().toString();

                        User user = new User(name, email, password, country, gender,bitmap);
                        if (dataBase.insertUser(user)) {
                            Intent intentSingUp = new Intent(SingUpActivity.this, SingInActivity.class);
                            //intentSingUp.putExtra("userID",dataBase.getUserID(email,password));
                            startActivity(intentSingUp);
                            finish();
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(SingUpActivity.this);
                            builder.setTitle("Warning").setMessage("The data did not add to the DataBase");
                            builder.show();
                        }

                    }else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(SingUpActivity.this);
                        builder.setTitle("Warning").setMessage("You should select an image for your profile");
                        builder.show();
                    }
                }

            }
        });

        binding.tvSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentFromSingUp = new Intent(SingUpActivity.this,SingInActivity.class);
                startActivity(intentFromSingUp);
            }
        });


    }

    private void ShowAlertDialgo(){


        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Warning");
        builder.setMessage("You should fill all fields");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }

        });

        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();


    }

    private void ShowAlertDialgo2(){


        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Warning");
        builder.setMessage("Password is not matching");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }

        });

        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();


    }

    private void ShowAlertDialgo3(){


        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Warning");
        builder.setMessage("email is not valid");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }

        });

        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();


    }

    private void ShowAlertDialgo4(){


        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Warning");
        builder.setMessage("Password is not valid");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }

        });

        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();


    }

}