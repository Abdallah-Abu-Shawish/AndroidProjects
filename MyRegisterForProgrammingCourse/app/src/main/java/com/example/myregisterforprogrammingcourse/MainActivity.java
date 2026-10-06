package com.example.myregisterforprogrammingcourse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        View my_layout = findViewById(R.id.layout_prog);
        EditText name = findViewById(R.id.editText);
        RadioGroup rg1 = findViewById(R.id.rg1);
        RadioGroup rg2 = findViewById(R.id.rg2);
        RadioButton rbtn1 = findViewById(R.id.rbtn_1);
        RadioButton rbtn2 = findViewById(R.id.rbtn_2);
        RadioButton rbtn3 = findViewById(R.id.rbtn_3);
        RadioButton rbtn_male = findViewById(R.id.rbtn_gen_male);
        RadioButton rbtn_female = findViewById(R.id.rbtn_gen_female);
        Button save = findViewById(R.id.save_btn);
        CheckBox btn1 = findViewById(R.id.btn1);
        CheckBox btn2 = findViewById(R.id.btn2);
        CheckBox btn3 = findViewById(R.id.btn3);
        CheckBox btn4 = findViewById(R.id.btn4);
        TextView result = findViewById(R.id.tv_set_results);



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (rbtn_male.isChecked()||rbtn_female.isChecked()){
                    if (rbtn1.isChecked()){
                        result.setText(name.getText().toString()+" is Young");
                        my_layout.setBackground((getDrawable(R.drawable.programming_languages_img)));
                        my_layout.setBackgroundColor(1990132236);
                        if (btn1.isChecked()){
                            Toast.makeText(MainActivity.this, "You chose JAVA", Toast.LENGTH_SHORT).show();
                        }
                        if (btn2.isChecked()){
                            Toast.makeText(MainActivity.this, "You chose Python", Toast.LENGTH_SHORT).show();
                        }
                        if (btn3.isChecked()){
                            Toast.makeText(MainActivity.this, "You chose C#", Toast.LENGTH_SHORT).show();
                        }
                        if (btn4.isChecked()){
                            Toast.makeText(MainActivity.this, "You chose C++", Toast.LENGTH_SHORT).show();
                        }


                    } else if (rbtn2.isChecked()){
                        result.setText(name.getText().toString()+" is Youth");
                        my_layout.setBackground((getDrawable(R.drawable.programming_languages_img)));
                        if (btn1.isChecked()){
                            Toast.makeText(MainActivity.this, "You chose JAVA", Toast.LENGTH_SHORT).show();
                        }
                        if (btn2.isChecked()){
                            Toast.makeText(MainActivity.this, "You chose Python", Toast.LENGTH_SHORT).show();
                        }
                        if (btn3.isChecked()){
                            Toast.makeText(MainActivity.this, "You chose C#", Toast.LENGTH_SHORT).show();
                        }
                        if (btn4.isChecked()){
                            Toast.makeText(MainActivity.this, "You chose C++", Toast.LENGTH_SHORT).show();
                        }

                    } else if (rbtn3.isChecked()){
                        result.setText(name.getText().toString()+" is Older");
                        my_layout.setBackground((getDrawable(R.drawable.programming_languages_img)));
                        my_layout.setBackgroundColor(2115639176);
                        if (btn1.isChecked()){
                            Toast.makeText(MainActivity.this, "You chose JAVA", Toast.LENGTH_SHORT).show();
                        }
                        if (btn2.isChecked()){
                            Toast.makeText(MainActivity.this, "You chose Python", Toast.LENGTH_SHORT).show();
                        }
                        if (btn3.isChecked()){
                            Toast.makeText(MainActivity.this, "You chose C#", Toast.LENGTH_SHORT).show();
                        }
                        if (btn4.isChecked()){
                            Toast.makeText(MainActivity.this, "You chose C++", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        result.setText("You have to select your age");


                    }

                } else {
                    result.setText("You should select your gender");
                }











            }
        });























    }
}