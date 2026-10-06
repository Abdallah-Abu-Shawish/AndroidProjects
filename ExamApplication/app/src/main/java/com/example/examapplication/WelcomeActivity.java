package com.example.examapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.examapplication.databinding.ActivityWelcomeBinding;

public class WelcomeActivity extends AppCompatActivity {

    ActivityWelcomeBinding binding;
    ExamDataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityWelcomeBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        db = new ExamDataBase(this);
        //Toast.makeText(this, "in onCreate", Toast.LENGTH_SHORT).show();


        binding.btnGoToExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                //Toast.makeText(WelcomeActivity.this, "clicked / go to Main", Toast.LENGTH_SHORT).show();
                Log.d("In Click", "btn clicked");

                if (db.getAllQuestions() == null || db.getAllQuestions().isEmpty())
                        db.fillInDatabase();

                startActivity(intent);
                finish();
            }
        });

    }

}