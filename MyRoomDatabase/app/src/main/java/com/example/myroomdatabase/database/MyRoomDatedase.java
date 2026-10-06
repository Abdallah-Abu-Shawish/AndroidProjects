package com.example.myroomdatabase.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Employee.class,Salary.class}, version = 1, exportSchema = false)
public abstract class MyRoomDatedase extends RoomDatabase {

    public abstract EmployeeDAO employeeDao();
    public abstract SalaryDAO salaryDao();
    private static volatile MyRoomDatedase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static MyRoomDatedase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MyRoomDatedase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    MyRoomDatedase.class, "employees_database")
                                    .addCallback(sRoomDatabaseCallback)
                                    .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.

            });
        }
    };

}