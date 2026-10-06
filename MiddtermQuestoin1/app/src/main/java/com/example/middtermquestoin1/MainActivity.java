package com.example.middtermquestoin1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.example.middtermquestoin1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Intent intent123 = new Intent();


        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,FinancialActivity.class);
                if (binding.personName.getText().toString().isEmpty()){
                    //dialog
                }else if (binding.rg.getCheckedRadioButtonId()==-1){
                    //dialog
                } else if (binding.rg2.getCheckedRadioButtonId()==-1){

                    //dialog
                }


                else {
                    RadioButton rb_age = findViewById(binding.rg2.getCheckedRadioButtonId());
                    RadioButton rb_gender = findViewById(binding.rg.getCheckedRadioButtonId());
                    intent.putExtra("name" ,binding.personName.getText().toString());
                    intent.putExtra("age",rb_age.getText().toString());
                    intent.putExtra("gender",rb_gender.getText().toString());

                    startActivity(intent);
                }


            }
        });






    }
}