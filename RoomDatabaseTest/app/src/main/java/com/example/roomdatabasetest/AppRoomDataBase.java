package com.example.roomdatabasetest;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Question.class, User.class, UserScore.class, UserIn.class}, version = 1,exportSchema = false)
@TypeConverters({UriTypeConverter.class})
public abstract class AppRoomDataBase extends RoomDatabase {

    public abstract QuestionDao questionDao();
    public  abstract UserDao userDao();
    public abstract UserScoreDao userScoreDao();
    public abstract UserInDao userInDao();


    private static volatile AppRoomDataBase INSTANCE;

    static AppRoomDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppRoomDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppRoomDataBase.class, "app_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .allowMainThreadQueries()
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
            //databaseWriteExecutor.execute(() -> {
            // Populate the database in the background.
            // If you want to start with more words, just add them.
            new PopulateTask(INSTANCE).execute();
            // });
        }
    };

    private static class PopulateTask extends AsyncTask<Void,Void,Void> {
        private QuestionDao questionDao;
        private UserDao userDao;
        private UserScoreDao userScoreDao;
        private UserInDao userInDao;
        public PopulateTask(AppRoomDataBase db) {
            questionDao = db.questionDao();
            userDao = db.userDao();
            userScoreDao = db.userScoreDao();
            userInDao = db.userInDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
//            noteDao.insertNote(new Note("Title 1","description 1",1));
//            noteDao.insertNote(new Note("Title 2","description 2",2));
//            noteDao.insertNote(new Note("Title 3","description 3",3));
            return null;
        }
    }

}
