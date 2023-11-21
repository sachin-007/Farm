package com.example.farm;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;


public class SplashActivity extends AppCompatActivity {
    private SharedPreferencesManager sharedPreferencesManager;

    long userId = UserDataRepository.getInstance().getUserId();





    private SharedPreferencesManager prefsManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sharedPreferencesManager = new SharedPreferencesManager(this); // Initialize SharedPreferencesManager


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                redirectToAppropriateActivity();
            }
        }, 1);
    }

    private void redirectToAppropriateActivity() {
        // String userId = prefsManager.getUserId();
        String userId = sharedPreferencesManager.getUserId();
        Log.d(TAG, "redirectToAppropriateActivity: useridisthe " + userId);

        if (userId == null || userId.equals("-1L")) {
            startActivity(new Intent(this, Login_page.class));
        } else {
            startActivity(new Intent(this, MainActivity.class));
        }
        finish();
    }

}