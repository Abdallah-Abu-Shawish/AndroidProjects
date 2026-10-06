package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.test.databinding.ActivityCartBinding;
import com.example.test.recicleview.RecicleAdabterForSearchedProducts;
import com.example.test.recicleview.RecicleAdabterForSearchedProducts2;
import com.google.android.material.navigation.NavigationBarView;

public class CartActivity extends AppCompatActivity {

    RecicleAdabterForSearchedProducts2 adabterProducts;

    ActivityCartBinding binding;
    RecicleAdabterForSearchedProducts productsCart;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        Intent intent=getIntent();
        int id=intent.getIntExtra("id",0);
        MyDataBase myDataBase=new MyDataBase(CartActivity.this);


        adabterProducts = new RecicleAdabterForSearchedProducts2(this, myDataBase.getAllProductsCART(myDataBase.getUserTemp()), new RecicleAdabterForSearchedProducts2.ClickItem() {
            @Override
            public void clickOnItem(int position) {

            }

            @Override
            public void LongClick(int postion) {

                if (myDataBase.deleteProductCart(id,myDataBase.getUserTemp())){
                    Toast.makeText(CartActivity.this, "Dewleted", Toast.LENGTH_SHORT).show();
                    finish();

                }else {
                    Toast.makeText(CartActivity.this, "no", Toast.LENGTH_SHORT).show();
                }


            }
        });

                binding.recycleCart.setAdapter(adabterProducts);
        //  LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //  LinearLayoutManager linearLayoutManagerH = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        binding.recycleCart.setLayoutManager(gridLayoutManager);




        binding.tabBar.setSelectedItemId(R.id.item_home);

        binding.tabBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){


                    case R.id.item_home:
                        Intent intent3 = new Intent(CartActivity.this , MainActivityForHome.class);
                        startActivity(intent3);
                        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_in_left);
                        return true;


                    case R.id.item_cart :
                        return true;



                    case R.id.item_account:

                        Intent intent4 = new Intent(CartActivity.this , ProfileActivity.class);
                        startActivity(intent4);
                        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_in_left);
                        return true;

                }
                return false;

            }
        });










    }
}