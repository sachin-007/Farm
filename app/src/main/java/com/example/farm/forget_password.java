package com.example.farm;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.farm.controllers.ApiService;
import com.example.farm.controllers.CheckPhoneResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class forget_password extends AppCompatActivity {
    AppCompatButton savebtn;
    EditText phone;

    //TextView correcrtnumber;
    private ApiService apiService;

    private ImageView backbtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        phone = findViewById(R.id.phone);
        savebtn = findViewById(R.id.savebtn);
        backbtn = findViewById(R.id.backbtn);
       // correcrtnumber =findViewById(R.id.correcrtnumber);



        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = phone.getText().toString();



                // Check if the phone number is present in the database
                checkPhoneNumber(phoneNumber);
            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }

    private void checkPhoneNumber(String phoneNumber) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dev.cofastudio.com/my_farm/myfarm.php/") // Remove {phoneNumber}
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

        Call<CheckPhoneResponse> call = apiService.checkPhoneNumber(phoneNumber);
        call.enqueue(new Callback<CheckPhoneResponse>() {
            @Override
            public void onResponse(@NonNull Call<CheckPhoneResponse> call, @NonNull Response<CheckPhoneResponse> response) {
                if (response.isSuccessful()) {
                    CheckPhoneResponse checkPhoneResponse = response.body();
                    assert checkPhoneResponse != null;
                    String userId = checkPhoneResponse.getUserId();
                    if (userId != null && !userId.isEmpty()) {

                        Intent intent = new Intent(forget_password.this, new_password_page.class);
                        UserDataRepository.getInstance().setUserId(Long.parseLong(userId));



                        startActivity(intent);
                    } else {
                        // Phone number is not found
                        Toast.makeText(forget_password.this, "Phone number not found in the database.", Toast.LENGTH_SHORT).show();
                    }
                } else {

                    // Handle other response codes or errors
                    Toast.makeText(forget_password.this, "Error checking phone number.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CheckPhoneResponse> call, Throwable t) {
                // Handle network or unexpected errors
                Toast.makeText(forget_password.this, "Failed to check phone number: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
