package com.example.farm;

import android.app.DatePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.AppCompatButton;

import com.bumptech.glide.Glide;
import com.example.farm.MainActivity;
import com.example.farm.R;

import java.util.Calendar;





public class CalendarFragment extends Fragment {

    private static final int IMAGE_PICK_REQUEST = 1;
    private ImageView selectedImageView;


    private LinearLayout irrigationPopup;
    private LinearLayout sprayPopup;
    private LinearLayout nutrientsPopup;
    private LinearLayout farmPracticePopup;
    private View lineview;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_calendar, container, false);

        TextView sector1 = rootView.findViewById(R.id.sector1);
        TextView sector2 = rootView.findViewById(R.id.sector2);
        TextView sector3 = rootView.findViewById(R.id.sector3);


        sector1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sector1.setBackgroundResource(R.drawable.greenboxfill);
                sector2.setBackgroundResource(R.drawable.greenbox);
                sector3.setBackgroundResource(R.drawable.greenbox);
            }
        });

        sector2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sector2.setBackgroundResource(R.drawable.greenboxfill);
                sector1.setBackgroundResource(R.drawable.greenbox);
                sector3.setBackgroundResource(R.drawable.greenbox);
            }
        });

        sector3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sector3.setBackgroundResource(R.drawable.greenboxfill);
                sector2.setBackgroundResource(R.drawable.greenbox);
                sector1.setBackgroundResource(R.drawable.greenbox);
            }
        });



        // Initialize the popups
        irrigationPopup = rootView.findViewById(R.id.irrigationpopup);
        sprayPopup = rootView.findViewById(R.id.spreypopup);
        nutrientsPopup = rootView.findViewById(R.id.nutrientspopup);
        farmPracticePopup = rootView.findViewById(R.id.farmpracticepopup);
        lineview = rootView.findViewById(R.id.lineview);

        // Set the visibility of popups
        irrigationPopup.setVisibility(View.GONE);
        sprayPopup.setVisibility(View.GONE);
        nutrientsPopup.setVisibility(View.GONE);
        farmPracticePopup.setVisibility(View.GONE);


        TextView irrigationTextView = rootView.findViewById(R.id.irrigation);
        TextView sprayPestTextView = rootView.findViewById(R.id.spraypest);
        TextView nutrientsTextView = rootView.findViewById(R.id.nutrients);
        TextView farmPracticeTextView = rootView.findViewById(R.id.farmpractice);

        // Set click listeners for the TextViews to toggle the visibility of popups
        irrigationTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irrigationPopup.setVisibility(View.VISIBLE);
                sprayPopup.setVisibility(View.GONE);
                nutrientsPopup.setVisibility(View.GONE);
                farmPracticePopup.setVisibility(View.GONE);
                lineview.setVisibility(View.VISIBLE);

                irrigationTextView.setBackgroundResource(R.drawable.greenboxfill);
                sprayPestTextView.setBackgroundResource(R.drawable.greenbox);
                nutrientsTextView.setBackgroundResource(R.drawable.greenbox);
                farmPracticeTextView.setBackgroundResource(R.drawable.greenbox);

            }
        });

        sprayPestTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irrigationPopup.setVisibility(View.GONE);
                sprayPopup.setVisibility(View.VISIBLE);
                nutrientsPopup.setVisibility(View.GONE);
                farmPracticePopup.setVisibility(View.GONE);
                lineview.setVisibility(View.VISIBLE);

                irrigationTextView.setBackgroundResource(R.drawable.greenbox);
                sprayPestTextView.setBackgroundResource(R.drawable.greenboxfill);
                nutrientsTextView.setBackgroundResource(R.drawable.greenbox);
                farmPracticeTextView.setBackgroundResource(R.drawable.greenbox);

            }
        });

        nutrientsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irrigationPopup.setVisibility(View.GONE);
                sprayPopup.setVisibility(View.GONE);
                nutrientsPopup.setVisibility(View.VISIBLE);
                farmPracticePopup.setVisibility(View.GONE);
                lineview.setVisibility(View.VISIBLE);

                irrigationTextView.setBackgroundResource(R.drawable.greenbox);
                sprayPestTextView.setBackgroundResource(R.drawable.greenbox);
                nutrientsTextView.setBackgroundResource(R.drawable.greenboxfill);
                farmPracticeTextView.setBackgroundResource(R.drawable.greenbox);

            }
        });

        farmPracticeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irrigationPopup.setVisibility(View.GONE);
                sprayPopup.setVisibility(View.GONE);
                nutrientsPopup.setVisibility(View.GONE);
                farmPracticePopup.setVisibility(View.VISIBLE);
                lineview.setVisibility(View.VISIBLE);

                irrigationTextView.setBackgroundResource(R.drawable.greenbox);
                sprayPestTextView.setBackgroundResource(R.drawable.greenbox);
                nutrientsTextView.setBackgroundResource(R.drawable.greenbox);
                farmPracticeTextView.setBackgroundResource(R.drawable.greenboxfill);

            }
        });



        TextView dateTextView = rootView.findViewById(R.id.dateTextView);
        EditText dateEditText = rootView.findViewById(R.id.dateEditText);

        Spinner hoursSpinner = rootView.findViewById(R.id.hoursSpinner);
        Spinner minutesSpinner = rootView.findViewById(R.id.minutesSpinner);

        String[] hoursArray = new String[24];
        String[] minutesArray = new String[60];

        for (int i = 0; i < 24; i++) {
            hoursArray[i] = String.format("%02d", i);
        }

        for (int i = 0; i < 60; i++) {
            minutesArray[i] = String.format("%02d", i);
        }

        ArrayAdapter<String> hoursAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, hoursArray);
        ArrayAdapter<String> minutesAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, minutesArray);

        // Set dropdown view resource
        hoursAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        minutesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the adapters to the spinners
        hoursSpinner.setAdapter(hoursAdapter);
        minutesSpinner.setAdapter(minutesAdapter);

        Calendar calendar = Calendar.getInstance();

        DatePickerDialog datePickerDialog;
        AppCompatButton savebtn = rootView.findViewById(R.id.savebtn);

        // Initialize the DatePickerDialog using the parent activity (getActivity())
        datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                String selectedDate = year + "-" + (month + 1) + "-" + day;
                dateEditText.setText(selectedDate);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class); // Use getActivity() to get the parent activity
                startActivity(intent);
            }
        });



        ImageView imageView1 = rootView.findViewById(R.id.imageView1);
        ImageView imageView2 = rootView.findViewById(R.id.imageView2);
        ImageView imageView3 = rootView.findViewById(R.id.imageView3);


        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedImageView = imageView1;
                selectImage();
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedImageView = imageView2;
                selectImage();
            }
        });

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedImageView = imageView3;
                selectImage();
            }
        });

        return rootView;
    }

    private void selectImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");

        try {
            startActivityForResult(intent, IMAGE_PICK_REQUEST);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(getContext(), "No suitable app to handle the request.", Toast.LENGTH_SHORT).show();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_PICK_REQUEST && resultCode == getActivity().RESULT_OK) {
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
