package com.example.myapplicationelictronicmsbaha;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int num = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button minec = findViewById(R.id.btn1);
        Button plus = findViewById(R.id.btn2);
        Button restart = findViewById(R.id.btn3);
        TextView numbers  = findViewById(R.id.tv_counter);

        minec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num--;
                if (num==100){
                    num=0;
                }
                if (num<0)
                    num =0;
                numbers.setText(String.valueOf(num));

            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num++;
                if (num==100){
                    num=0;
                }
                numbers.setText(String.valueOf(num));
            }
        });

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num =0;
                if (num==100){
                    num=0;
                }

                numbers.setText(String.valueOf(num));
            }
        });


    }
}