package com.example.farm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

public class clender extends AppCompatActivity {

    TextView dateTextView;
    EditText dateEditText;
    Calendar calendar;
    DatePickerDialog datePickerDialog;

    AppCompatButton savebtn;

    ImageView backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clender);

        dateTextView = findViewById(R.id.dateTextView);
        dateEditText = findViewById(R.id.dateEditText);
        calendar = Calendar.getInstance();

        // Initialize the DatePickerDialog
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                // Update the selected date in the EditText
                String selectedDate = year + "-" + (month + 1) + "-" + day;
                dateEditText.setText(selectedDate);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        // Set the click listener for the EditText
        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show the DatePickerDialog when EditText is clicked
                datePickerDialog.show();
            }
        });


        backbtn = findViewById(R.id.backbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        savebtn = findViewById(R.id.savebtn);
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(clender.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}