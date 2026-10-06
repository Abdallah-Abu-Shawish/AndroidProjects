package com.example.examapplication;

import static android.graphics.Color.RED;

import androidx.annotation.ColorRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.examapplication.databinding.ActivityMainBinding;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    QuestionAdapter adapter;
    private CountDownTimer examTimer;
    ExamDataBase db;
    ActivityMainBinding binding;
    int count;
    int mark;
    String strAnswers = "";
    String questionsIDs = "";
    boolean flag = false;

    ArrayList<Integer> answers;

    ArrayList<Integer> ids;

    List<Question> examQuestions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        count = 0;
        db = new ExamDataBase(this);
        List<Question> allQuestions = db.getAllQuestions();
        examQuestions = db.getRandomQuestions(allQuestions, 20);

        mark = 0;

        answers = new ArrayList<>();
        ids = new ArrayList<>();
        ArrayList<Integer> rbsAnswers = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            answers.add(0);
            rbsAnswers.add(-1);
        }

        binding.progressBar.setMax(19);
        startExamTimer();
        //adapter = new QuestionAdapter(this, );
        //binding.rvQuestions.setAdapter(adapter);
        //LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        //binding.rvQuestions.setLayoutManager(layoutManager);

        binding.tvQuestionText.setText(examQuestions.get(0).getQuestion());
        binding.rbChoice1.setText(examQuestions.get(0).getChoice1());
        binding.rbChoice2.setText(examQuestions.get(0).getChoice2());
        binding.rbChoice3.setText(examQuestions.get(0).getChoice3());
        binding.rbChoice4.setText(examQuestions.get(0).getChoice4());

        if (count == 0){
            binding.btnPrevQuestion.setVisibility(View.INVISIBLE);
        }
        binding.tvQuestionNumber.setText(count+1 +"/20");
        binding.btnNextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int checkedRbID = binding.rgChoices.getCheckedRadioButtonId();
