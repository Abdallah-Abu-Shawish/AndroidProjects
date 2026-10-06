package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.test.databinding.ActivityPdoductDetailsBinding;

import java.util.ArrayList;

public class PdoductDetailsActivity extends AppCompatActivity {

    ActivityPdoductDetailsBinding binding;
    int counter = 0;
    ArrayList<String> productList = new ArrayList<>();
    MyDataBase dataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityPdoductDetailsBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        MyDataBase myDataBase=new MyDataBase(PdoductDetailsActivity.this);
        Intent detailsIntent = getIntent();
        int id= detailsIntent.getIntExtra("id",0);

        binding.tvProdutNameDetail.setText(myDataBase.getProductByID(id).getName());
        binding.tvProdutTypeDetail.setText(myDataBase.getProductByID(id).getType());
        binding.tvDetailDescriptionText.setText(myDataBase.getProductByID(id).getDescription());
        binding.tvProdutPriceDetail.setText(myDataBase.getProductByID(id).getPrice()+"$");
        binding.imageProductShowDetails.setImageBitmap(myDataBase.getProductByID(id).getProductImage());







        binding.imgPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                binding.tvCounterForBuying.setText(counter+"");
            }
        });
        binding.imgMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (counter < 2){
                    AlertDialog.Builder builder = new AlertDialog.Builder(PdoductDetailsActivity.this);
                    builder.setTitle("Warning").setMessage("There is no Product quntity less than 1 (negative) ");
                    builder.show();
                }else {
                    counter--;
                    binding.tvCounterForBuying.setText(counter+"");
                }

            }
        });


        binding.btnBuyProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (Integer.parseInt(binding.tvCounterForBuying.getText().toString())>myDataBase.getProductByID(id).getQuntity()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(PdoductDetailsActivity.this);
                    builder.setTitle("Warning").setMessage("The Product quntity is less than you ask to buy ");
                    builder.show();
                }else {
                    Intent intent = new Intent(PdoductDetailsActivity.this,ReceiptActivity.class);
                    intent.putExtra("price",myDataBase.getProductByID(id).getPrice());
                    intent.putExtra("quntity",myDataBase.getProductByID(id).getQuntity());
                    startActivity(intent);
                    finish();

                }
            }
        });

        binding.btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Product2 product2=new Product2(binding.tvProdutNameDetail.getText().toString(),binding.tvProdutTypeDetail.getText().toString()
                ,myDataBase.getProductByID(id).getPrice(),Integer.parseInt( binding.tvCounterForBuying.getText().toString()),
                        myDataBase.getUserTemp(),myDataBase.getProductByID(id).getProductImage());


                myDataBase.insertProductCart(product2);




                Intent intent = new Intent(PdoductDetailsActivity.this,CartActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
                finish();

            }
        });


    }
}