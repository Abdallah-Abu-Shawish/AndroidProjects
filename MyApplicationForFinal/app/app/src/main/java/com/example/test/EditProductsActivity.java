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
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.example.test.databinding.ActivityEditProductsBinding;

import java.io.IOException;

public class EditProductsActivity extends AppCompatActivity {

    int flag = 0;
    ActivityEditProductsBinding binding;
    MyDataBase dataBase;
    int a =0;
    Uri uri;
    Bitmap bitmap;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityEditProductsBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        dataBase = new MyDataBase(this);
        Intent intent3=getIntent();
        String id= String.valueOf(intent3.getIntExtra("gg",0));

        binding.editProductNewName.setText(dataBase.getProductByID(intent3.getIntExtra("gg",0)).getName());
        binding.editProductNewMark.setText(dataBase.getProductByID(intent3.getIntExtra("gg",0)).getType());
        binding.editProductNewDescription.setText(dataBase.getProductByID(intent3.getIntExtra("gg",0)).getDescription());
        binding.editProductNewPrice.setText(dataBase.getProductByID(intent3.getIntExtra("gg",0)).getPrice()+"");
        binding.editProductNewQuntity.setText(dataBase.getProductByID(intent3.getIntExtra("gg",0)).getQuntity()+"");
        binding.imgNewProduct.setImageBitmap(dataBase.getProductByID(intent3.getIntExtra("gg",0)).getProductImage());


        a = 0;


        Intent intent = getIntent();
        String pName = intent.getStringExtra("pName");

        binding.btnEditProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



        flag = 0;

        if (binding.editProductNewName.getText().toString().isEmpty()){
            binding.editProductNewName.setError("You should fill this field");
            flag = 1;
        }
        if (binding.editProductNewMark.getText().toString().isEmpty()){
            binding.editProductNewMark.setError("You should fill this field");
            flag = 1;
        }
        if (binding.editProductNewPrice.getText().toString().isEmpty()){
            binding.editProductNewPrice.setError("You should fill this field");
            flag = 1;
        }
        if (binding.editProductNewQuntity.getText().toString().isEmpty()) {
            binding.editProductNewQuntity.setError("You should fill this field");
            flag = 1;
        }if (binding.editProductNewDescription.getText().toString().isEmpty()){
            binding.editProductNewDescription.setError("You should fill this field");
            flag = 1;
        } if (binding.imgNewProduct.getDrawable() == null){
        flag = 2;
       }

        if (flag == 1){
            ShowAlertDialgo();
        }else if (flag ==2 ){
            ShowAlertDialgo2();
        }else {
            if (a == 1) {


                Product product = new Product(binding.editProductNewName.getText().toString(), binding.editProductNewMark.getText().toString()
                        , Integer.parseInt(binding.editProductNewPrice.getText().toString()), Integer.parseInt(binding.editProductNewQuntity.getText().toString()),
                        binding.editProductNewDescription.getText().toString(),bitmap);


                if (dataBase.updateProduct(product,intent3.getIntExtra("gg",0))) {
                    ShowAlertDialgo3();
                    finish();
                } else {
                    ShowAlertDialgo4();
                }
            }else {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditProductsActivity.this);
                builder.setTitle("Warning").setMessage("You should select an image for the product");
                builder.show();
            }
        }

            }
        });

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
                        binding.imgNewProduct.setImageURI(uri);
                    }
                });





        binding.editBtnSelectNewImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a = 1;
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

    private void ShowAlertDialgo3(){


        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Be Cearful");
        builder.setMessage("You update the product successfully");
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
        builder.setTitle("Be Cearful");
        builder.setMessage("The update for the product failed");
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
    private Product getProduct(String name){
        Cursor cursor = dataBase.getUsers();
        Product product = new Product();
        while (cursor.moveToNext()){
            if (cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_NAME_PRODUCT)).equals(name)){
                product.setId(cursor.getInt(cursor.getColumnIndex(MyDataBase.COLUMN_ID_PRODUCT)));
                product.setName(cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_NAME_PRODUCT)));
                product.setType(cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_TYPE_PRODUCT)));
                product.setDescription(cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_DESCRIPTION)));
                product.setPrice(Integer.parseInt(cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_PRICE))));
                product.setQuntity(Integer.parseInt(cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_QUNTITY))));
            }

        }

        return product;
    }

    }