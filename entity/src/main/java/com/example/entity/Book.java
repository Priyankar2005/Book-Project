package com.example.entity;

public class Book {
    private String title;
    private String author;
    private String category;

    // Default constructor
    public Book() {
    }

    // All-args constructor
    public Book(String title, String author, String category) {
        this.title = title;
        this.author = author;
        this.category = category;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    // Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

