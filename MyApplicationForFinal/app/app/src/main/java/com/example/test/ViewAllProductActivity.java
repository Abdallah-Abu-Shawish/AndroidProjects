package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.test.databinding.ActivitySearchBinding;
import com.example.test.databinding.ActivityViewAllProductBinding;
import com.example.test.recicleview.RecicleAdabterForSearchedProducts;

import java.util.ArrayList;

public class ViewAllProductActivity extends AppCompatActivity implements RecicleAdabterForSearchedProducts.ClickItem {

    ActivityViewAllProductBinding binding;
    Context context = ViewAllProductActivity.this;
    MyDataBase dataBase;
    RecicleAdabterForSearchedProducts adabter;
    RecicleAdabterForSearchedProducts adabterForAllProducts;
    ArrayList<Product> allProducts;
    ArrayList<Product> searchedProducts;
    int post;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityViewAllProductBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        dataBase = new MyDataBase(this);
        allProducts = new ArrayList<>();

        binding.searchForProductImgForAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.editTextSearchForAdmin.getText().toString().isEmpty()){
                    ShowAlertDialgo();
                }else {
                    searchedProducts = dataBase.searchProduct(binding.editTextSearchForAdmin.getText().toString());
                    adabter = new RecicleAdabterForSearchedProducts(context,searchedProducts);
                    binding.searchViewResultForAdmin.setAdapter(adabter);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(context,2);
                    binding.searchViewResultForAdmin.setLayoutManager(gridLayoutManager);
                }

            }
        });

        allProducts = dataBase.getAllProducts();
        adabterForAllProducts = new RecicleAdabterForSearchedProducts(context, allProducts, new RecicleAdabterForSearchedProducts.ClickItem() {
            @Override
            public void clickOnItem(int position) {

                MyDataBase myDataBase=new MyDataBase(ViewAllProductActivity.this);
                Intent intent=new Intent(ViewAllProductActivity.this,EditProductsActivity.class);
                intent.putExtra("gg",myDataBase.getAllProducts().get(position).getId());
                startActivity(intent);
            }

            @Override
            public void LongClick(int postion) {
                post=postion;
                ShowAlertDialgoForDelete();
            }
        });
        binding.viewAllProductAdmin.setAdapter(adabterForAllProducts);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,2);
        binding.viewAllProductAdmin.setLayoutManager(gridLayoutManager);



    }

    private void ShowAlertDialgo(){


        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Warning");
        builder.setMessage("You should fill Search field");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }

        });

        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();


    }

    private void ShowAlertDialgoForDelete(){


        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Warning");
        builder.setMessage("Are You sure you want to delete this product?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dataBase.deleteProduct(dataBase.getAllProducts().get(post).getId());

                allProducts = dataBase.getAllProducts();
                adabterForAllProducts = new RecicleAdabterForSearchedProducts(context, allProducts, new RecicleAdabterForSearchedProducts.ClickItem() {
                    @Override
                    public void clickOnItem(int position) {

                        MyDataBase myDataBase=new MyDataBase(ViewAllProductActivity.this);
                        Intent intent=new Intent(ViewAllProductActivity.this,EditProductsActivity.class);
                        intent.putExtra("gg",myDataBase.getAllProducts().get(position).getId());
                        startActivity(intent);
                    }

                    @Override
                    public void LongClick(int postion) {
                        post=postion;
                        ShowAlertDialgoForDelete();
                    }
                });
                binding.viewAllProductAdmin.setAdapter(adabterForAllProducts);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context,2);
                binding.viewAllProductAdmin.setLayoutManager(gridLayoutManager);



            }


        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();


    }






    @Override
    public void clickOnItem(int position) {


    }

    @Override
    public void LongClick(int postion) {

    }



}