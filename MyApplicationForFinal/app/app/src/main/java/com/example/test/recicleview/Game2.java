package com.example.test.recicleview;

public class Game2 {

    private String name;
    private String rate;
    private String rate2;
    private String newBrand;
    private int image;
    private int offerQuntity = 1;


    public Game2(String name, String rate, String rate2, int image) {
        this.name = name;
        this.rate = rate;
        this.rate2 = rate2;
        this.newBrand = "New\nBrand";
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOfferQuntity() {
        return offerQuntity;
    }

    public void setOfferQuntity(int offerQuntity) {
        this.offerQuntity = offerQuntity;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getNewBrand() {
        return newBrand;
    }

    public String getRate2() {
        return rate2;
    }

    public void setRate2(String rate2) {
        this.rate2 = rate2;
    }

    public void setNewBrand(String newBrand) {
        this.newBrand = newBrand;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
