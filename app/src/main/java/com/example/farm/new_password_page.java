package com.example.farm;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.farm.controllers.ApiService;
import com.example.farm.model.PasswordResetRequest;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class new_password_page extends AppCompatActivity {
    private ApiService apiService;
    private EditText password, password1;
    private long userId;
    private ImageView backbtn;
    private AppCompatButton savebtn;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password_page);



        savebtn = findViewById(R.id.savebtn);
        password = findViewById(R.id.password);
        password1 = findViewById(R.id.password1);

        backbtn = findViewById(R.id.backbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        // Create the Retrofit instance here
        retrofit = new Retrofit.Builder()
                .baseUrl("https://dev.cofastudio.com/my_farm/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        long userId = UserDataRepository.getInstance().getUserId();


        // Create the ApiService instance here
        apiService = retrofit.create(ApiService.class);

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass1 = password.getText().toString();
                String pass2 = password1.getText().toString();

                if (pass1.equals(pass2)) {
                    resetPassword(userId, pass2);
                } else {
                    showToast("Passwords do not match");
                }
            }
        });


    }

    private void resetPassword(long userId, String pass2) {
        PasswordResetRequest request = new PasswordResetRequest(pass2);
        Call<ResponseBody> call = apiService.resetPassword(String.valueOf(userId), request);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    // Password reset successful
                    showToast("Password reset successful");
                    Intent intent=new Intent(new_password_page.this, MainActivity.class);
                    startActivity(intent);

                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        showToast("Password reset failed. Error: " + errorBody);
                    } catch (IOException e) {
                        e.printStackTrace();
                        showToast("Password reset failed. Error: An error occurred while processing the response.");
                    }
                    // You can log the error or perform additional error handling here
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                showToast("Network or other errors occurred: " + t.getMessage());
                // Handle network or other errors here
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
