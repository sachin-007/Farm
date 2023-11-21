package com.example.farm;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.farm.controllers.ApiService;
import com.example.farm.controllers.YourResponseClass;
import com.example.farm.model.RegistrationRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Registration_page extends AppCompatActivity {

    private EditText name, email, phone, password;
    private LinearLayout signupbtn;
    private TextView signinbtn;

    private boolean done = true;

    private SharedPreferencesManager prefsManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_page);

        initializeViews();

        setupPasswordToggle();

        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginint =new Intent(Registration_page.this,Login_page.class);
                startActivity(loginint);
            }
        });
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleRegistration();
            }
        });


    }

    private void initializeViews() {
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phoneno);
        password = findViewById(R.id.password);
        signupbtn = findViewById(R.id.signupbtn);
        signinbtn = findViewById(R.id.signin);
    }



    private void setupPasswordToggle() {
        password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    int drawableRight = 2; // Index of the drawableEnd (2 for right-most drawable)
                    if (event.getRawX() >= (view.getRight() - password.getCompoundDrawables()[drawableRight].getBounds().width())) {
                        togglePasswordVisibility();
                        return true;
                    }
                }
                return false;
            }
        });
    }

    private void togglePasswordVisibility() {
        int inputType = password.getInputType();
        int newInputType = (inputType == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)) ?
                (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) :
                (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        password.setInputType(newInputType);
        password.setSelection(password.getText().length());
        password.refreshDrawableState();
    }

    private void handleRegistration() {
        String userName = name.getText().toString();
        String userEmail = email.getText().toString();
        String userPhone = phone.getText().toString();
        String userPassword = password.getText().toString();

        if (userPhone.length() == 10 && userPassword.length() > 8&& userName.length()!=0 &&userEmail.length()!=0) {
            done = true;
        } else {
            Toast.makeText(getApplicationContext(), "Enter valid details: name, email, phone (10 digits), and password (at least 9 characters)", Toast.LENGTH_LONG).show();
            done = false;
        }

        if (done) {
            RegistrationRequest registrationRequest = new RegistrationRequest(userName, userEmail, userPhone, userPassword);
            sendRegistrationRequest(registrationRequest);
        }
    }

    private void sendRegistrationRequest(RegistrationRequest registrationRequest) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dev.cofastudio.com/my_farm/myfarm.php/registration/") // Replace with your API base URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService registrationService = retrofit.create(ApiService.class);

        Call<YourResponseClass> call = registrationService.registerUser(registrationRequest);

        call.enqueue(new Callback<YourResponseClass>() {
            @Override
            public void onResponse(Call<YourResponseClass> call, Response<YourResponseClass> response) {
                if (response.isSuccessful()) {
                    YourResponseClass responseBody = response.body();
                    Toast.makeText(getApplicationContext(), "Registration successfully", Toast.LENGTH_SHORT).show();
                    if (responseBody != null) {
                        String dataString = responseBody.toString();
                        Toast.makeText(getApplicationContext(), "Posted Data: " + dataString, Toast.LENGTH_LONG).show();
                    }
                    navigateToLoginPage();
                } else {
                    Toast.makeText(getApplicationContext(), "Data posting failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<YourResponseClass> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Internal Server error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void navigateToLoginPage() {
        Intent loginIntent = new Intent(Registration_page.this, Login_page.class);
        startActivity(loginIntent);
    }
}
