package com.example.android.rmsassignment;

import com.google.firebase.Timestamp;

public class User {
    private String name;
    private int age;
    private Timestamp createdDate;

    public User() {
    }

    public User(String name, int age, Timestamp createdDate) {
        this.name = name;
        this.age = age;
        this.createdDate = createdDate;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Timestamp getCreatedDate(){
        return createdDate;
    }
}
