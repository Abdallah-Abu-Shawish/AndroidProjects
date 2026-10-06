package com.example.middtermquestoin1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.middtermquestoin1.databinding.ActivityEmpStuBinding;
import com.example.middtermquestoin1.databinding.ActivityFinancialBinding;

public class EmpStuActivity extends AppCompatActivity {

    ActivityEmpStuBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityEmpStuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent2 = getIntent();





        //الواجهة الثانية
        binding.finantialBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //نستخدم كلاس intent للرجوع الى الواحهة ونقل البيانات المدخلة
                Intent intent=new Intent();
                //نرسل البيانات

               if (intent2.getStringExtra("select").equals("1")){
                   binding.ed1.setHint("Enter university name you study in ");
                   binding.ed2.setHint("Enter your graduation year ");
                   if (binding.ed1.getText().toString().isEmpty()){
                       //diolog
                   }else if (binding.ed2.getText().toString().isEmpty()){
                       //dialog
                   }else{

                       intent.putExtra("edit1",binding.ed1.getText().toString());
                       intent.putExtra("edit2",binding.ed2.getText().toString());


                   }
                }
                //نضع رمز كود للتعرف على الواجهة
                setResult(100,intent);
                finish();
            }
        });







    }
}