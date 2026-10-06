package com.example.roomdatabasetest;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    AppRoomDataBase dataBase;
    boolean flag = true;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        dataBase = AppRoomDataBase.getDatabase(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        v.findViewById(R.id.btn_go_to_exam).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                //Toast.makeText(WelcomeActivity.this, "clicked / go to Main", Toast.LENGTH_SHORT).show();
                Log.d("In Click", "btn clicked");
                if (flag){
                    fillInDatabase(); //Using RoomDatabase
                    flag = false;
                }

                startActivity(intent);
                onDestroyView();
            }
        });
        User user = dataBase.userDao().getUser(dataBase.userInDao().getAllUsersIn().getUserIn_id());
        TextView textView = v.findViewById(R.id.textView);
        textView.setText("Hi "+user.getName()+" Test your self in Android Development 1");

        return v;
    }
    public void fillInDatabase(){
        ArrayList<Question> questionsList = new ArrayList<>();
        //SQLiteDatabase db = getWritableDatabase();
        //ContentValues values = new ContentValues();
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
//            values.put(COLUMN_QUESTIONS, questionsTexts[i]);
//            values.put(COLUMN_CHOICE1, questionsChoice1[i]);
//            values.put(COLUMN_CHOICE2, questionsChoice2[i]);
//            values.put(COLUMN_CHOICE3, questionsChoice3[i]);
//            values.put(COLUMN_CHOICE4, questionsChoice4[i]);
//            values.put(COLUMN_ANSWER_KEY, questionsAnswersKey[i]);
//            db.insert(QUESTIONS_TABLE, null, values);
            dataBase.questionDao().insertQuestion(new Question(questionsTexts[i],questionsChoice1[i],questionsChoice2[i],questionsChoice3[i],questionsChoice4[i],questionsAnswersKey[i]));
        }
        Log.d("in database", "Database has been populated");
    }
}