//                if (checkedRbID == R.id.rb_choice1){
//                    binding.rbChoice1.setChecked(true);
//                }else if (checkedRbID == R.id.rb_choice2){
//                    binding.rbChoice2.setChecked(true);
//                }else if (checkedRbID == R.id.rb_choice3){
//                    binding.rbChoice3.setChecked(true);
//                }else if (checkedRbID == R.id.rb_choice4){
//                    binding.rbChoice4.setChecked(true);
//                }
                flag = false;
                if (count==19){
                    binding.btnNextQuestion.setVisibility(View.INVISIBLE);
                    binding.btnEnd.setVisibility(View.VISIBLE);
                }else {
                    binding.btnNextQuestion.setVisibility(View.VISIBLE);
//                    if (rbsAnswers.get(count + 1) == -1){
//                        binding.rgChoices.check(-1);
//                    } else if (rbsAnswers.get(count+1) == R.id.rb_choice1) {
//                        binding.rbChoice1.setSelected(true);
//                    } else if (rbsAnswers.get(count+1) == R.id.rb_choice2) {
//                        binding.rbChoice2.setSelected(true);
//                    } else if (rbsAnswers.get(count+1) == R.id.rb_choice3) {
//                        binding.rbChoice3.setSelected(true);
//                    } else if (rbsAnswers.get(count+1) == R.id.rb_choice4) {
//                        binding.rbChoice4.setSelected(true);
//                    }
                    binding.rgChoices.clearCheck();
                    binding.tvQuestionText.setText(examQuestions.get(count+1).getQuestion());
                    binding.rbChoice1.setText(examQuestions.get(count+1).getChoice1());
                    binding.rbChoice2.setText(examQuestions.get(count+1).getChoice2());
                    binding.rbChoice3.setText(examQuestions.get(count+1).getChoice3());
                    binding.rbChoice4.setText(examQuestions.get(count+1).getChoice4());
                    count++;
                    binding.progressBar.setProgress(count);
                    binding.tvQuestionNumber.setText(count+1 +"/20");

                }
                if (count==1){
                    binding.btnPrevQuestion.setVisibility(View.VISIBLE);
                }
            }
        });

        binding.btnPrevQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count >= 1){
                    binding.btnPrevQuestion.setVisibility(View.VISIBLE);
                    binding.btnNextQuestion.setVisibility(View.VISIBLE);
                    binding.btnEnd.setVisibility(View.INVISIBLE);
                    binding.rgChoices.clearCheck();
                    binding.tvQuestionText.setText(examQuestions.get(count-1).getQuestion());
                    binding.rbChoice1.setText(examQuestions.get(count-1).getChoice1());
                    binding.rbChoice2.setText(examQuestions.get(count-1).getChoice2());
                    binding.rbChoice3.setText(examQuestions.get(count-1).getChoice3());
                    binding.rbChoice4.setText(examQuestions.get(count-1).getChoice4());
//                    if (rbsAnswers.get(count - 1) == -1){
//                        binding.rgChoices.check(-1);
//                    } else if (rbsAnswers.get(count-1) == R.id.rb_choice1) {
//                        binding.rbChoice1.setSelected(true);
//                    } else if (rbsAnswers.get(count-1) == R.id.rb_choice2) {
//                        binding.rbChoice2.setSelected(true);
//                    } else if (rbsAnswers.get(count-1) == R.id.rb_choice3) {
//                        binding.rbChoice3.setSelected(true);
//                    } else if (rbsAnswers.get(count-1) == R.id.rb_choice4) {
//                        binding.rbChoice4.setSelected(true);
//                    }
                    count--;
                    binding.progressBar.setProgress(count);
                    binding.tvQuestionNumber.setText(count+1 +"/20");

                }
            }
        });




        binding.btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShowResultActivity.class);
                for (int i = 0; i < examQuestions.size(); i++) {
                    if (answers.get(i) == examQuestions.get(i).getAnswerKey()){
                        mark++;
                    }
                }
                for (int i = 0; i < examQuestions.size(); i++) {
                    ids.add(examQuestions.get(i).getId());
                    if (i == examQuestions.size() - 1){
                        questionsIDs += ids.get(i);
                    }else {
                        questionsIDs += ids.get(i) + ",";
                    }

                }
                for (int i = 0; i < answers.size(); i++) {
                    if (i == answers.size() - 1){
                        strAnswers += answers.get(i);
                    }else {
                        strAnswers += answers.get(i) + ",";
                    }


                }

//                Toast.makeText(MainActivity.this, answers.size()+"", Toast.LENGTH_SHORT).show();
//                Toast.makeText(MainActivity.this, strAnswers, Toast.LENGTH_SHORT).show();
//                Toast.makeText(MainActivity.this, "ids: "+questionsIDs, Toast.LENGTH_SHORT).show();
//                Toast.makeText(MainActivity.this, mark+"", Toast.LENGTH_SHORT).show();

                intent.putExtra("ids", questionsIDs);
                intent.putExtra("userAnswers", strAnswers);
                intent.putExtra("examMark", mark);
                startActivity(intent);
                examTimer.cancel();
                finish();
            }
        });






        binding.rbChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answers.set(count, 1);
                rbsAnswers.set(count,R.id.rb_choice1);
                if (answers.get(count) == examQuestions.get(count).getAnswerKey()){
                    //if (!flag)
                       // mark++;
                }
                flag = true;
            }
        });
        binding.rbChoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answers.set(count, 2);
                rbsAnswers.set(count,R.id.rb_choice2);
                if (answers.get(count) == examQuestions.get(count).getAnswerKey()){
//                    if (!flag)
//                        mark++;
                }
                flag = true;
            }
        });
        binding.rbChoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answers.set(count, 3);
                rbsAnswers.set(count,R.id.rb_choice3);
                if (answers.get(count) == examQuestions.get(count).getAnswerKey()){
//                    if (!flag)
//                        mark++;
                }
                flag = true;
            }
        });
        binding.rbChoice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answers.set(count, 4);
                rbsAnswers.set(count,R.id.rb_choice4);
                if (answers.get(count) == examQuestions.get(count).getAnswerKey()){
//                    if (!flag)
//                        mark++;
                }
                flag = true;
            }
        });

