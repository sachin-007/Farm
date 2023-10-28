package com.example.farm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class verified_page extends AppCompatActivity {


    AppCompatButton browsehome ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verified_page);

        browsehome = findViewById(R.id.browsehome);

        browsehome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browsehome = new Intent(verified_page.this,MainActivity.class);
                startActivity(browsehome);
            }
        });
    }


}