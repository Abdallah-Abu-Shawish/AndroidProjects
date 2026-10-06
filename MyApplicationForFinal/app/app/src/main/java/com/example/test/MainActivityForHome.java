package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.test.databinding.ActivityMainBinding;
import com.example.test.databinding.HOfferWatchesBinding;
import com.example.test.recicleview.Game;
import com.example.test.recicleview.Game2;
import com.example.test.recicleview.RecicleAdabter;
import com.example.test.recicleview.RecicleAdabterForSearchedProducts;
import com.example.test.recicleview.RecicleAdabterH;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class MainActivityForHome extends AppCompatActivity implements RecicleAdabter.ClickItem,RecicleAdabterH.ClickOffer {

    ActivityMainBinding binding;
    HOfferWatchesBinding binding2;
    RecicleAdabter adabter;
    private ArrayList<Game> games;
    RecicleAdabterForSearchedProducts adabterProducts;
    RecicleAdabterH adabter2;
    private ArrayList<Game2> offers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        binding2 = HOfferWatchesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intentToProfile = getIntent();
        String username = intentToProfile.getStringExtra("username");
        String password = intentToProfile.getStringExtra("password");
        binding.tabBar.setSelectedItemId(R.id.item_home);

        MyDataBase myDataBase=new MyDataBase(MainActivityForHome.this);
        binding.tabBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){


                    case R.id.item_home:
                        return true;


                    case R.id.item_cart :
                        Intent intent3 = new Intent(MainActivityForHome.this , CartActivity.class);
                        startActivity(intent3);
                        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_in_left);
                        return true;



                    case R.id.item_account:

                        Intent intent4 = new Intent(MainActivityForHome.this , ProfileActivity.class);

                        intent4.putExtra("profileUsername",username);
                        intent4.putExtra("profilePassword",password);
                        startActivity(intent4);
                        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_in_left);
                        return true;

                }
                return false;

            }
        });

        binding.searchProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityForHome.this,SearchActivity.class);
                startActivity(intent);
            }
        });


            offers = new ArrayList<>();
            offers.add(new Game2("%20off","Apple","Watch", R.drawable.apple_watch1));
            offers.add(new Game2("%30off","Apple","Watch",R.drawable.apple_watch2));
            offers.add(new Game2("%35off","Apple","Watch",R.drawable.apple_watch3));

            adabter2 = new RecicleAdabterH(this,offers,this::clickOnOffer);
            binding.HOffers.setAdapter(adabter2);
            //  LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            LinearLayoutManager linearLayoutManagerH = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
            //   GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
            binding.HOffers.setLayoutManager(linearLayoutManagerH);

        games = new ArrayList<>();
        games.add(new Game("Sammsung Watch", "Gxt46","100", R.drawable.apple_watch1_card1));
        games.add(new Game("Rolex watch","PgXc45" ,"1000", R.drawable.rolex_watch1));
        games.add(new Game("Apple Watch","CVb9u" ,"140", R.drawable.apple_watch6));
        games.add(new Game("Rolex Watch","Trhge234" ,"2500", R.drawable.rolex_watch4));
        games.add(new Game("Casio Watch", "Caci63t","75", R.drawable.casio_watch1));
        games.add(new Game("Sammsung Watch", "KGui037","450", R.drawable.samsung_watch11));



        adabterProducts = new RecicleAdabterForSearchedProducts(this, myDataBase.getAllProducts(), new RecicleAdabterForSearchedProducts.ClickItem() {
            @Override
            public void clickOnItem(int position) {
                Intent intentToDetails = new Intent(MainActivityForHome.this , PdoductDetailsActivity.class);
                intentToDetails.putExtra("id",myDataBase.getAllProducts().get(position).getId());
                startActivity(intentToDetails);
            }

            @Override
            public void LongClick(int postion) {

            }
        });
        binding.watchesList.setAdapter(adabterProducts);
        //  LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //  LinearLayoutManager linearLayoutManagerH = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        binding.watchesList.setLayoutManager(gridLayoutManager);
    }


    @Override
    public void clickOnItem(int position) {
        Intent intentToDetails = new Intent(MainActivityForHome.this , PdoductDetailsActivity.class);
        intentToDetails.putExtra("tvName",games.get(position).getName());
        intentToDetails.putExtra("tvPrice",games.get(position).getRate()+"$");
        intentToDetails.putExtra("tvSpecialName",games.get(position).getSpecialName());
        intentToDetails.putExtra("imgWatch",games.get(position).getImage());
        startActivity(intentToDetails);
    }

    @Override
    public void clickOnOffer(int position) {
        Intent intentToOfferDetails = new Intent(MainActivityForHome.this, OfferDetailsActivity.class);
        intentToOfferDetails.putExtra("tvOfferDiscount",offers.get(position).getName());
        intentToOfferDetails.putExtra("tvOfferProductType",offers.get(position).getRate()
                +" "+offers.get(position).getRate2());
        intentToOfferDetails.putExtra("tvDiscription","New Brand");
        intentToOfferDetails.putExtra("imgOffer",offers.get(position).getImage());
        startActivity(intentToOfferDetails);

    }
}