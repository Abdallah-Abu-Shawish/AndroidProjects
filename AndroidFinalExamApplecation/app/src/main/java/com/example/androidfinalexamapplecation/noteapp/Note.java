package com.example.androidfinalexamapplecation.noteapp;

import android.graphics.Bitmap;

public class Note {

    private int id;
    private String title;
    private String text;
    private String date;
    private String time;
    private Bitmap bitmap;



    public Note() {

    }

    public Note(String title, String text, String date, String time, Bitmap bitmap) {
        this.title = title;
        this.text = text;
        this.date = date;
        this.time = time;
        this.bitmap = bitmap;
    }

    public Note(String title, String text, String date, String time) {
        this.title = title;
        this.text = text;
        this.date = date;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
