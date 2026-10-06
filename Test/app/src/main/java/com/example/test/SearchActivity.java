package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.example.test.databinding.ActivitySearchBinding;
import com.example.test.recicleview.RecicleAdabterForSearchedProducts;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements RecicleAdabterForSearchedProducts.ClickItem {

    Context context = SearchActivity.this;
    ActivitySearchBinding binding;
    MyDataBase dataBase;
    RecicleAdabterForSearchedProducts adabter;
    ArrayList<Product> products;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        dataBase = new MyDataBase(this);
        products = new ArrayList<>();
        binding.searchForProductImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.editTextSearch.getText().toString().isEmpty()){
                    ShowAlertDialgo();
                }else {
                    products = dataBase.searchProduct(binding.editTextSearch.getText().toString());
                    adabter = new RecicleAdabterForSearchedProducts(context,products);
                    binding.searchViewResult.setAdapter(adabter);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(context,2);
                    binding.searchViewResult.setLayoutManager(gridLayoutManager);
                }

            }
        });

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

    @Override
    public void clickOnItem(int position) {

    }

    @Override
    public void LongClick(int postion) {

    }
}