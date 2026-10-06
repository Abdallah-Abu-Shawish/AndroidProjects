package com.example.roomdatabasetest;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.roomdatabasetest.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;
    boolean a = true;
    boolean flag = true;
    AppRoomDataBase dataBase;
    Uri image;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        SharedPreferences preferences = getSharedPreferences("remember",MODE_PRIVATE);
        boolean b = preferences.getBoolean("isRemember",false);
        if(b){
            startActivity(new Intent(RegisterActivity.this,WelcomeActivity.class));
            finish();
        }
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dataBase = AppRoomDataBase.getDatabase(this);

        ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                image = result.getData().getData();
                binding.registerImage.setImageURI(image);
            }
        });

        binding.selectImgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION |
                                Intent.FLAG_GRANT_WRITE_URI_PERMISSION |
                                Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
                launcher.launch(intent);
            }
        });

        binding.singUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.enterNameUp.getText().toString().trim();
                String email = binding.enterEmailUp.getText().toString().trim();
                String username = binding.usernameUp.getText().toString().trim();
                String password = binding.passwordUp.getText().toString().trim();
                String confirm = binding.confirmUp.getText().toString().trim();
                if (name.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty() || confirm.isEmpty()){
                    showEmptyFieldAlertDialog();
                    flag = false;
                }

//                if (){
//                    binding.enterNameUp.setError("You should write your name");
//                    flag = false;
//                }
                 if (name.length()<3){
                    binding.enterNameUp.setError("Your name should be at least 3 characters long");
                    flag = false;
                }
//                 if (){
//                    binding.usernameUp.setError("You should write your username");
//                    flag = false;
//                }
                 if (username.length()<5){
                    binding.usernameUp.setError("Your username should be at least 5 characters long");
                    flag = false;
                }
                if (username.length()>10){
                    binding.usernameUp.setError("Your username should be at most 10 characters long");
                    flag = false;
                }

//                if (){
//                    binding.enterEmailUp.setError("You should write your email");
//                    flag = false;
//                }
                 if (!email.endsWith("@gmail.com")){
                    binding.enterEmailUp.setError("You should write a valid email");
                    flag = false;
                }
//                 if (){
//                    binding.passwordUp.setError("You should write your password");
//                    flag = false;
//                }
                 if (password.length()<5){
                    binding.passwordUp.setError("Your password should be at least 5 characters long");
                    flag = false;
                }
//                 if (){
//                    binding.confirmUp.setError("You should confirm your password");
//                    flag = false;
//                }
                 if (!password.equals(confirm)){
                    binding.confirmUp.setError("This field should match your password");
                    flag = false;
                }
                 if (dataBase.userDao().getUserByUsername(username) != null){
                    binding.usernameUp.setError("This username is already taken");
                    flag = false;
                }

                if (flag){
//                    getContentResolver().takePersistableUriPermission(
//                            image,
//                            Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION
//                    );
                  long userId = dataBase.userDao().insertUser(new User(name,image,email,username,password));
                  Intent intent = new Intent(RegisterActivity.this, SignInActivity.class);
                  intent.putExtra("userId",userId);
                  startActivity(intent);
                  finish();
                }else {
                    flag = true;
                }

            }
        });


        binding.tvSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });


        binding.passwordUp.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    Drawable drawableEnd = binding.passwordUp.getCompoundDrawables()[2];
                    if (drawableEnd != null && motionEvent.getRawX() >= (binding.passwordUp.getRight() - drawableEnd.getBounds().width())){
                        if (a){
                            binding.passwordUp.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                            binding.passwordUp.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_lock_24,0,R.drawable.ic_baseline_remove_red_eye_24,0);
                            a = false;
                        }else {
                            binding.passwordUp.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            binding.passwordUp.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_lock_24,0,R.drawable.baseline_visibility_off_24,0);
                            a = true;
                        }
                        binding.passwordUp.setSelection(binding.passwordUp.getText().length());
                        return true;
                    }
                }
                return false;
            }
        });

        binding.confirmUp.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    Drawable drawableEnd = binding.confirmUp.getCompoundDrawables()[2];
                    if (drawableEnd != null && motionEvent.getRawX() >= (binding.confirmUp.getRight() - drawableEnd.getBounds().width())){
                        if (a){
                            binding.confirmUp.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                            binding.confirmUp.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_lock_24,0,R.drawable.ic_baseline_remove_red_eye_24,0);
                            a = false;
                        }else {
                            binding.confirmUp.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            binding.confirmUp.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_lock_24,0,R.drawable.baseline_visibility_off_24,0);
                            a = true;
                        }
                        binding.confirmUp.setSelection(binding.confirmUp.getText().length());
                        return true;
                    }
                }
                return false;
            }
        });


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

}