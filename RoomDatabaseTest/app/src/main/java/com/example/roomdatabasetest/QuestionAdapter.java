package com.example.roomdatabasetest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;


import com.example.roomdatabasetest.databinding.QuestionItemBinding;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Question> questionList;
    private QuestionItemBinding binding;
    private int[] userAnswers;


    public QuestionAdapter(Context context, List<Question> questionList, int[] userAnswers) {
        this.context = context;
        this.questionList = questionList;
        this.userAnswers = userAnswers;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = QuestionItemBinding.inflate(LayoutInflater.from(context), parent, false);
        return new QuestionHolder(binding);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        QuestionHolder myHolder = (QuestionHolder) holder;
        myHolder.binding.tvQuestionText.setText(questionList.get(position).getQuestion());
        myHolder.binding.rbChoice1.setText(questionList.get(position).getChoice1());
        myHolder.binding.rbChoice2.setText(questionList.get(position).getChoice2());
        myHolder.binding.rbChoice3.setText(questionList.get(position).getChoice3());
        myHolder.binding.rbChoice4.setText(questionList.get(position).getChoice4());
        if (userAnswers[position] == questionList.get(position).getAnswerKey()) {
            myHolder.binding.getRoot().setCardBackgroundColor(Color.parseColor("#9DF2A0"));

            if (userAnswers[position] == 1) {
                myHolder.binding.rbChoice1.setChecked(true);


            } else if (userAnswers[position] == 2) {
                myHolder.binding.rbChoice2.setChecked(true);


            } else if (userAnswers[position] == 3) {
                myHolder.binding.rbChoice3.setChecked(true);


            } else if (userAnswers[position] == 4) {
                myHolder.binding.rbChoice4.setSelected(true);


            }
            myHolder.binding.rgItemChoices.setClickable(false);
            myHolder.binding.rbChoice1.setEnabled(false);
            myHolder.binding.rbChoice2.setEnabled(false);
            myHolder.binding.rbChoice3.setEnabled(false);
            myHolder.binding.rbChoice4.setEnabled(false);

        }   else {
                if (userAnswers[position] == 0){
                    binding.rgItemChoices.setClickable(false);
                    myHolder.binding.rbChoice1.setChecked(false);
                    myHolder.binding.rbChoice2.setChecked(false);
                    myHolder.binding.rbChoice3.setChecked(false);
                    myHolder.binding.rbChoice4.setChecked(false);

                    myHolder.binding.rbChoice1.setEnabled(false);
                    myHolder.binding.rbChoice2.setEnabled(false);
                    myHolder.binding.rbChoice3.setEnabled(false);
                    myHolder.binding.rbChoice4.setEnabled(false);
                }
            myHolder.binding.getRoot().setCardBackgroundColor(Color.parseColor("#FB9590"));
        }

        myHolder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userAnswers[position] == questionList.get(position).getAnswerKey()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Right Answer");
                    builder.setMessage("You answer correctly on the question good job");
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else if (userAnswers[position] == 0) {
                    AlertDialog.Builder builder2 = new AlertDialog.Builder(context);
                    builder2.setTitle("Empty Answer");
                    builder2.setMessage("You didn`t answer on the question and you left it empty");
                    AlertDialog dialog = builder2.create();
                    dialog.show();
                }else {
                    AlertDialog.Builder builder3 = new AlertDialog.Builder(context);
                    builder3.setTitle("False Answer");
                    builder3.setMessage("You didn`t answer correctly on the question Be careful!!");
                    AlertDialog dialog = builder3.create();
                    dialog.show();
                }



            }
        });
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }


    public class QuestionHolder extends RecyclerView.ViewHolder {
        QuestionItemBinding binding;
        public QuestionHolder(QuestionItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }

}
