package com.example.registeractivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        TextView tv1 = findViewById(R.id.tv1);
        TextView tv2 = findViewById(R.id.tv2);
        TextView tv3 = findViewById(R.id.tv3);
        TextView tv4 = findViewById(R.id.tv4);
        TextView tv5 = findViewById(R.id.tv5);
        Button btn = findViewById(R.id.back);
        Intent intent = getIntent();
        tv1.setText(intent.getStringExtra("name"));
        tv2.setText(intent.getStringExtra("email"));
        tv3.setText(intent.getStringExtra("phone"));
        tv4.setText(intent.getStringExtra("age"));
        tv5.setText(intent.getStringExtra("state")+" / "+intent.getStringExtra("city")+" / "+intent.getStringExtra("street"));


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });








    }
}