package com.example.roomdatabasetest;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.roomdatabasetest.databinding.ActivityWelcomeBinding;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class WelcomeActivity extends AppCompatActivity {
    ActivityWelcomeBinding binding;
    AppRoomDataBase db;
    FragmentManager manager;

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

        db = AppRoomDataBase.getDatabase(this);
        //Toast.makeText(this, "in onCreate", Toast.LENGTH_SHORT).show();

        manager = getSupportFragmentManager();

//        binding.btnGoToExam.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
//                //Toast.makeText(WelcomeActivity.this, "clicked / go to Main", Toast.LENGTH_SHORT).show();
//                Log.d("In Click", "btn clicked");
//                // fillInDatabase(); Using RoomDatabase
//                //db.fillInDatabase(); Using SQLite Database
//                startActivity(intent);
//                finish();
//            }
//        });
        binding.tabBar.setSelectedItemId(R.id.item_home);
        addFragment(new HomeFragment());
        binding.tabBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.item_home){
                    addFragment(new HomeFragment());
//                        return true;
                } else if (item.getItemId() == R.id.item_scores) {
//                        Intent intent2 = new Intent(WelcomeActivity.this , ShowAllScoresActivity.class);
//                        startActivity(intent2);
                        addFragment(new ShowAllScoresFragment());
                        //return true;
                } else if (item.getItemId() == R.id.item_profile) {
//                        Intent intent3 = new Intent(WelcomeActivity.this , ProfileActivity.class);
//                        startActivity(intent3);
                        addFragment(new ProfileFragment());
                        //return true;
                }
                return true;
            }
        });

//        binding.tabBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
//            @SuppressLint("NonConstantResourceId")
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()){
//
//                    case R.id.item_home:
//                        return true;
//
//                    case R.id.item_scores :
//                        Intent intent2 = new Intent(WelcomeActivity.this , ShowAllScoresActivity.class);
//                        startActivity(intent2);
//                        return true;
//
//                    case R.id.item_profile:
//                        Intent intent3 = new Intent(WelcomeActivity.this , ProfileActivity.class);
//                        startActivity(intent3);
//                        return true;
//
//                }
//                return false;
//
//            }
//        });

//        binding.btnGoToExam.setOnClickListener(v -> {
//
//
//
//        });

    }

    public void addFragment(Fragment fragment){
        manager.beginTransaction().replace(R.id.fr_container,fragment).commit();
    }

