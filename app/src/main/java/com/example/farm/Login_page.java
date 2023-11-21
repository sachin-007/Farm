package com.example.farm;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.farm.controllers.ApiService;
import com.example.farm.controllers.UserResponse;
import com.example.farm.model.LoginRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login_page extends AppCompatActivity {
    private long userIid;


    private TextView forget;
    private LinearLayout welcomeBack;
    private TextView signupbtn;

    private EditText phoneno, passwordi;


    private SharedPreferencesManager sharedPreferencesManager;
    private ApiService apiService;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        sharedPreferencesManager = new SharedPreferencesManager(this); // Initialize SharedPreferencesManager



        signupbtn = findViewById(R.id.signupbtn);
        phoneno = findViewById(R.id.phoneno);
        passwordi = findViewById(R.id.password);
        forget = findViewById(R.id.forget);

        // Retrofit initialization
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dev.cofastudio.com/my_farm/myfarm.php/") // Replace with your base URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);

        signupbtn.setOnClickListener(view -> {
            Intent signupIntent = new Intent(Login_page.this, Registration_page.class);
            startActivity(signupIntent);
        });

        forget.setOnClickListener(view -> {
            Intent forgetIntent = new Intent(Login_page.this, forget_password.class);
            startActivity(forgetIntent);
        });

        welcomeBack = findViewById(R.id.welcomeBack);
        welcomeBack.setOnClickListener(view -> {
            // Call the login method
            performLogin();
        });

        passwordi.setOnTouchListener((view, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                int drawableRight = 2; // Index of the drawableEnd (2 for right-most drawable)
                if (event.getRawX() >= (view.getRight() - passwordi.getCompoundDrawables()[drawableRight].getBounds().width())) {
                    togglePasswordVisibility();
                    return true;
                }
            }
            return false;
        });
    }

    private void togglePasswordVisibility() {
        int inputType = passwordi.getInputType();
        int newInputType = (inputType == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)) ?
                (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) :
                (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        passwordi.setInputType(newInputType);
        passwordi.setSelection(passwordi.getText().length());
        passwordi.refreshDrawableState();
    }

    private void performLogin() {
        String phone = phoneno.getText().toString();
        String password = passwordi.getText().toString();


        // Check network connectivity
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        if (!isConnected) {
            // Show a toast message indicating a lack of internet connection
            Toast.makeText(Login_page.this, "Login failed: No internet connection", Toast.LENGTH_SHORT).show();
            return;
        }

        LoginRequest loginRequest = new LoginRequest(phone, password);
        Call<UserResponse> call = apiService.authenticateUser(loginRequest);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    UserResponse userResponse = response.body();
//                    prefsManager.saveUserId(userId);

                    String suserIid = userResponse.getUserId();
                    String sname = userResponse.getName();
                    String semail = userResponse.getEmail();
                    String sphone = userResponse.getPhone();

//
//                    // Debugging output
//                    Log.d(TAG, "userResponse.getUserId(): " + userResponse.getUserId());
//                    Log.d(TAG, "userResponse.getName(): " + userResponse.getName());
//                    Log.d(TAG, "userResponse.getPhone(): " + userResponse.getPhone());
//                    Log.d(TAG, "userResponse.getEmail(): " + userResponse.getEmail());


                    sharedPreferencesManager.saveUserId(suserIid);
                    sharedPreferencesManager.saveUserData(sname, semail, sphone);



                    // Show a success message
                    Toast.makeText(Login_page.this, "Login successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login_page.this, MainActivity.class));
                    finish();
                } else {
                    // Handle authentication failure
                    Toast.makeText(Login_page.this, "Enter correct phone or password", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                // Handle failure
                Toast.makeText(Login_page.this, "Login failed: Check internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
