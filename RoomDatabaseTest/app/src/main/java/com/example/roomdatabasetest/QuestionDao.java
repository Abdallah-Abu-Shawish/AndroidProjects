package com.example.roomdatabasetest;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface QuestionDao {
    @Insert
    long insertQuestion(Question question);

    @Update
    int updateQuestion(Question question);

    @Delete
    int deleteQuestion(Question question);

    @Query("SELECT * FROM Question")
    List<Question> getAllQuestions();

    @Query("SELECT * FROM Question WHERE q_id = :id")
    Question getQuestionByID(long id);



/*



TODO DONE
    @SuppressLint("Range")
    public ArrayList<Question> getQuestionsByIDs(int[] ids){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Question> questions = new ArrayList<>();
        Cursor cursor = null;
        for (int i = 0; i < ids.length ; i++) {
            String[] args = {ids[i]+""};
            cursor = db.rawQuery("Select * from "+QUESTIONS_TABLE+" where "+COLUMN_QUESTION_ID+" =?", args);
            while (cursor.moveToNext()){
                Question q = new Question();
                q.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_QUESTION_ID)));
                q.setQuestion(cursor.getString(cursor.getColumnIndex(COLUMN_QUESTIONS)));
                q.setChoice1(cursor.getString(cursor.getColumnIndex(COLUMN_CHOICE1)));
                q.setChoice2(cursor.getString(cursor.getColumnIndex(COLUMN_CHOICE2)));
                q.setChoice3(cursor.getString(cursor.getColumnIndex(COLUMN_CHOICE3)));
                q.setChoice4(cursor.getString(cursor.getColumnIndex(COLUMN_CHOICE4)));
                q.setAnswerKey(cursor.getInt(cursor.getColumnIndex(COLUMN_ANSWER_KEY)));
                questions.add(q);
            }
        }
        cursor.close();
        return questions;
    }

TODO Still
    public List<Question> getRandomQuestions(List<Question> allQuestions, int size){
        List<Question> questionListCopy = new ArrayList<>(allQuestions);
        Collections.shuffle(questionListCopy);
        int numberOfQuestions = Math.min(size, questionListCopy.size());
        return  questionListCopy.subList(0, numberOfQuestions);

    }

    */



}
