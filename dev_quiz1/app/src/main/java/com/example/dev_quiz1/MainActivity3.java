package com.example.dev_quiz1;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.dev_quiz1.databinding.ActivityMain2Binding;
import com.example.dev_quiz1.databinding.ActivityMain3Binding;

public class MainActivity3 extends AppCompatActivity {
    ActivityMain3Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMain3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Intent intent = new Intent();

       ActivityResultLauncher<Intent> lancher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {

                if (result.getResultCode()==100){
                    Intent intentee=result.getData();
                    binding.tvMass.setText("Reply");
                    binding.tvShow.setText(intentee.getStringExtra("message"));
                }
            }
        });


        binding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity3.this,MainActivity4.class);
                intent.putExtra("massage2", binding.edMassage.getText().toString());


                lancher.launch(intent);
            }
        });













    }
}