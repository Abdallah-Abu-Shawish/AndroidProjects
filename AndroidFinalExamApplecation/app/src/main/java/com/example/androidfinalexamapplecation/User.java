package com.example.androidfinalexamapplecation;

import android.graphics.Bitmap;

public class User {

    private int id;
    private String name;
    private String email;
    private String password;
    private String specalize;
    private String gender;
    private Bitmap image;

    public User() {

    }

    public User(String name, String email, String password,
                String specalize, String gender, Bitmap image) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.specalize = specalize;
        this.gender = gender;
        this.image = image;
    }

    public String getSpecalize() {
        return specalize;
    }

    public void setSpecalize(String specalize) {
        this.specalize = specalize;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
