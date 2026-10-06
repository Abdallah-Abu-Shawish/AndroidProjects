package com.example.student_teacher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        EditText ed1 = findViewById(R.id.ed1);
        EditText ed2 = findViewById(R.id.ed2);
        Button back = findViewById(R.id.btn_back);
        Intent intent = new Intent();
        setResult(100 , intent);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("student" , "Student : "+ed1.getText().toString() +"\n"+ "Student : "+ed2.getText().toString());
                intent.putExtra("teacher" , "Teacher : "+ed1.getText().toString()+"\n"+"Teacher : "+ed2.getText().toString());
                finish();
            }
        });


    }
}