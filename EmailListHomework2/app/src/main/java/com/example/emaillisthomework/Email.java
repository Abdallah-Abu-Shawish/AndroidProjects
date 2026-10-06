package com.example.emaillisthomework;

public class Email {


    private String name;
    private String title;
    private String text;
    private String time;
    private String firstLetter;

    public Email( String name, String title, String text, String time) {

        this.name = name;
        this.title = title;
        this.text = text;
        this.time = time;
        this.firstLetter = this.getName().substring(0,1);
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }
}
