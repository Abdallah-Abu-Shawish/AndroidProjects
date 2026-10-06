package com.example.examapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ExamDataBase extends SQLiteOpenHelper {
    Context context;
    private final static String DATABASE_NAME = "exam.db";
    private static int DATABASE_VERSION = 6;

    public final static String QUESTIONS_TABLE = "questions";
    public final static String COLUMN_QUESTION_ID = "id_col";
    public final static String COLUMN_QUESTIONS = "questions_col";
    public final static String COLUMN_CHOICE1 = "choice1_col";
    public final static String COLUMN_CHOICE2 = "choice2_col";
    public final static String COLUMN_CHOICE3 = "choice3_col";
    public final static String COLUMN_CHOICE4 = "choice4_col";
    public final static String COLUMN_ANSWER_KEY = "answerKey_col";

    public ExamDataBase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String questions_table = "CREATE TABLE " + QUESTIONS_TABLE + "(" +
                COLUMN_QUESTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_QUESTIONS + " TEXT NOT NULL,"+
                COLUMN_CHOICE1 + " TEXT NOT NULL,"+
                COLUMN_CHOICE2 + " TEXT NOT NULL," +
                COLUMN_CHOICE3 + " TEXT NOT NULL," +
                COLUMN_CHOICE4 + " TEXT NOT NULL," +
                COLUMN_ANSWER_KEY + " INTEGER NOT NULL)";

        db.execSQL(questions_table);

//        if (fillInDatabase()){
//            Log.d("Questions have been inserted", "insert questions");
//        }
    }

    public void fillInDatabase(){
        ArrayList<Question> questionsList = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        String[] questionsTexts = {
                "Which of the following is true about Intents in Android?",
                "What is the primary purpose of an implicit Intent?",
                "Which method is used to start a new activity using an Intent?",
                "What method would you use to pass data between activities?",
                "What is a Bundle in Android?",
                "How do you put a string into a Bundle?",
                "To retrieve an integer from a Bundle, you use:",
                "What is the main use of Bundle in an Activity?",
                "What method is used to retrieve data from a Bundle?",
                "Which method is called when an activity is first created?",
                "Which lifecycle method is called when an activity becomes visible to the user?",
                "Which method is called when an activity is about to go into the background?",
                "Which lifecycle method is called just before an activity is destroyed?",
                "What method is called when an activity restarts after being stopped?",
                "Which listener is used to handle a button click in Android?",
                "Which event is triggered when a button is clicked?",
                "Which listener is used to detect touch events in Android?",
                "What method must be implemented when using onClickListener?",
                "Which listener is used to handle long click events?",
                "Which component is more memory-efficient for handling large lists?",
                "Which method is used to bind data to a RecyclerView?",
                "Which LayoutManager positions items in a grid?",
                "Which adapter is typically used with ListView?",
                "To define how RecyclerView items look, which file is used?",
                "Which method is used to start a new activity and expect a result back?",
                "What method is used to handle the result of an activity?",
                "Which flag can be used with Intents to clear the activity stack?",
                "Which constant is used to indicate a successful result?",
                "Which class is used to handle SQLite databases in Android?",
                "Which method is called to create a new database?",
                "Which command is used to insert data in an SQLite database?",
                "To retrieve data from an SQLite database, you use:",
                "What method is used to upgrade a database in Android?",
                "Which method is used to delete data from an SQLite database?",
                "Which method is used to execute a raw SQL statement in SQLite?",
                "What data type is NOT supported by SQLite?",
                "What are SharedPreferences used for in Android?",
                "Which method is used to get an instance of SharedPreferences?",
                "What method is used to write data to SharedPreferences?",
                "What type of data can be stored in SharedPreferences?",
                "How do you remove a value from SharedPreferences?",
                "Which method is used to apply changes in SharedPreferences?",
                "What’s the difference between apply() and commit() in SharedPreferences?",
                "Which method removes all data in SharedPreferences?",
                "Where are SharedPreferences stored?",
                "In Android, which file is used to declare app permissions?",
                "What is R.java used for in Android?",
                "Which XML attribute is used to define an element's ID?",
                "Which component manages UI rendering in Android?",
                "What is the primary language for Android development?",
                "Which Android component is primarily used for background tasks?",
                "Which file is used to store string resources in Android?",
                "What is the purpose of Logcat in Android Studio?",
                "What SQL command is used to retrieve data from an SQLite database?",
                "Which SQLite data type is used to store true or false values?",
                "Which method is used to read data from an SQLite database with a specific query?",
                "What is the role of the ContentValues class in SQLite operations?",
                "What happens if you attempt to insert data with a primary key that already exists in SQLite?",
                "In Android’s SQLite, what method is used to get the number of rows affected by an operation?",
                "What object is used to iterate through results retrieved from an SQLite query?" };

        String[] questionsChoice1 = {
                "They are used to navigate between activities.",
                "To perform an action without specifying the component.",
                "onStartActivity()",
                "addExtras()",
                "A storage unit for large files",
                "bundle.addString()",
                "getInteger()",
                "To handle HTTP requests",
                "get()",
                "onStart()",
                "onCreate()",
                "onPause()",
                "onPause()",
                "onRestart()",
                "onKeyListener",
                "onClick",
                "onClickListener",
                "onClick(View v)",
                "onClickListener",
                "ListView",
                "onBind()",
                "GridLayoutManager",
                "ArrayAdapter",
                "XML layout file",
                "startActivity()",
                "onResultReceived()",
                "FLAG_CLEAR_TOP",
                "RESULT_OK",
                "SQLiteOpenHelper",
                "onCreateDatabase()",
                "insert()",
                "get()",
                "onUpgrade()",
                "remove()",
                "execute()",
                "INTEGER",
                "For storing files",
                "getSharedPreferences()",
                "putData()",
                "String only",
                "delete()",
                "save()",
                "apply() is asynchronous; commit() is synchronous",
                "clearAll()",
                "External storage",
                "config.xml",
                "To define all resources in an app",
                "idName",
                "MainActivity",
                "C++",
                "Service",
                "strings.xml",
                "To show UI elements",
                "INSERT",
                "BOOLEAN",
                "rawQuery()",
                "It holds the connection to the database",
                "The row is replaced",
                "rowCount()",
                "ResultSet" };

        String[] questionsChoice2 = {
                "They are only used for sending data to the server.",
                "To define a specific activity to start.",
                "startActivity()",
                "putExtra()",
                "A way to pass data between components.",
                "bundle.putString()",
                "getInt()",
                "To save and retrieve data across configuration changes",
                "retrieve()",
                "onCreate()",
                "onStart()",
                "onStop()",
                "onStop()",
                "onResume()",
                "onClickListener",
                "onTouch",
                "onTouchListener",
                "handleClick(View v)",
                "onTouchListener",
                "ScrollView",
                "onCreateViewHolder()",
                "LinearLayoutManager",
                "RecyclerAdapter",
                "Adapter class",
                "launchActivity()",
                "onActivityResult()",
                "FLAG_REMOVE_TOP",
                "RESULT_SUCCESS",
                "DatabaseHelper",
                "onCreate()",
                "add()",
                "query()",
                "upgradeDatabase()",
                "delete()",
                "execSQL()",
                "FLOAT",
                "For storing small amounts of data persistently",
                "retrievePreferences()",
                "saveData()",
                "Primitive data types (e.g., String, int, boolean)",
                "remove()",
                "commit()",
                "commit() is asynchronous; apply() is synchronous",
                "reset()",
                "Internal storage",
                "permissions.xml",
                "To manage the main activity",
                "android:name",
                "Layout Manager",
                "Java",
                "Broadcast Receiver",
                "resources.xml",
                "To view logs and debugging information",
                "UPDATE",
                "INTEGER (0 and 1)",
                "queryData()",
                "It provides key-value pairs for data insertion and updates",
                "An exception is thrown",
                "getColumnCount() in Cursor",
                "Cursor" };

        String[] questionsChoice3 = {
                "They can only be used within the same app.",
                "To interact with only local components.",
                "launchActivity()",
                "startActivity()",
                "A component that handles network requests.",
                "bundle.setString()",
                "getValue()",
                "To store background tasks",
                "putExtra()",
                "onResume()",
                "onResume()",
                "onDestroy()",
                "onDestroy()",
                "onCreate()",
                "onTouchListener",
                "onKey",
                "onEventListener",
                "viewClicked(View v)",
                "onLongClickListener",
                "RecyclerView",
                "onBindViewHolder()",
                "StaggeredGridLayoutManager",
                "CustomAdapter",
                "ViewModel file",
                "startActivityForResult()",
                "onReturnData()",
                "FLAG_CLEAR_ALL",
                "RESULT_COMPLETE",
                "SQLiteHelper",
                "initializeDatabase()",
                "put()",
                "select()",
                "updateDatabase()",
                "erase()",
                "runSQL()",
                "DATE",
                "For managing database connections",
                "openPreferences()",
                "edit()",
                "Complex objects",
                "clear()",
                "apply()",
                "Both are the same",
                "deleteAll()",
                "Database",
                "AndroidManifest.xml",
                "To specify permissions",
                "android:id",
                "ViewGroup",
                "Swift",
                "Activity",
                "text.xml",
                "To manage layouts",
                "DELETE",
                "TINYINT",
                "selectQuery()",
                "It defines the structure of the database",
                "The new data is ignored",
                "numRows()",
                "QueryResult"};

        String[] questionsChoice4 = {
                "None of the above.",
                "None of the above.",
                "createActivity()",
                "setIntent()",
                "A debugging tool.",
                "bundle.insertString()",
                "getNumber()",
                "None of the above",
                "getX()",
                "onRestart()",
                "onPause()",
                "onExit()",
                "onExit()",
                "onStart()",
                "onPressListener",
                "onPress",
                "onPressListener",
                "click(View v)",
                "onDoubleClickListener",
                "ArrayAdapter",
                "setAdapter()",
                "GridAdapter",
                "BaseAdapter",
                "Java class",
                "beginActivity()",
                "onRequestResult()",
                "FLAG_CLEAR_STACK",
                "RESULT_FINISHED",
                "SQLHelper",
                "createDatabase()",
                "store()",
                "retrieve()",
                "refreshDatabase()",
                "drop()",
                "processSQL()",
                "TEXT",
                "For rendering the UI",
                "getPreferences()",
                "storeData()",
                "XML files",
                "erase()",
                "store()",
                "apply() doesn’t save data",
                "clear()",
                "Cache",
                "settings.xml",
                "To create custom layouts",
                "android:ID",
                "RecyclerView",
                "Ruby",
                "Content Provider",
                "content.xml",
                "To edit XML files",
                "SELECT",
                "BOOL",
                "fetch()",
                "It retrieves all rows from a table",
                "The entire table is cleared",
                "getCount() in Cursor",
                "DataResult"};


        int[] questionsAnswersKey = { 1,1,2,2,2,2,2,2,4,2,2,1,3,1,2,1,2,1,3,3,3,1,1,1,3,2,1,1,1,2,
                                     1,2,1,2,2,3,2,1,3,2,2,3,1,4,2,3,1,3,3,2,1,1,2,4,2,1,2,2,4,2 };




        for (int i = 0; i < 60; i++) {
            values.put(COLUMN_QUESTIONS, questionsTexts[i]);
            values.put(COLUMN_CHOICE1, questionsChoice1[i]);
            values.put(COLUMN_CHOICE2, questionsChoice2[i]);
            values.put(COLUMN_CHOICE3, questionsChoice3[i]);
            values.put(COLUMN_CHOICE4, questionsChoice4[i]);
            values.put(COLUMN_ANSWER_KEY, questionsAnswersKey[i]);
            db.insert(QUESTIONS_TABLE, null, values);
        }
        Log.d("in database", "Database has been populated");
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QUESTIONS_TABLE);
        onCreate(db);
    }
