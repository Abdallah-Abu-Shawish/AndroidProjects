package com.example.activityresultlansher2;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityResultBinding.inflate(getLayoutInflater());
        TextView tv = findViewById(R.id.tv);
        ImageView img = findViewById(R.id.img);
        Button btn = findViewById(R.id.btn);
        Button btn_gal = findViewById(R.id.btn_gal);
        ActivityResultLauncher<Intent> launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult()
                , new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == 100){
                            Intent intent = result.getData();
                            tv.setText(intent.getStringExtra("massage"));
                        }

                    }
                });






        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , MainActivity2.class);
                launcher.launch(intent);

            }
        });

        ActivityResultLauncher<Intent> launcher2 = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult()
                , new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Uri image = result.getData().getData();
                        img.setImageURI(image);

                    }
                });

        btn_gal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                launcher2.launch(intent);

            }
        });


    }
}