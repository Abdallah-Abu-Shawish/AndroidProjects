package com.example.androidfinalexamapplecation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.androidfinalexamapplecation.databinding.ActivityChangePasswordBinding;

public class ChangePasswordActivity extends AppCompatActivity {

    ActivityChangePasswordBinding binding;
    int flag = 0;
    String newPassword="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityChangePasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        flag = 0;
        MyDataBase dataBase = new MyDataBase(this);


        Intent intent = getIntent();
        binding.edAldPassword.setText(dataBase.getUserByID(intent.getIntExtra("user_id",-1)).getPassword());
        if (binding.edNewPassword.getText().toString().isEmpty()){
            binding.edNewPassword.setError("You should fill password");
            flag = 1;
        }if (binding.edNewPassword.getText().toString().length()<8){
            binding.edNewPassword.setError("invalid password");
            flag = 2;
        }

        if (flag == 1 ){
            ShowAlertDialgo2();

        }else if (flag == 2){
            ShowAlertDialgo4();
        }else {
            newPassword = binding.edNewPassword.getText().toString();
        }


        binding.btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataBase.updateUserPassword(newPassword,intent.getIntExtra("user_id",-1))){
                    Toast.makeText(ChangePasswordActivity.this, "Password updated", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(ChangePasswordActivity.this, "Password is not updated", Toast.LENGTH_SHORT).show();
                }
            }
        });




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
    private void ShowAlertDialgo2(){


        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Warning");
        builder.setMessage("Password is empty");
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