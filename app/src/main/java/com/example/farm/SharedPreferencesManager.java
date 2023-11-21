package com.example.farm;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager {
    private static final String PREF_NAME = "MyPrefs";
    private static final String USER_ID_KEY = "user_id"; // Corrected to be a string
    private static final String NAME_KEY = "name";
    private static final String EMAIL_KEY = "email";
    private static final String PHONE_KEY = "phone";

    private SharedPreferences preferences;

    public SharedPreferencesManager(Context context) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void saveUserId(String userId) {
        preferences.edit().putString(USER_ID_KEY, userId).apply();
    }

    public String getUserId() {
        return preferences.getString(USER_ID_KEY, null);
    }

    public void saveUserData(String name, String email, String phone) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(NAME_KEY, name);
        editor.putString(EMAIL_KEY, email);
        editor.putString(PHONE_KEY, phone);
        editor.apply();
    }

    public void updateProfile(String name, String phone) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(NAME_KEY, name);
        editor.putString(PHONE_KEY, phone);
        editor.apply();
    }

    public String getName() {
        return preferences.getString(NAME_KEY, null);
    }

    public String getEmail() {
        return preferences.getString(EMAIL_KEY, null);
    }

    public String getPhone() {
        return preferences.getString(PHONE_KEY, null);
    }

//    public void remove() {
//        SharedPreferences.Editor editor = preferences.edit();
//        editor.clear().apply();
//    }

    public void remove() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.putString(USER_ID_KEY, "-1L"); // Set user_id to -1L
        editor.apply();
    }

    public User getUserData() {
        String userId = getUserId();
        String name = getName();
        String email = getEmail();
        String phone = getPhone();

        if (userId != null) {
            // User is logged in, return user data
            return new User(userId, name, email, phone);
        } else {
            // User is not logged in or data is not available
            return null;
        }
    }

    public class User {
        private String userId;
        private String name;
        private String email;
        private String phone;

        public User(String userId, String name, String email, String phone) {
            this.userId = userId;
            this.name = name;
            this.email = email;
            this.phone = phone;
        }

        public String getUserId() {
            return userId;
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
}
