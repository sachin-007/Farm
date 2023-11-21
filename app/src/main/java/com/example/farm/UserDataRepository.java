package com.example.farm;

public class UserDataRepository {
    private static UserDataRepository instance;
    private long userId;

    private String name;
    private String email;
    private String phone;


    private UserDataRepository() {
        // Private constructor to prevent direct instantiation
    }

    public static synchronized UserDataRepository getInstance() {
        if (instance == null) {
            instance = new UserDataRepository();
        }
        return instance;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