//    public void fillInDatabase(){
//        ArrayList<Question> questionsList = new ArrayList<>();
//        //SQLiteDatabase db = getWritableDatabase();
//        //ContentValues values = new ContentValues();
//        String[] questionsTexts = {
//                "Which of the following is true about Intents in Android?",
//                "What is the primary purpose of an implicit Intent?",
//                "Which method is used to start a new activity using an Intent?",
//                "What method would you use to pass data between activities?",
//                "What is a Bundle in Android?",
//                "How do you put a string into a Bundle?",
//                "To retrieve an integer from a Bundle, you use:",
//                "What is the main use of Bundle in an Activity?",
//                "What method is used to retrieve data from a Bundle?",
//                "Which method is called when an activity is first created?",
//                "Which lifecycle method is called when an activity becomes visible to the user?",
//                "Which method is called when an activity is about to go into the background?",
//                "Which lifecycle method is called just before an activity is destroyed?",
//                "What method is called when an activity restarts after being stopped?",
//                "Which listener is used to handle a button click in Android?",
//                "Which event is triggered when a button is clicked?",
//                "Which listener is used to detect touch events in Android?",
//                "What method must be implemented when using onClickListener?",
//                "Which listener is used to handle long click events?",
//                "Which component is more memory-efficient for handling large lists?",
//                "Which method is used to bind data to a RecyclerView?",
//                "Which LayoutManager positions items in a grid?",
//                "Which adapter is typically used with ListView?",
//                "To define how RecyclerView items look, which file is used?",
//                "Which method is used to start a new activity and expect a result back?",
//                "What method is used to handle the result of an activity?",
//                "Which flag can be used with Intents to clear the activity stack?",
//                "Which constant is used to indicate a successful result?",
//                "Which class is used to handle SQLite databases in Android?",
//                "Which method is called to create a new database?",
//                "Which command is used to insert data in an SQLite database?",
//                "To retrieve data from an SQLite database, you use:",
//                "What method is used to upgrade a database in Android?",
//                "Which method is used to delete data from an SQLite database?",
//                "Which method is used to execute a raw SQL statement in SQLite?",
//                "What data type is NOT supported by SQLite?",
//                "What are SharedPreferences used for in Android?",
//                "Which method is used to get an instance of SharedPreferences?",
//                "What method is used to write data to SharedPreferences?",
//                "What type of data can be stored in SharedPreferences?",
//                "How do you remove a value from SharedPreferences?",
//                "Which method is used to apply changes in SharedPreferences?",
//                "What’s the difference between apply() and commit() in SharedPreferences?",
//                "Which method removes all data in SharedPreferences?",
//                "Where are SharedPreferences stored?",
//                "In Android, which file is used to declare app permissions?",
//                "What is R.java used for in Android?",
//                "Which XML attribute is used to define an element's ID?",
//                "Which component manages UI rendering in Android?",
//                "What is the primary language for Android development?",
//                "Which Android component is primarily used for background tasks?",
//                "Which file is used to store string resources in Android?",
//                "What is the purpose of Logcat in Android Studio?",
//                "What SQL command is used to retrieve data from an SQLite database?",
//                "Which SQLite data type is used to store true or false values?",
//                "Which method is used to read data from an SQLite database with a specific query?",
//                "What is the role of the ContentValues class in SQLite operations?",
//                "What happens if you attempt to insert data with a primary key that already exists in SQLite?",
//                "In Android’s SQLite, what method is used to get the number of rows affected by an operation?",
//                "What object is used to iterate through results retrieved from an SQLite query?" };
//
//        String[] questionsChoice1 = {
//                "They are used to navigate between activities.",
//                "To perform an action without specifying the component.",
//                "onStartActivity()",
//                "addExtras()",
//                "A storage unit for large files",
//                "bundle.addString()",
//                "getInteger()",
//                "To handle HTTP requests",
//                "get()",
//                "onStart()",
//                "onCreate()",
//                "onPause()",
//                "onPause()",
//                "onRestart()",
//                "onKeyListener",
//                "onClick",
//                "onClickListener",
//                "onClick(View v)",
//                "onClickListener",
//                "ListView",
//                "onBind()",
//                "GridLayoutManager",
//                "ArrayAdapter",
//                "XML layout file",
//                "startActivity()",
//                "onResultReceived()",
//                "FLAG_CLEAR_TOP",
//                "RESULT_OK",
//                "SQLiteOpenHelper",
//                "onCreateDatabase()",
//                "insert()",
//                "get()",
//                "onUpgrade()",
//                "remove()",
//                "execute()",
//                "INTEGER",
//                "For storing files",
//                "getSharedPreferences()",
//                "putData()",
//                "String only",
//                "delete()",
//                "save()",
//                "apply() is asynchronous; commit() is synchronous",
//                "clearAll()",
//                "External storage",
//                "config.xml",
//                "To define all resources in an app",
//                "idName",
//                "MainActivity",
//                "C++",
//                "Service",
//                "strings.xml",
//                "To show UI elements",
//                "INSERT",
//                "BOOLEAN",
//                "rawQuery()",
//                "It holds the connection to the database",
//                "The row is replaced",
//                "rowCount()",
//                "ResultSet" };
//
//        String[] questionsChoice2 = {
//                "They are only used for sending data to the server.",
//                "To define a specific activity to start.",
//                "startActivity()",
//                "putExtra()",
//                "A way to pass data between components.",
//                "bundle.putString()",
//                "getInt()",
//                "To save and retrieve data across configuration changes",
//                "retrieve()",
//                "onCreate()",
//                "onStart()",
//                "onStop()",
//                "onStop()",
//                "onResume()",
//                "onClickListener",
//                "onTouch",
//                "onTouchListener",
//                "handleClick(View v)",
//                "onTouchListener",
//                "ScrollView",
//                "onCreateViewHolder()",
//                "LinearLayoutManager",
//                "RecyclerAdapter",
//                "Adapter class",
//                "launchActivity()",
//                "onActivityResult()",
//                "FLAG_REMOVE_TOP",
//                "RESULT_SUCCESS",
//                "DatabaseHelper",
//                "onCreate()",
//                "add()",
//                "query()",
//                "upgradeDatabase()",
//                "delete()",
//                "execSQL()",
//                "FLOAT",
//                "For storing small amounts of data persistently",
//                "retrievePreferences()",
//                "saveData()",
//                "Primitive data types (e.g., String, int, boolean)",
//                "remove()",
//                "commit()",
//                "commit() is asynchronous; apply() is synchronous",
//                "reset()",
//                "Internal storage",
//                "permissions.xml",
//                "To manage the main activity",
//                "android:name",
//                "Layout Manager",
//                "Java",
//                "Broadcast Receiver",
//                "resources.xml",
//                "To view logs and debugging information",
//                "UPDATE",
//                "INTEGER (0 and 1)",
//                "queryData()",
//                "It provides key-value pairs for data insertion and updates",
//                "An exception is thrown",
//                "getColumnCount() in Cursor",
//                "Cursor" };
//
//        String[] questionsChoice3 = {
//                "They can only be used within the same app.",
//                "To interact with only local components.",
//                "launchActivity()",
//                "startActivity()",
//                "A component that handles network requests.",
//                "bundle.setString()",
//                "getValue()",
//                "To store background tasks",
//                "putExtra()",
//                "onResume()",
//                "onResume()",
//                "onDestroy()",
//                "onDestroy()",
//                "onCreate()",
//                "onTouchListener",
//                "onKey",
//                "onEventListener",
//                "viewClicked(View v)",
//                "onLongClickListener",
//                "RecyclerView",
//                "onBindViewHolder()",
//                "StaggeredGridLayoutManager",
//                "CustomAdapter",
//                "ViewModel file",
//                "startActivityForResult()",
//                "onReturnData()",
//                "FLAG_CLEAR_ALL",
//                "RESULT_COMPLETE",
//                "SQLiteHelper",
//                "initializeDatabase()",
//                "put()",
//                "select()",
//                "updateDatabase()",
//                "erase()",
//                "runSQL()",
//                "DATE",
//                "For managing database connections",
//                "openPreferences()",
//                "edit()",
//                "Complex objects",
//                "clear()",
//                "apply()",
//                "Both are the same",
//                "deleteAll()",
//                "Database",
//                "AndroidManifest.xml",
//                "To specify permissions",
//                "android:id",
//                "ViewGroup",
//                "Swift",
//                "Activity",
//                "text.xml",
//                "To manage layouts",
//                "DELETE",
//                "TINYINT",
//                "selectQuery()",
//                "It defines the structure of the database",
//                "The new data is ignored",
//                "numRows()",
//                "QueryResult"};
//
//        String[] questionsChoice4 = {
//                "None of the above.",
//                "None of the above.",
//                "createActivity()",
//                "setIntent()",
//                "A debugging tool.",
//                "bundle.insertString()",
//                "getNumber()",
//                "None of the above",
//                "getX()",
//                "onRestart()",
//                "onPause()",
//                "onExit()",
//                "onExit()",
//                "onStart()",
//                "onPressListener",
//                "onPress",
//                "onPressListener",
//                "click(View v)",
//                "onDoubleClickListener",
//                "ArrayAdapter",
//                "setAdapter()",
//                "GridAdapter",
//                "BaseAdapter",
//                "Java class",
//                "beginActivity()",
//                "onRequestResult()",
//                "FLAG_CLEAR_STACK",
//                "RESULT_FINISHED",
//                "SQLHelper",
//                "createDatabase()",
//                "store()",
//                "retrieve()",
//                "refreshDatabase()",
//                "drop()",
//                "processSQL()",
//                "TEXT",
//                "For rendering the UI",
//                "getPreferences()",
//                "storeData()",
//                "XML files",
//                "erase()",
//                "store()",
//                "apply() doesn’t save data",
//                "clear()",
//                "Cache",
//                "settings.xml",
//                "To create custom layouts",
//                "android:ID",
//                "RecyclerView",
//                "Ruby",
//                "Content Provider",
//                "content.xml",
//                "To edit XML files",
//                "SELECT",
//                "BOOL",
//                "fetch()",
//                "It retrieves all rows from a table",
//                "The entire table is cleared",
//                "getCount() in Cursor",
//                "DataResult"};
//
//
//        int[] questionsAnswersKey = { 1,1,2,2,2,2,2,2,4,2,2,1,3,1,2,1,2,1,3,3,3,1,1,1,3,2,1,1,1,2,
//                1,2,1,2,2,3,2,1,3,2,2,3,1,4,2,3,1,3,3,2,1,1,2,4,2,1,2,2,4,2 };
//
//
//
//
//        for (int i = 0; i < 60; i++) {
////            values.put(COLUMN_QUESTIONS, questionsTexts[i]);
////            values.put(COLUMN_CHOICE1, questionsChoice1[i]);
////            values.put(COLUMN_CHOICE2, questionsChoice2[i]);
////            values.put(COLUMN_CHOICE3, questionsChoice3[i]);
////            values.put(COLUMN_CHOICE4, questionsChoice4[i]);
////            values.put(COLUMN_ANSWER_KEY, questionsAnswersKey[i]);
////            db.insert(QUESTIONS_TABLE, null, values);
//            db.questionDao().insertQuestion(new Question(questionsTexts[i],questionsChoice1[i],questionsChoice2[i],questionsChoice3[i],questionsChoice4[i],questionsAnswersKey[i]));
//        }
//        Log.d("in database", "Database has been populated");
//    }

}