package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    double number1 = 0;
    double number2 = 0;
    double total = 0;
    String operation = "";
    String toNum1 = "";
    String toNum2 = "";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn0 = findViewById(R.id.btn_0);
        Button btn1 = findViewById(R.id.btn_1);
        Button btn2 = findViewById(R.id.btn_2);
        Button btn3 = findViewById(R.id.btn_3);
        Button btn4 = findViewById(R.id.btn_4);
        Button btn5 = findViewById(R.id.btn_5);
        Button btn6 = findViewById(R.id.btn_6);
        Button btn7 = findViewById(R.id.btn_7);
        Button btn8 = findViewById(R.id.btn_8);
        Button btn9 = findViewById(R.id.btn_9);
        Button btn_mul = findViewById(R.id.mult_btn);
        Button btn_dive = findViewById(R.id.dive_btn);
        Button btn_pius = findViewById(R.id.pius_btn);
        Button btn_mince = findViewById(R.id.minc_btn);
        Button btn_equale = findViewById(R.id.equal_btn);
        Button btn_clear = findViewById(R.id.clear_btn);
        TextView tv_resultes = findViewById(R.id.tv_result);


        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operation+="0";
                tv_resultes.setText(operation);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operation+="1";
                tv_resultes.setText(operation);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operation+="2";
                tv_resultes.setText(operation);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
         @Override
       public void onClick(View v) {
             operation+="3";
             tv_resultes.setText(operation);
        }
        });
              btn4.setOnClickListener(new View.OnClickListener() {
          @Override
               public void onClick(View v) {
              operation+="4";
              tv_resultes.setText(operation);
        }

        });
              btn5.setOnClickListener(new View.OnClickListener() {
              @Override
                public void onClick(View v) {
                  operation+="5";
                  tv_resultes.setText(operation);
        }
        });
              btn6.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      operation+="6";
                      tv_resultes.setText(operation);
        }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operation+="7";
                tv_resultes.setText(operation);
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operation+="8";
                tv_resultes.setText(operation);

            }
        });


        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operation+="9";
                tv_resultes.setText(operation);

            }
        });


        btn_mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operation+="*";
                tv_resultes.setText(operation);

            }
        });

        btn_dive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operation+="÷";
                tv_resultes.setText(operation);
            }
        });

        btn_pius.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operation+="+";
                tv_resultes.setText(operation);
            }
        });

        btn_mince.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operation+="-";
                tv_resultes.setText(operation);
            }
        });

        btn_equale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int counter = 0;
                for (int i = 0; i < operation.length(); i++) {
                    char c = operation.charAt(i);
                    if (c == '*' || c == '+' || c == '-' || c == '÷'){
                        counter++;

                }
            }
          if (operation.indexOf("*")==0||operation.indexOf("+")==0||operation.indexOf("-")==0||operation.indexOf("÷")==0
                  ||operation.indexOf("*")==operation.length()-1||operation.indexOf("+")==operation.length()-1
                  ||operation.indexOf("-")==operation.length()-1||operation.indexOf("÷")==operation.length()-1){
              Toast.makeText(MainActivity.this, "You should have two numbers to start the operation", Toast.LENGTH_SHORT).show();
              operation = "";
              tv_resultes.setText(operation);
          }


             else if (counter>1){
                  Toast.makeText(MainActivity.this, "This calculator only allows one operation", Toast.LENGTH_SHORT).show();
                  operation="";
                  tv_resultes.setText(operation);
              } else {

                  if (operation.contains("+")){

                      toNum1 = operation.substring(0 , operation.indexOf("+"));
                      number1 = Double.parseDouble(toNum1);
                      toNum2 = operation.substring(operation.indexOf("+")+1
                              , operation.length());
                      number2 = Double.parseDouble(toNum2);

                      total = number1+number2;
                      tv_resultes.setText(total+"");


                  }else if (operation.contains("-")){


                      toNum1 = operation.substring(0 , operation.indexOf("-"));
                      number1 = Double.parseDouble(toNum1);
                      toNum2 = operation.substring(operation.indexOf("-")+1
                              , operation.length());
                      number2 = Double.parseDouble(toNum2);

                      total = number1-number2;
                      tv_resultes.setText(total+"");


                  }else if (operation.contains("*")){


                      toNum1 = operation.substring(0 , operation.indexOf("*"));
                      number1 = Double.parseDouble(toNum1);
                      toNum2 = operation.substring(operation.indexOf("*")+1
                              , operation.length());
                      number2 = Double.parseDouble(toNum2);

                      total = number1*number2;
                      tv_resultes.setText(total+"");


                  }else if (operation.contains("÷")){

                      toNum1 = operation.substring(0 , operation.indexOf("÷"));
                      number1 = Double.parseDouble(toNum1);
                      toNum2 = operation.substring(operation.indexOf("÷")+1
                              , operation.length());
                      number2 = Double.parseDouble(toNum2);


                      if (number2==0){

                          tv_resultes.setText("Undefind");
                      }else {
                          total = number1/number2;
                          tv_resultes.setText(total+"");

                      }

                  }

              }
                operation = "";
            }
        });

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                number1 = 0;
               number2 = 0;
               total=0;
               operation="";
                tv_resultes.setText("");
            }
        });


    }
}