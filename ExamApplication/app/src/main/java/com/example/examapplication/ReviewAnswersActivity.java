package com.example.examapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.examapplication.databinding.ActivityReviewAnswersBinding;

import java.util.ArrayList;
import java.util.List;

public class ReviewAnswersActivity extends AppCompatActivity {

    ActivityReviewAnswersBinding binding;

    QuestionAdapter adapter;
    ExamDataBase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityReviewAnswersBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        db = new ExamDataBase(this);

        Intent intent = getIntent();
        String[] arrIds = intent.getStringExtra("ids").split(",");
        String[] arrAnswers = intent.getStringExtra("userAnswers").split(",");
        int[] intIds = new int[arrIds.length];
        int[] intAnswers  = new int[arrAnswers.length];
        for (int i = 0; i < arrIds.length; i++) {
            intIds[i] = Integer.parseInt(arrIds[i]);
            intAnswers[i] = Integer.parseInt(arrAnswers[i]);
        }
        ArrayList<Question> questions = db.getQuestionsByIDs(intIds);
        //List<Question> questions = (List<Question>) intent.getSerializableExtra("examQuestions");

        adapter = new QuestionAdapter(this,questions,intAnswers);
        binding.rvReviewAnswers.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rvReviewAnswers.setLayoutManager(layoutManager);


        binding.btnTakeAnotherExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ReviewAnswersActivity.this, WelcomeActivity.class);
                startActivity(intent1);
                finish();
            }
        });

    }
}