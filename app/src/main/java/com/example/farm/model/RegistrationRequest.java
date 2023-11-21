package com.example.farm.model;
public class RegistrationRequest {
    private String name;
    private String email;
    private String phone;
    private String password;

    public RegistrationRequest(String name, String email, String phone, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }
}


