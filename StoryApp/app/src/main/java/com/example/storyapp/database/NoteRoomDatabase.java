package com.example.storyapp.database;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class NoteRoomDatabase extends RoomDatabase {

    public abstract NoteDao NoteDao();
    private static volatile NoteRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static NoteRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NoteRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    NoteRoomDatabase.class, "note_database")
                                    .fallbackToDestructiveMigration()
                                    .addCallback(sRoomDatabaseCallback)
                                    .build();
                }
            }
        }
        return INSTANCE;
    }

    private static Callback sRoomDatabaseCallback = new Callback() {
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

    private static class PopulateTask extends AsyncTask<Void,Void,Void>{
        private NoteDao noteDao;

        public PopulateTask(NoteRoomDatabase db) {
            noteDao = db.NoteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insertNote(new Note("Title 1","description 1",1));
            noteDao.insertNote(new Note("Title 2","description 2",2));
            noteDao.insertNote(new Note("Title 3","description 3",3));
            return null;
        }
    }
}