//     @SuppressLint("Range")
//    public ArrayList<Question> generateQuestionsForExam() {
//        SQLiteDatabase db = getReadableDatabase();
//        ArrayList<Question> questions = new ArrayList<>();
//        List<Integer> indexes = generateUniqueRandomIndexes(20, 60);
//        if (indexes != null){
//            Log.d("indexes not null", "not null");
//            //Toast.makeText(context, "not null", Toast.LENGTH_SHORT).show();
//        }else {
//            Log.d("indexes is null", "is null");
//            //Toast.makeText(context, "is null", Toast.LENGTH_SHORT).show();
//        }
//           StringBuilder arr = new StringBuilder();
//
//           for (int index : indexes) {
//               arr.append(index).append(" ");
//           }
//         // Toast.makeText(context, arr, Toast.LENGTH_SHORT).show();
//
//        String[] args = convertToStringArray(indexes);
//        String[] args2 = {args[0]};
//
//           if (args2 != null){
//               Log.d("args2 not null", "not null");
//               //Toast.makeText(context, "not null", Toast.LENGTH_SHORT).show();
//           }else {
//               Log.d("args2 is null", "is null");
//               //Toast.makeText(context, "is null", Toast.LENGTH_SHORT).show();
//           }
//        Cursor cursor = null;
//
//               cursor = db.rawQuery("Select * from " + QUESTIONS_TABLE + " Where "+ COLUMN_QUESTION_ID + "=?",args2);
//               while (cursor.moveToNext()){
//                   Question q = new Question();
//                   q.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_QUESTION_ID)));
//                   q.setQuestion(cursor.getString(cursor.getColumnIndex(COLUMN_QUESTIONS)));
//                   q.setChoice1(cursor.getString(cursor.getColumnIndex(COLUMN_CHOICE1)));
//                   q.setChoice2(cursor.getString(cursor.getColumnIndex(COLUMN_CHOICE2)));
//                   q.setChoice3(cursor.getString(cursor.getColumnIndex(COLUMN_CHOICE3)));
//                   q.setChoice4(cursor.getString(cursor.getColumnIndex(COLUMN_CHOICE4)));
//                   q.setAnswerKey(cursor.getInt(cursor.getColumnIndex(COLUMN_ANSWER_KEY)));
//                   questions.add(q);
//
//               }
//       // Toast.makeText(context, cursor.getCount()+"", Toast.LENGTH_SHORT).show();
//        cursor.close();
//        return questions;
//       }

