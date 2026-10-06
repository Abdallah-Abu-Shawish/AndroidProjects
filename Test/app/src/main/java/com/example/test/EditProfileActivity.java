package com.example.test;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.test.databinding.ActivityEditProfileBinding;

import java.io.IOException;

public class EditProfileActivity extends AppCompatActivity {

    ActivityEditProfileBinding binding;
    MyDataBase dataBase;
    int flag = 0;
    Uri uri;
    Bitmap bitmap;
    RadioButton rb_gender;
    int a = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        binding = ActivityEditProfileBinding.inflate(getLayoutInflater());


        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        a = 0;
        dataBase = new MyDataBase(this);


        if (dataBase.getUsersByUserName(dataBase.getUserTemp()).getGender().equals("Male")){
            binding.editGenderMale.setChecked(true);
        }else if (dataBase.getUsersByUserName(dataBase.getUserTemp()).getGender().equals("Female")){
            binding.editGenderFemale.setChecked(true);

        }

        binding.editProfileName.setText(dataBase.getUsersByUserName(dataBase.getUserTemp()).getName());
        binding.editProfileEmail.setText(dataBase.getUsersByUserName(dataBase.getUserTemp()).getEmail());
        binding.editProfileUsername.setText(dataBase.getUsersByUserName(dataBase.getUserTemp()).getUserName());
        binding.editProfilePassword.setText(dataBase.getUsersByUserName(dataBase.getUserTemp()).getPassword());
        binding.editProfileCountry.setText(dataBase.getUsersByUserName(dataBase.getUserTemp()).getCountry());
        binding.profileImgEdited.setImageBitmap(dataBase.getUsersByUserName(dataBase.getUserTemp()).getImage());







        ActivityResultLauncher<Intent> lancher2 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Intent intent = result.getData();
                        assert intent != null;
                        uri = intent.getData();
                        try {
                            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        binding.profileImgEdited.setImageURI(uri);
                    }
                });





        binding.editImgProfileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a = 1;
                Intent intent2=new Intent();
                intent2.setAction(Intent.ACTION_GET_CONTENT);
                intent2.setType("image/*");
                lancher2.launch(intent2);
            }
        });





            Intent intent = getIntent();
        String usernameA = intent.getStringExtra("sendUserName");
        String passwordA = intent.getStringExtra("sendPassword");

        binding.btnSaveEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                flag = 0;

                if (binding.editProfileName.getText().toString().isEmpty()){
                    binding.editProfileName.setError("You should fill this field");
                    flag = 1;
                }
                if (binding.editProfileEmail.getText().toString().isEmpty()){
                    binding.editProfileEmail.setError("You should fill this field");
                    flag = 1;
                }
                if (binding.editProfileUsername.getText().toString().isEmpty()){
                    binding.editProfileUsername.setError("You should fill this field");
                    flag = 1;
                }
                if (binding.editProfilePassword.getText().toString().isEmpty()){
                    binding.editProfilePassword.setError("You should fill this field");
                    flag = 1;
                }

                if (binding.editProfileCountry.getText().toString().isEmpty()){
                    binding.editProfileCountry.setError("You should fill this field");
                    flag = 1;
                }

                if (binding.editGender.getCheckedRadioButtonId() == -1){
                    flag = 1;
                }


                if (flag == 1){

                    ShowAlertDialgo();

                }else {

                    if (a ==1){

                        MyDataBase  dataBase2 = new MyDataBase(EditProfileActivity.this);
                        Intent profileIntent = getIntent();

//هدول عشان لما يرجع من الاديت يعرض البيانات المعدلة

                        rb_gender = findViewById(binding.editGender.getCheckedRadioButtonId());
                        String username = binding.editProfileUsername.getText().toString();
                        User user = new User(binding.editProfileName.getText().toString(),
                                binding.editProfileEmail.getText().toString(),
                                username,
                                binding.editProfilePassword.getText().toString(),
                                binding.editProfileCountry.getText().toString(),
                                rb_gender.getText().toString(),
                                bitmap
                        );

                        if (dataBase2.updateUser(user,dataBase.getUsersByUserName(dataBase.getUserTemp()).getUserName())){
                            dataBase.updateUserTemp(user.getUserName(),user.getPassword());
                            Toast.makeText(EditProfileActivity.this, "Account updated", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(EditProfileActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }


                        // binding.profileName.setText(dataBase.getUsersByUserName(dataBase.getUserTemp()).getName());


                }else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(EditProfileActivity.this);
                        builder.setTitle("Warning").setMessage("You should select an image for your profile");
                        builder.show();
                    }
            }
            }
        });



    }

    @SuppressLint("Range")
    private User getUser(String userName , String password){
        Cursor cursor = dataBase.getUsers();
        User user = new User();
        while (cursor.moveToNext()){
            if (cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_USERNAME)).equals(userName)){
                if (cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_PASSWORD)).equals(password)){
                    user.setUserName(cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_USERNAME)));
                    user.setPassword(cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_PASSWORD)));
                    user.setName(cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_NAME)));
                    user.setEmail(cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_EMAIL)));
                    user.setCountry(cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_COUNTRY)));
                    user.setGender(cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_GENDER)));
                }

            }
        }
        return user;
    }
    @SuppressLint("Range")
    private User getUserByID(int username){
        Cursor cursor = dataBase.getUsers();
        User user = new User();
        while (cursor.moveToNext()){
            if (cursor.getInt(cursor.getColumnIndex(MyDataBase.COLUMN_USERNAME)) == username){
                    user.setUserName(cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_USERNAME)));
                    user.setPassword(cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_PASSWORD)));
                    user.setName(cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_NAME)));
                    user.setEmail(cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_EMAIL)));
                    user.setCountry(cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_COUNTRY)));
                    user.setGender(cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_GENDER)));
                }

            }

        return user;
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

//    @SuppressLint("Range")
//    private User getUserFromShow(){
//        Cursor cursor = dataBase.getUsers();
//        Cursor cursor1 = dataBase.getShowUsers();
//        User user = new User();
//        while (cursor1.moveToNext()){
//            while (cursor.moveToNext()){
//                if (cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_USERNAME)).equals
//                        (cursor1.getString(cursor1.getColumnIndex(MyDataBase.COLUMN_USERNAME_SHOW)))&&
//                        cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_PASSWORD)).equals
//                                (cursor1.getString(cursor1.getColumnIndex(MyDataBase.COLUMN_PASSWORD_SHOW )))){
//                    user.setId(cursor.getInt(cursor.getColumnIndex(MyDataBase.COLUMN_ID)));
//                    user.setUserName(cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_USERNAME)));
//                    user.setPassword(cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_PASSWORD)));
//                    user.setName(cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_NAME)));
//                    user.setEmail(cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_EMAIL)));
//                    user.setCountry(cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_COUNTRY)));
//                    user.setGender(cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_GENDER)));
//                    byte[] bytes = cursor.getBlob(cursor.getColumnIndex(MyDataBase.COLUMN_IMAGE));
//                    user.setImage(getBitmap(bytes));
//                }
//
//            }
//        }
//
//        return user;
//    }

    private Bitmap getBitmap(byte[] bytes){
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }


}