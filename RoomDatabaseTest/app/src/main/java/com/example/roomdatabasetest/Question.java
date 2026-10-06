package com.example.roomdatabasetest;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Question {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "q_id")
    private long id;
    @NonNull
    private String question;
    @NonNull
    private String choice1;
    @NonNull
    private String choice2;
    @NonNull
    private String choice3;
    @NonNull
    private String choice4;
    @NonNull
    private int answerKey;


    public Question() {
    }


    public Question(String question, String choice1, String choice2, String choice3, String choice4, int answerKey) {
        this.question = question;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
        this.choice4 = choice4;
        this.answerKey = answerKey;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getChoice1() {
        return choice1;
    }

    public void setChoice1(String choice1) {
        this.choice1 = choice1;
    }

    public String getChoice2() {
        return choice2;
    }

    public void setChoice2(String choice2) {
        this.choice2 = choice2;
    }

    public String getChoice3() {
        return choice3;
    }

    public void setChoice3(String choice3) {
        this.choice3 = choice3;
    }

    public String getChoice4() {
        return choice4;
    }

    public void setChoice4(String choice4) {
        this.choice4 = choice4;
    }

    public int getAnswerKey() {
        return answerKey;
    }

    public void setAnswerKey(int answerKey) {
        this.answerKey = answerKey;
    }
}
