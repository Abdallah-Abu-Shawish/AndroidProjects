package com.example.middtermquestoin1;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.middtermquestoin1.databinding.ActivityFinancialBinding;

public class FinancialActivity extends AppCompatActivity {

    ActivityFinancialBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityFinancialBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




        ActivityResultLauncher<Intent> lancher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {

                //نفحص رمز الكود اذا كان يساوي الرمز الذي وضعناه نستعيد البيانات
                if (result.getResultCode()==100){
                    Intent intentee = result.getData();
                    //الكود الذي وضعناه
                    binding.name1111.setText(intentee.getStringExtra("edit1"));
                    binding.ys1111.setText(intentee.getStringExtra("edit2"));

                }

        }});

        //الانتقال للواجهة التي نريد الحصول على البيانات منها
      binding.finantialEnterData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FinancialActivity.this,EmpStuActivity.class);
                if (binding.rb2Stu.isChecked()){
                    intent.putExtra("select","1");
                }else{
                    intent.putExtra("select","2");
                }
                //استخدام الاوبجيكت الذي انشئناه
                lancher.launch(intent);
            }
        });










    }
}