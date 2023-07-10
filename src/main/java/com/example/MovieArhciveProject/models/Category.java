package com.example.MovieArhciveProject.models;

public enum Category {
    ACTION("Action"),
    DRAMA("Drama"),
    COMEDY("Comedy"),
    ROMANCE("Romance");

    private String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}