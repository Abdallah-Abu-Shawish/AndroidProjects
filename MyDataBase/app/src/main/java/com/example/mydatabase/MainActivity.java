package com.example.mydatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.example.mydatabase.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private MyDataBase dataBase;
    int flag = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        dataBase = new MyDataBase(this);
        binding.singUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RadioButton rb_gender;





                flag = 0;

                if (binding.enterNameUp.getText().toString().isEmpty()){
                    binding.enterNameUp.setError("You should fill this field");
                    flag = 1;
                }
                if (binding.enterEmailUp.getText().toString().isEmpty()){
                    binding.enterEmailUp.setError("You should fill this field");
                    flag = 1;
                }
                if (binding.phoneUp.getText().toString().isEmpty()){
                    binding.phoneUp.setError("You should fill this field");
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
                }




                if (flag == 1){

                    ShowAlertDialgo();

                }else if (flag == 2){
                    ShowAlertDialgo2();
                } else {
                    rb_gender = findViewById(binding.enterGenderUp.getCheckedRadioButtonId());
                    String name = binding.enterNameUp.getText().toString();
                    String email = binding.enterEmailUp.getText().toString();
                    String phone = binding.phoneUp.getText().toString();
                    String password = binding.passwordUp.getText().toString();
                    String country = binding.enterCountryUp.getText().toString();
                    String gender = rb_gender.getText().toString();
                    User user = new User(name,email,phone,password,country,gender);
                   if (dataBase.insertUser(user)){
                       Intent intentSingUp = new Intent(MainActivity.this,SingInActivity.class);
                       startActivity(intentSingUp);
                       finish();
                   }else {
                       AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                       builder.setTitle("Warning").setMessage("The data did not add to the DataBase");
                       builder.show();
                   }

                }


            }
        });

        binding.tvSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentFromSingUp = new Intent(MainActivity.this,SingInActivity.class);
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

}