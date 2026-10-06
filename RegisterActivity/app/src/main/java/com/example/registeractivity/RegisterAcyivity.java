package com.example.registeractivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.registeractivity.databinding.ActivityMainBinding;

public class RegisterAcyivity extends AppCompatActivity {

ActivityMainBinding bibding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bibding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(bibding.getRoot());

        Button save = findViewById(R.id.save);
        EditText ed1 = findViewById(R.id.ed_name);
        EditText ed2 = findViewById(R.id.ed_email);
        EditText ed3 = findViewById(R.id.ed_phone);
        RadioGroup rg = findViewById(R.id.rg);
        RadioButton rb1 = findViewById(R.id.rb_1);
        RadioButton rb2 = findViewById(R.id.rb_2);
        RadioButton rb3 = findViewById(R.id.rb_3);
        CheckBox box = findViewById(R.id.have_adress);
        EditText city = findViewById(R.id.ed_city);
        EditText state = findViewById(R.id.ed_state);
        EditText street = findViewById(R.id.ed_street);


        bibding.haveAdress.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (bibding.haveAdress.isChecked()){
                    bibding.edCity.setVisibility(View.VISIBLE);
                    bibding.edState.setVisibility(View.VISIBLE);
                   bibding.edStreet.setVisibility(View.VISIBLE);
                }else{
                    bibding.edCity.setVisibility(View.INVISIBLE);
                    bibding.edState.setVisibility(View.INVISIBLE);
                    bibding.edStreet.setVisibility(View.INVISIBLE);

                }

            }
        });



       bibding.save.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(RegisterAcyivity.this , ShowActivity.class);
               if (bibding.rb1.isChecked()||bibding.rb2.isChecked()||bibding.rb3.isChecked()) {


                   if (bibding.edEmail.getText().toString().isEmpty() ||
                           bibding.edName.getText().toString().isEmpty() || bibding.edPhone.getText().toString().isEmpty()) {
                       Toast.makeText(RegisterAcyivity.this, "You should enter" +
                               " your name , email , and your phone", Toast.LENGTH_SHORT).show();
                   } else {
                       intent.putExtra("name", bibding.edName.getText().toString());
                       intent.putExtra("email", bibding.edEmail.getText().toString());
                       intent.putExtra("phone", bibding.edPhone.getText().toString());
                       intent.putExtra("city", bibding.edCity.getText().toString());
                       intent.putExtra("street", bibding.edStreet.getText().toString());
                       intent.putExtra("state", bibding.edState.getText().toString());
                       if (bibding.rb1.isChecked())
                           intent.putExtra("age", rb1.getText().toString());
                       else if (bibding.rb2.isChecked())
                           intent.putExtra("age", rb2.getText().toString());
                       else if (bibding.rb3.isChecked())
                           intent.putExtra("age", rb3.getText().toString());
                       startActivity(intent);
                   }

               }else
                   Toast.makeText(RegisterAcyivity.this, "Choise your age", Toast.LENGTH_SHORT).show();



           }
       });










    }
}