package com.example.farm.controllers;

public class UserResponse {
    private String user_id;

    private long userId;

    private String name;
    private String email;
    private String phone;

    public String getUserId() {
        return user_id;
    }


    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}