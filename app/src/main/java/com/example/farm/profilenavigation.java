package com.example.farm;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class profilenavigation extends AppCompatActivity {
    private SharedPreferencesManager sharedPreferencesManager;

    ImageView backbtn,profileavatar,selectedImageView;

    TextView profname,profphone;

    LinearLayout addplot;
    LinearLayout terms;
    LinearLayout about;
    LinearLayout notification;
    LinearLayout privacy,logout;

    TextView Edit;

    private static final int IMAGE_PICK_REQUEST = 1;



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilenavigation);

        sharedPreferencesManager = new SharedPreferencesManager(this); // Initialize SharedPreferencesManager

        backbtn = findViewById(R.id.backbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        addplot = findViewById(R.id.addplot);

        addplot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addp = new Intent(profilenavigation.this, Add_plot.class);
                startActivity(addp);
            }
        });

        terms = findViewById(R.id.terms);
        terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addp = new Intent(profilenavigation.this,termncondition.class);
                startActivity(addp);
            }
        });
        about = findViewById(R.id.about);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addp = new Intent(profilenavigation.this,aboutus.class);
                startActivity(addp);
            }
        });

        notification = findViewById(R.id.notification);
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addp = new Intent(profilenavigation.this,notification_page.class);
                startActivity(addp);
            }
        });

        privacy = findViewById(R.id.privacy);
        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addp = new Intent(profilenavigation.this,privacy_page.class);
                startActivity(addp);
            }
        });


        profileavatar=findViewById(R.id.profileavatar);

        profileavatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedImageView = profileavatar;
                selectImage();
            }
        });

        logout=findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(profilenavigation.this, Registration_page.class);
                UserDataRepository.getInstance().setUserId(-1L);

                sharedPreferencesManager.remove();



                Toast.makeText(profilenavigation.this, "Logout Successful", Toast.LENGTH_SHORT).show();

                startActivity(intent);
            }
        });


        Edit=findViewById(R.id.edit);
        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editpg=new Intent(profilenavigation.this, edit_page.class);
                startActivity(editpg);
            }
        });

        profname=findViewById(R.id.profname);
        profphone=findViewById(R.id.profphone);


        String name,phone;
        SharedPreferencesManager.User ud= sharedPreferencesManager.getUserData() ;
        phone = ud.getPhone();
        name = ud.getName();
        profphone.setText(phone);
        profname.setText(name);

    }

    private void selectImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");

        try {
            startActivityForResult(intent, IMAGE_PICK_REQUEST);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "No suitable app to handle the request.", Toast.LENGTH_SHORT).show();
        }
    }
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_PICK_REQUEST && resultCode == RESULT_OK) {
            if (data != null && data.getData() != null) {
                Uri selectedImageUri = data.getData();

                // Load the selected image into the selectedImageView using Glide
                Glide.with(this)
                        .load(selectedImageUri)
                        .into(selectedImageView);
            }
        }
    }
}