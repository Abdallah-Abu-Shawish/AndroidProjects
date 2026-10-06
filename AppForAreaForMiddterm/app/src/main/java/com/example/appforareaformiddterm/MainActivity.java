package com.example.appforareaformiddterm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.appforareaformiddterm.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    double result = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        binding.doCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                binding.result.setText(result+"");


            }
        });






    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflator=getMenuInflater();
        inflator.inflate(R.menu.my_area_menu,menu);
        return true;
    }





    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.circle:
                binding.ed1.setHint("Enter the radius of the Circle");
                binding.ed2.setVisibility(View.INVISIBLE);
                result = Math.PI*Integer.parseInt( binding.ed1.getText().toString())*Integer.parseInt( binding.ed1.getText().toString());
                break;
            case R.id.rctangle:
                binding.ed1.setHint("Enter the length of the Rectangle");
                binding.ed2.setHint("Enter the width of the rctangle");
                result = Integer.parseInt( binding.ed1.getText().toString())*Integer.parseInt( binding.ed2.getText().toString());;
                break;
            case R.id.square:
                binding.ed1.setHint("Enter the length of the Square");
                binding.ed2.setVisibility(View.INVISIBLE);
                result = Integer.parseInt( binding.ed1.getText().toString())*Integer.parseInt( binding.ed1.getText().toString());
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}