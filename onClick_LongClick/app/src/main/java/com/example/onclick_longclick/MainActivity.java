package com.example.onclick_longclick;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
boolean bb = true;
String s1 = "";
String s2 = "";
String s3 = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btn);
        TextView tv = findViewById(R.id.tv);
        RadioGroup rdg = findViewById(R.id.rdgb);
        CheckBox box1 = findViewById(R.id.cbox_1);
        CheckBox box2 = findViewById(R.id.cbox_2);
        CheckBox box3 = findViewById(R.id.cbox_3);
        Button checbox_btn = findViewById(R.id.chacbox_btn);
        StringBuilder str ;
        str = new StringBuilder();

        checbox_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (box1.isChecked()){
               s1 = box1.getText().toString();
             //  str.append(box1.getText().toString());

                } else if (box2.isChecked()){
                    s2 = box2.getText().toString();
                //   str.append(box2.getText().toString());
                } else if (box3.isChecked()){
                  s3 = box3.getText().toString();
                 // str.append(box3.getText().toString());
                }else {
                    Toast.makeText(MainActivity.this, "Nothing is selectd", Toast.LENGTH_SHORT).show();
                }

                Toast.makeText(MainActivity.this, "You selected "+s1+"\n"+s2+"\n"+s3, Toast.LENGTH_SHORT).show();

            }
        });






        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bb){
                    btn.setText("Android");
                    btn.setTextSize(30);
                    btn.setBackgroundColor(getColor(R.color.purple_500));
                    tv.setText("JAVA&&C#");
                    tv.setTextSize(24);
                    tv.setTextColor(getColor(R.color.black));
                    bb=false;
                }else {
                    btn.setText("Hello");
                    btn.setTextSize(14);
                    btn.setBackgroundColor(getColor(R.color.black));
                    tv.setText("bbbbb");
                    tv.setTextSize(16);
                    tv.setTextColor(getColor(R.color.purple_500));
                    bb=true;

                }

                Log.d("abcd","Welcome to Android");
                Toast.makeText(MainActivity.this, "Button is clisked", Toast.LENGTH_SHORT).show();

            }
        });



        btn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                btn.setText("Android");
                btn.setTextSize(40);
                btn.setBackgroundColor(getColor(R.color.purple_500));

                return true;
            }
        });



        rdg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        rdg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_1:
                        Toast.makeText(MainActivity.this, "Male is selscted", Toast.LENGTH_SHORT).show();
                    break;
                    case R.id.rb_2:
                        Toast.makeText(MainActivity.this, "Female is selscted", Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        });









    }
}