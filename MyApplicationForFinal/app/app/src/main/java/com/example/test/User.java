package com.example.test;

import android.graphics.Bitmap;

public class User {

    private int id;
    private String name;
    private String userName;
    private String email;
    private String password;
    private String country;
    private String gender;
    private Bitmap image;

    public User() {

    }

    public User( String name, String userName, String email, String password,
                String country, String gender, Bitmap image) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.country = country;
        this.gender = gender;
        this.image = image;
    }

    public User(String userName , String password) {
        this.userName = userName;
        this.password = password;
    }

    public User(String name, String userName, String email, String password, String country, String gender) {
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.country = country;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
