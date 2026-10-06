package com.example.instegramproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonUiContext;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ExamDatabase extends SQLiteOpenHelper {

    Context context;
    private final static String DATABASE_NAME = "exam.db";
    private static int DATABASE_VERSION = 1;

    public final static String QUESTIONS_TABLE = "questions";
    public final static String COLUMN_QUESTION_ID = "id_col";
    public final static String COLUMN_QUESTIONS = "questions_col";
    public final static String COLUMN_CHOICE1 = "choice1_col";
    public final static String COLUMN_CHOICE2 = "choice2_col";
    public final static String COLUMN_CHOICE3 = "choice3_col";
    public final static String COLUMN_CHOICE4 = "choice4_col";
    public final static String COLUMN_ANSWER_KEY = "answerKey_col";

    public ExamDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String questions_table = "CREATE TABLE " + QUESTIONS_TABLE + "(" +
                COLUMN_QUESTION_ID + "INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_QUESTIONS + " TEXT NOT NULL,"+
                COLUMN_CHOICE1 + " TEXT NOT NULL,"+
                COLUMN_CHOICE2 + " TEXT NOT NULL," +
                COLUMN_CHOICE3 + " TEXT NOT NULL," +
                COLUMN_CHOICE4 + " TEXT NOT NULL," +
                COLUMN_ANSWER_KEY + " INTEGER NOT NULL)";

        db.execSQL(questions_table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QUESTIONS_TABLE);
        onCreate(db);
    }



//    public ArrayList<Question> getQuestionsForExam() {

  //  }






}
