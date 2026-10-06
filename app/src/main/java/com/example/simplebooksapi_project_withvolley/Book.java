package com.example.simplebooksapi_project_withvolley;

public class Book {

    private int id;
    private String name;
    private String author;
    private String type;
    private double price;
    private int currentStock;
    private boolean available;


    public Book() {
    }

    public Book(int id, String name, String type, boolean available) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.available = available;
    }

    public Book(int id, String name, String author, String type, double price, int currentStock, boolean available) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.type = type;
        this.price = price;
        this.currentStock = currentStock;
        this.available = available;
    }

    public Book(String name, String author, String type, double price, int currentStock, boolean available) {
        this.name = name;
        this.author = author;
        this.type = type;
        this.price = price;
        this.currentStock = currentStock;
        this.available = available;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(int currentStock) {
        this.currentStock = currentStock;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
