package com.example.test;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import com.example.test.databinding.ActivityAddWatchesBinding;

import java.io.IOException;

public class AddWatchesActivity extends AppCompatActivity {

    ActivityAddWatchesBinding binding;
    MyDataBase dataBase;
    int flag =0;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityAddWatchesBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        dataBase = new MyDataBase(this);


        binding.btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                flag = 0;

                if (binding.addProductName.getText().toString().isEmpty()){
                    binding.addProductName.setError("You should fill this field");
                    flag = 1;
                }
                if (binding.addProductMark.getText().toString().isEmpty()){
                    binding.addProductMark.setError("You should fill this field");
                    flag = 1;
                }
                if (binding.addProductPrice.getText().toString().isEmpty()){
                    binding.addProductPrice.setError("You should fill this field");
                    flag = 1;
                }
                if (binding.addProductQuntity.getText().toString().isEmpty()){
                    binding.addProductQuntity.setError("You should fill this field");
                    flag = 1;
                }if (binding.addProductDescription.getText().toString().isEmpty()){
                    binding.addProductDescription.setError("You should fill this field");
                    flag = 1;
                }
                if (binding.imgProduct.getDrawable() == null){
                    flag = 2;
                }

                if (flag == 1){
                    ShowAlertDialgo();
                }else if (flag ==2 ){
                    ShowAlertDialgo2();
                }else {
                    String name = binding.addProductName.getText().toString();
                    String marka = binding.addProductMark.getText().toString();
                    int price = Integer.parseInt(binding.addProductPrice.getText().toString());
                    int quntity = Integer.parseInt(binding.addProductQuntity.getText().toString());
                    String description = binding.addProductDescription.getText().toString();
                    Product product = new Product(name,marka,price,quntity,description,bitmap);
                    if (dataBase.insertProduct(product)){
                        Intent intent = new Intent(AddWatchesActivity.this,ViewAllProductActivity.class);
                        startActivity(intent);
                        finish();
                    }else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(AddWatchesActivity.this);
                        builder.setTitle("Warning").setMessage("The product did not add to the DataBase");
                        builder.show();
                    }
                }







            }
        });


        ActivityResultLauncher<Intent> lancher2=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Intent intent = result.getData();
                        Uri img = intent.getData();
                        try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), img);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                        binding.imgProduct.setImageURI(img);
                    }
                });
        binding.btnSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2=new Intent();
                intent2.setAction(Intent.ACTION_GET_CONTENT);
                intent2.setType("image/*");
                lancher2.launch(intent2);
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
        builder.setMessage("You should select an image for the product");
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