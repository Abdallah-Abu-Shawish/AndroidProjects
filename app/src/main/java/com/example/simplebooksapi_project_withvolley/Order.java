package com.example.simplebooksapi_project_withvolley;

public class Order {

        private String id;
        private int bookId;
        private String customerName;
        private String createdBy; // "createdBy": "5d99fd7e313344ebc163b88280c806ddcdca297b999474385d11fc74f8e57730",
        private int quantity;
        private double timestamp;


    public Order(String id, int bookId, String customerName, String createdBy, int quantity, double timestamp) {
        this.id = id;
        this.bookId = bookId;
        this.customerName = customerName;
        this.createdBy = createdBy;
        this.quantity = quantity;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(double timestamp) {
        this.timestamp = timestamp;
    }
}
