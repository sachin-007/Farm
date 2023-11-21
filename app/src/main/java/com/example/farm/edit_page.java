package com.example.farm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import com.example.farm.controllers.ApiService;
import com.example.farm.model.UpdateProfileRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class edit_page extends AppCompatActivity {
    private SharedPreferencesManager sharedPreferencesManager;

    private ApiService apiService;


    TextView passerror;
    EditText name, email, phone;

    ImageView backtn;

    LinearLayout signupbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_page);

        Retrofit retrofit  = new Retrofit.Builder()
                .baseUrl("https://dev.cofastudio.com/my_farm/myfarm.php/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);


        backtn=findViewById(R.id.backbtn);

        backtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        sharedPreferencesManager = new SharedPreferencesManager(this); // Initialize SharedPreferencesManager


        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phoneno);

        SharedPreferencesManager.User userData = sharedPreferencesManager.getUserData();

        if (userData != null) {
            // Retrieve user data from SharedPreferences
            String userName = userData.getName();
            String userEmail = userData.getEmail();
            String userPhone = userData.getPhone();

            // Set the EditText fields with the retrieved data
            name.setText(userName);
            email.setText(userEmail);
            phone.setText(userPhone);
        }


        passerror = findViewById(R.id.passerror);



        signupbtn=findViewById(R.id.signupbtn);
//        signupbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String nname,nphone;
//                nname=name.getText().toString();
//                nphone=phone.getText().toString();
//
//                UpdateProfileRequest updateRequest = new UpdateProfileRequest(nname,nphone);
//                String useriid = userData.getUserId();
//
//                Call<Void> call = apiService.updateProfile(Long.parseLong(useriid),updateRequest);
//
//                call.enqueue(new Callback<Void>() {
//                    @Override
//                    public void onResponse(Call<Void> call, Response<Void> response) {
//                        if (response.isSuccessful()) {
//                            // Profile update was successful
//                            sharedPreferencesManager.updateProfile(nname,nphone);
//                            Toast.makeText(edit_page.this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
//                            Intent proint = new Intent(edit_page.this,profilenavigation.class);
//                            startActivity(proint);
//
//                        } else {
//                            // Handle profile update failure
//                            Toast.makeText(edit_page.this, "Failed to update profile", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<Void> call, Throwable t) {
//                        // Handle network or unexpected errors
//                        Toast.makeText(edit_page.this, "Failed to update profile: " + t.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//            }
//        });


        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nname = name.getText().toString();
                String nphone = phone.getText().toString();

                // Check if the phone number has exactly 10 digits
                if (nphone.length() == 10) {
                    passerror.setVisibility(View.GONE); // Hide the error message
                    UpdateProfileRequest updateRequest = new UpdateProfileRequest(nname, nphone);
                    String useriid = userData.getUserId();

                    Call<Void> call = apiService.updateProfile(Long.parseLong(useriid), updateRequest);

                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.isSuccessful()) {
                                // Profile update was successful
                                sharedPreferencesManager.updateProfile(nname, nphone);
                                Toast.makeText(edit_page.this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
                                Intent proint = new Intent(edit_page.this, profilenavigation.class);
                                startActivity(proint);
                            } else {
                                // Handle profile update failure
                                Toast.makeText(edit_page.this, "Failed to update profile", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            // Handle network or unexpected errors
                            Toast.makeText(edit_page.this, "Failed to update profile: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    // Show an error message if the phone number doesn't have 10 digits
                    passerror.setVisibility(View.VISIBLE);
                    passerror.setText("Phone number must have exactly 10 digits");
                }
            }
        });

        Log.d(TAG, "onCreate: " + name.getText() + "\n" + email.getText() + "\n" + phone.getText());
    }
}
