package com.example.test.recicleview;

public class Game {

    private String name;
    private String rate;
    private String specialName;
    private int image;
    private int quntity = 1;

    public Game(String name,String specialName ,String rate, int image) {
        this.name = name;
        this.rate = rate;
        this.specialName = specialName;
        this.image = image;
    }




    public String getSpecialName() {
        return specialName;
    }

    public void setSpecialName(String specialName) {
        this.specialName = specialName;
    }

    public int getQuntity() {
        return quntity;
    }

    public void setQuntity(int quntity) {
        this.quntity = quntity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }


    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
