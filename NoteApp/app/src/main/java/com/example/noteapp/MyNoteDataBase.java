package com.example.noteapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyNoteDataBase extends SQLiteOpenHelper {


    public static final String DB_NAME = "noteDB";
    public final static int DB_VERSION = 1;
    public final static String TABLE_NOTE = "noteTable";
    public final static String COLUMN_NOTE_ID = "noteID";
    public final static String COLUMN_NOTE_TITLE = "noteTitle";
    public final static String COLUMN_NOTE_TEXT = "noteText";
    public final static String COLUMN_NOTE_DATE = "noteDate";
    public final static String COLUMN_NOTE_TIME = "noteTime";





    public MyNoteDataBase(@Nullable Context context) {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String table_note = "create table "+TABLE_NOTE+" ( "+
                COLUMN_NOTE_ID+" integer primary key autoincrement ,"+
                COLUMN_NOTE_TITLE+" text not null ,"+
                COLUMN_NOTE_TEXT+" text not null ,"+
                COLUMN_NOTE_DATE+" text not null ,"+
                COLUMN_NOTE_TIME+" text not null "+" ) ";
        db.execSQL(table_note);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TABLE_NOTE);
    }


    public boolean insertNote(Note note){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOTE_TITLE,note.getTitle());
        values.put(COLUMN_NOTE_TEXT,note.getText());
        values.put(COLUMN_NOTE_DATE,note.getDate());
        values.put(COLUMN_NOTE_TIME,note.getTime());
        long result = database.insert(TABLE_NOTE,null,values);
        return result != 0;
    }



    public Cursor getNotes(){
        SQLiteDatabase database = this.getReadableDatabase();
        return database.rawQuery("select * from "+TABLE_NOTE,null);
    }

    public boolean updateNote(Note note){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOTE_TITLE,note.getTitle());
        values.put(COLUMN_NOTE_TEXT,note.getText());
        values.put(COLUMN_NOTE_DATE,note.getDate());
        values.put(COLUMN_NOTE_TIME,note.getTime());
        String[] args = {note.getId()+""};
        int result = database.update(TABLE_NOTE,values,COLUMN_NOTE_ID+" =? ",args);
        return result != 0;
    }


    public boolean deleteNote(int id){
        SQLiteDatabase database = this.getWritableDatabase();
        String[] args = {id+""};
        int result = database.delete(TABLE_NOTE,COLUMN_NOTE_ID+" =? ",args);
        return result > 0;
    }

    @SuppressLint("Range")
    public ArrayList<Note> getAllNotes(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE_NOTE,null);
        ArrayList<Note> notesList = new ArrayList<>();
        while (cursor.moveToNext()){
            Note note = new Note();
            note.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_NOTE_ID)));
            note.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_NOTE_TITLE)));
            note.setText(cursor.getString(cursor.getColumnIndex(COLUMN_NOTE_TEXT)));
            note.setDate(cursor.getString(cursor.getColumnIndex(COLUMN_NOTE_DATE)));
            note.setTime(cursor.getString(cursor.getColumnIndex(COLUMN_NOTE_TIME)));
            notesList.add(note);
        }
        cursor.close();
        return notesList;
    }




    @SuppressLint("Range")
    public ArrayList<Note> searchForNote(String title){
        SQLiteDatabase database = this.getReadableDatabase();
        ArrayList<Note> notes = new ArrayList<>();
        String[] args = {title};
        Cursor cursor = database.rawQuery("select * from "+TABLE_NOTE + " where "+COLUMN_NOTE_TITLE + "=?",args);
        while (cursor.moveToNext()){
            Note note = new Note();
            note.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_NOTE_TITLE)));
            note.setText(cursor.getString(cursor.getColumnIndex(COLUMN_NOTE_TEXT)));
            note.setDate(cursor.getString(cursor.getColumnIndex(COLUMN_NOTE_DATE)));
            note.setTime(cursor.getString(cursor.getColumnIndex(COLUMN_NOTE_TIME)));
            notes.add(note);
        }
        cursor.close();
        return notes;
    }

    @SuppressLint("Range")
    public Note getNoteByID(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Note note = new Note();
        Cursor cursor = db.rawQuery("select * from "+TABLE_NOTE +" where "+COLUMN_NOTE_ID +" = "+id,null);
        while (cursor.moveToNext()){
            note.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_NOTE_TITLE)));
            note.setText(cursor.getString(cursor.getColumnIndex(COLUMN_NOTE_TEXT)));
        }

        cursor.close();
        return note;
    }





















}
