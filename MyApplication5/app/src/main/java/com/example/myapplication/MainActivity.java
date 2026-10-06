package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int number1 = 0;
    int number2 = 0;
    int total =0 ;
    String str = "";


    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button plus = findViewById(R.id.plus);
        Button mines = findViewById(R.id.mines);
        Button mul = findViewById(R.id.multebliy);
        Button clear = findViewById(R.id.clear);
        EditText edit_1 = findViewById(R.id.edit_1);
        EditText edit_2 = findViewById(R.id.edit_2);
        TextView res = findViewById(R.id.result);



        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            number1 = Integer.parseInt(edit_1.getText().toString());
            number2 = Integer.parseInt(edit_2.getText().toString());


          total = number1+number2;
             res.setText(total+"");


            }
        });

        mines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number1 = Integer.parseInt(edit_1.getText().toString());
                number2 = Integer.parseInt(edit_2.getText().toString());


                total = number1-number2;
                res.setText(total+"");

            }
        });

        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number1 = Integer.parseInt(edit_1.getText().toString());
                number2 = Integer.parseInt(edit_2.getText().toString());


                total = number1*number2;
                res.setText(total+"");
            }
        });


        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number1 = 0;
                number2 = 0;
                res.setText("");
                edit_1.setText("");
                edit_2.setText("");

            }
        });


    }
}