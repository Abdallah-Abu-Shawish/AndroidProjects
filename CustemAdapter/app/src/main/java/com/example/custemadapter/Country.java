package com.example.custemadapter;

public class Country {

    private String name;
    private int image;

    public Country(String name , int image) {
        this.setName(name);
        this.setImage(image);
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