//
//    private String[] convertToStringArray(List<Integer> indexes) {
//        String[] args = new String[indexes.size()];
//           for (int i = 0; i < indexes.size(); i++) {
//               args[i] = indexes.get(i)+"";
//           }
//           return args;
//    }


    @SuppressLint("Range")
    public List<Question> getAllQuestions(){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Question> questions = new ArrayList<>();
        Cursor cursor = db.rawQuery("Select * from "+QUESTIONS_TABLE, null);
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

        cursor.close();
        return questions;
    }



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

    public List<Question> getRandomQuestions(List<Question> allQuestions, int size){
        List<Question> questionListCopy = new ArrayList<>(allQuestions);
        Collections.shuffle(questionListCopy);
        int numberOfQuestions = Math.min(size, questionListCopy.size());
        return  questionListCopy.subList(0, numberOfQuestions);

    }

//    public static List<Integer> generateUniqueRandomIndexes(int numOfIndexes, int size) {
//        // Check if the number of indexes requested is valid
//        if (numOfIndexes > size) {
//            throw new IllegalArgumentException("Number of indexes requested exceeds the available range.");
//        }
//
//        // Create a list of indexes from 1 to size
//        List<Integer> indexes = new ArrayList<>();
//        for (int i = 1; i <= size; i++) {
//            indexes.add(i);
//        }
//
//        // Shuffle the list to randomize the order
//        Collections.shuffle(indexes);
//
//        // Return only the first numOfIndexes elements
//        return indexes.subList(0, numOfIndexes);
//    }

//    public static ArrayList<Integer> generateRandomIndexes(int numOfQ , int numOfAllQuestions){
//        ArrayList<Integer> randomIndexes = new ArrayList<>();
//        if (numOfQ <= numOfAllQuestions) {
//            int temp= -1;
//            int random = 0;
//            for (int i = 0; i < numOfQ; i++) {
//                do {
//                    random = (int) (Math.random() * numOfAllQuestions);
//                } while (random == temp || randomIndexes.contains(random));
//                randomIndexes.add(random);
//                temp = random;
//            }
//            return randomIndexes;
//        }
//        return null;
//    }


    public int getAllQuestionsCount(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + QUESTIONS_TABLE, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }


}
