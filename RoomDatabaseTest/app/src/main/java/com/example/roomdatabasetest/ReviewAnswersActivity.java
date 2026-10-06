package com.example.roomdatabasetest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.example.roomdatabasetest.databinding.ActivityReviewAnswersBinding;

import java.util.ArrayList;

public class ReviewAnswersActivity extends AppCompatActivity {

    ActivityReviewAnswersBinding binding;

    QuestionAdapter adapter;
    //ExamDataBase db;
    AppRoomDataBase dataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityReviewAnswersBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        //db = new ExamDataBase(this);
        dataBase = AppRoomDataBase.getDatabase(this);

        Intent intent = getIntent();
        String[] arrIds = intent.getStringExtra("ids").split(",");
        String[] arrAnswers = intent.getStringExtra("userAnswers").split(",");
        Long[] longIds = new Long[arrIds.length];
        int[] intAnswers  = new int[arrAnswers.length];
        for (int i = 0; i < arrIds.length; i++) {
            longIds[i] = Long.parseLong(arrIds[i]);
            intAnswers[i] = Integer.parseInt(arrAnswers[i]);
        }
        ArrayList<Question> questions = new ArrayList<>();
        for (int i = 0; i < longIds.length; i++) {
            Question question = dataBase.questionDao().getQuestionByID(longIds[i]);
            questions.add(question);
        }
        //List<Question> questions = (List<Question>) intent.getSerializableExtra("examQuestions");

        adapter = new QuestionAdapter(this,questions,intAnswers);
        binding.rvReviewAnswers.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rvReviewAnswers.setLayoutManager(layoutManager);


        binding.btnTakeAnotherExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}