package com.example.intint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EditText ed1 = findViewById(R.id.ed1);
        EditText ed2 = findViewById(R.id.ed2);
        Button btn_save = findViewById(R.id.btn1);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intint = new Intent(MainActivity.this , ShowActivity.class);
                intint.putExtra("name" , ed1.getText().toString());
                intint.putExtra("email" , ed2.getText().toString());
                startActivity(intint);
            }
        });



    }
}