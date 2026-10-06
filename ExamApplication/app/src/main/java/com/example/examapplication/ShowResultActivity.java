package com.example.examapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.example.examapplication.databinding.ActivityShowResultBinding;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShowResultActivity extends AppCompatActivity {

    ActivityShowResultBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityShowResultBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());


        Intent intent = getIntent();
        int mark = intent.getIntExtra("examMark",-1);
        ;
        String answers = intent.getStringExtra("userAnswers");
        String ids = intent.getStringExtra("ids");
        //List<Question> examQuestions = (List<Question>) intent.getSerializableExtra("questions");
        binding.tvUserResult.setText(mark+"/20");
        if (mark<10){
            binding.tvCongratFail.setText("Failure");
            binding.tvCongratFail.setTextColor(Color.RED);
            binding.tvUserResultRate.setText("Very Bad");
            binding.tvUserResultRate.setTextColor(Color.RED);
            binding.tvUserResult.setTextColor(Color.RED);
            AlertDialog.Builder builder = new AlertDialog.Builder(ShowResultActivity.this);
            builder.setTitle("لدينا ساقط");
            builder.setMessage("كيف أصلاً إنتا منزل وبتوخد تطوير وورش أندرويد 2 وإنتا مش عارف أساسيات تطوير وورش أندرويد 1 يا ساقط الحرب مش حجة ولا الإنجليزي ليش إنتا دبلوم عشان تشكي داخل بكالوريوس إنتا ومع هيك إنتا الحمار");
            builder.setPositiveButton("يعني عارف حالك حمار", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }

        binding.btnReviewAnswers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ShowResultActivity.this, ReviewAnswersActivity.class);
                intent1.putExtra("ids", ids);
                intent1.putExtra("userAnswers", answers);
                //intent1.putExtra("examQuestions", (Serializable) examQuestions);
                startActivity(intent1);
                finish();
            }
        });


    }
}