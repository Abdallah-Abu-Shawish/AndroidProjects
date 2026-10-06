package com.example.roomdatabasetest;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.roomdatabasetest.databinding.ActivitySignInBinding;

public class SignInActivity extends AppCompatActivity {
    ActivitySignInBinding binding;
    AppRoomDataBase dataBase;
    boolean b = true;
    boolean flag = true;
    String username = "";
    String password = "";
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        SharedPreferences preferences = getSharedPreferences("remember",MODE_PRIVATE);
        boolean c = preferences.getBoolean("isRemember",false);
        if(c){
            startActivity(new Intent(SignInActivity.this,WelcomeActivity.class));
            finish();
        }
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dataBase = AppRoomDataBase.getDatabase(this);
        Intent intent = getIntent();
        long id = intent.getLongExtra("userId",-1);

        binding.singIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 username = binding.usernameIn.getText().toString().trim();
                 password = binding.passwordIn.getText().toString().trim();
                if (username.isEmpty() || password.isEmpty()){
                    showEmptyFieldAlertDialog();
                    flag = false;
                }
                if (username.length()<5){
                    binding.usernameIn.setError("Your username should be at least 5 characters long");
                    flag = false;
                }
                if (username.length()>10){
                    binding.usernameIn.setError("Your username should be at most 10 characters long");
                    flag = false;
                }
//                if (password.isEmpty()){
//                    binding.passwordIn.setError("You should write your password");
//                    flag = false;
//                }
                if (password.length()<5){
                    binding.passwordIn.setError("Your password should be at least 5 characters long");
                    flag = false;
                }

                if (flag){
                    User user = dataBase.userDao().getUser(username,password);
                   if (user == null){
                       showAlertDialog();
                   }else {
                       UserIn userIn = dataBase.userInDao().getAllUsersIn();
                       if (userIn == null){
                           long idUserIn = dataBase.userInDao().insertUserIn(new UserIn(user.getId(),user.getUsername()));
                       }else {
                           UserIn updateUserIn = new UserIn(user.getId(),user.getUsername());
                           updateUserIn.set_id(userIn.get_id());
                           dataBase.userInDao().updateUserIn(updateUserIn);
                       }
                       Intent intent1 = new Intent(SignInActivity.this, WelcomeActivity.class);
                       startActivity(intent1);
                       finish();
                   }
                }else {
                    flag = true;
                }
            }
        });

        binding.tvSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInActivity.this, RegisterActivity.class));
                finish();
            }
        });

        binding.rememberMe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences preferences = getSharedPreferences("remember",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("isRemember",isChecked);
                editor.apply();

            }
        });


        binding.passwordIn.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    Drawable drawableEnd = binding.passwordIn.getCompoundDrawables()[2];
                    if (drawableEnd != null && motionEvent.getRawX() >= (binding.passwordIn.getRight() - drawableEnd.getBounds().width())){
                        if (b){
                            binding.passwordIn.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                            binding.passwordIn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_lock_24,0,R.drawable.ic_baseline_remove_red_eye_24,0);
                            b = false;
                        }else {
                            binding.passwordIn.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            binding.passwordIn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_lock_24,0,R.drawable.baseline_visibility_off_24,0);
                            b = true;
                        }
                        binding.passwordIn.setSelection(binding.passwordIn.getText().length());
                        return true;
                    }
                }
                binding.passwordIn.setSelection(binding.passwordIn.getText().length());
                return false;
            }
        });



    }

    public void showAlertDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Check Again!!!!");
        builder.setMessage("There is no such an account");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();


    }

    public void showEmptyFieldAlertDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Check The Fields Again!!!!");
        builder.setMessage("There is an empty field you should fill in all the fields to create the account");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();


    }


    public void showCustomAlertDialog(String title, String message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();


    }
}