//        binding.rgChoices.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @SuppressLint("NonConstantResourceId")
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                if (binding.rbChoice1.isChecked()){
//                    answers.set(count, 1);
//
//                } else if (binding.rbChoice2.isChecked()) {
//                    answers.set(count, 2);
//
//                } else if (binding.rbChoice3.isChecked()) {
//                    answers.set(count, 3);
//
//                } else if (binding.rbChoice4.isChecked()) {
//                    answers.set(count, 4);
//
//                } else {
//                    answers.set(count, 0);
//                }
//            }
//        });


    }






    private void startExamTimer() {
        // 25 inested of 6 ( I put 6 for test )
        examTimer = new CountDownTimer(5 * 60 * 1000, 1000) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                long minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);
                long seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(minutes);

                binding.timerTextView.setText("Time left: " + minutes + ":" + String.format("%02d", seconds));
                if (minutes==0){
                    binding.timerTextView.setTextColor(RED);
                }
            }

            @Override
            public void onFinish() {

                binding.btnPrevQuestion.setVisibility(View.INVISIBLE);
                binding.btnNextQuestion.setVisibility(View.INVISIBLE);
                binding.btnEnd.setVisibility(View.INVISIBLE);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Times Up!!!");
                builder.setMessage("You didn`t answer all the questions be faster next time");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, ShowResultActivity.class);
                        for (int i = 0; i < examQuestions.size(); i++) {
                            if (answers.get(i) == examQuestions.get(i).getAnswerKey()){
                                mark++;
                            }
                        }
                        for (int i = 0; i < examQuestions.size(); i++) {
                            ids.add(examQuestions.get(i).getId());
                            if (i == examQuestions.size() - 1){
                                questionsIDs += ids.get(i);
                            }else {
                                questionsIDs += ids.get(i) + ",";
                            }

                        }
                        for (int i = 0; i < answers.size(); i++) {
                            if (i == answers.size() - 1){
                                strAnswers += answers.get(i);
                            }else {
                                strAnswers += answers.get(i) + ",";
                            }


                        }

                        //Toast.makeText(MainActivity.this, answers.size()+"", Toast.LENGTH_SHORT).show();
                       // Toast.makeText(MainActivity.this, strAnswers, Toast.LENGTH_SHORT).show();
                        //Toast.makeText(MainActivity.this, "ids: "+questionsIDs, Toast.LENGTH_SHORT).show();
                        //Toast.makeText(MainActivity.this, mark+"", Toast.LENGTH_SHORT).show();

                        intent.putExtra("ids", questionsIDs);
                        intent.putExtra("userAnswers", strAnswers);
                        intent.putExtra("examMark", mark);
                        startActivity(intent);
                        finish();
                    }
                });
                examTimer.cancel();
                AlertDialog dialog = builder.create();
                dialog.setCancelable(false);
                dialog.show();


                //Toast.makeText(MainActivity.this, "Times up!!", Toast.LENGTH_SHORT).show();
                // Handle exam completion
            }
        };
        examTimer.start();
    }


//    public static ArrayList<Integer> generateRandomIndexes(int numOfQ , ArrayList<Question> questions){
//        ArrayList<Integer> randomIndexes = new ArrayList<>();
//        if (numOfQ <= questions.size()) {
//            int temp= -1;
//            int random = 0;
//            for (int i = 0; i < numOfQ; i++) {
//                do {
//                    random = (int) (Math.random() * questions.size());
//                } while (random == temp || randomIndexes.contains(random));
//                randomIndexes.add(random);
//                temp = random;
//            }
//            return randomIndexes;
//        }
//        return null;
//    }
}