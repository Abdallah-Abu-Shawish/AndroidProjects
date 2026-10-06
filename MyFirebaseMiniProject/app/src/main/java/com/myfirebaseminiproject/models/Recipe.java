package com.myfirebaseminiproject.models;

public class Recipe {
    private String id;
    private String title;
    private String ingredients;
    private String steps;
    private String category;
    private String imageUrl;
    private String videoUrl;
    private String userId;
    private long timestamp;

    public Recipe() {}

    public Recipe(String id, String title, String ingredients, String steps, String category, String imageUrl, String videoUrl, String userId, long timestamp) {
        this.id = id;
        this.title = title;
        this.ingredients = ingredients;
        this.steps = steps;
        this.category = category;
        this.imageUrl = imageUrl;
        this.videoUrl = videoUrl;
        this.userId = userId;
        this.timestamp = timestamp;
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getIngredients() { return ingredients; }
    public void setIngredients(String ingredients) { this.ingredients = ingredients; }
    public String getSteps() { return steps; }
    public void setSteps(String steps) { this.steps = steps; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public String getVideoUrl() { return videoUrl; }
    public void setVideoUrl(String videoUrl) { this.videoUrl = videoUrl; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
} 