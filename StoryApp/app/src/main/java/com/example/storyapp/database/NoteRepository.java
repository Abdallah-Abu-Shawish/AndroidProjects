package com.example.storyapp.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {

    private NoteDao noteDao;
    LiveData<List<Note>> allNotes;

    public NoteRepository(Application application) {
        NoteRoomDatabase noteDatabase = NoteRoomDatabase.getDatabase(application);
        noteDao = noteDatabase.NoteDao();
        allNotes = noteDao.getAllNotes();
    }

    public void insertNote(Note note) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                noteDao.insertNote(note);
            }
        }).start();
    }

    public void updateNote(Note note) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                noteDao.updateNote(note);
            }
        }).start();
    }

    public void deleteNote(Note note) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                noteDao.deleteNote(note);
            }
        }).start();
    }

    public void deleteAllNotes() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                noteDao.deleteAllNotes();
            }
        }).start();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }






}
