package com.example.test;

import android.graphics.Bitmap;

public class Product {

    private int id;
    private String name;
    private String type;
    private int price;
    private int quntity;
    private String description;
    private Bitmap productImage;

    public Product() {
    }

    public Product( String name, String type, int price, int quntity, String description, Bitmap productImage) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.quntity = quntity;
        this.description = description;
        this.productImage = productImage;
    }

    public Product(String name, String type, int price, int quntity, String description) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.quntity = quntity;
        this.description = description;
    }

    public Bitmap getProductImage() {
        return productImage;
    }

    public void setProductImage(Bitmap productImage) {
        this.productImage = productImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuntity() {
        return quntity;
    }

    public void setQuntity(int quntity) {
        this.quntity = quntity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
