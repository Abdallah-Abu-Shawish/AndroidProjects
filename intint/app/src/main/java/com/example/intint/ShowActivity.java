package com.example.intint;

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

        TextView  tv1 = findViewById(R.id.tv1);
        TextView  tv2 = findViewById(R.id.tv2);
        Button btn2 = findViewById(R.id.btn2);
        Intent intent = getIntent();

        tv1.setText(intent.getStringExtra("name"));
        tv2.setText(intent.getStringExtra("email"));

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });






    }
}