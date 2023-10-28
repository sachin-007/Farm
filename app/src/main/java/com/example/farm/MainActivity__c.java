package com.example.farm;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity__c extends AppCompatActivity {

    TextView editText; // Declare the EditText
    private TextView textView;
    private PopupWindow popupWindow;
    ImageView backbtn;

    FusedLocationProviderClient fusedLocationProviderClient;
    EditText locationadd;
    private final static int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_c);

        locationadd = findViewById(R.id.location);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        locationadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLastLocation();
            }
        });

        editText = findViewById(R.id.popup); // Initialize it here

        View popupView = getLayoutInflater().inflate(R.layout.popup_layout, null);

        // Create a PopupWindow with a specified width and height
        popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        // Set the focusable property to true to allow interactions with the pop-up
        popupWindow.setFocusable(true);

        // Set up the pop-up items (ImageView and TextView) for sandy, sandyloam, loamy, clay
        ImageView sandyImage = popupView.findViewById(R.id.img);
        ImageView sandyloamImage = popupView.findViewById(R.id.img1);
        ImageView loamyImage = popupView.findViewById(R.id.img2);
        ImageView clayImage = popupView.findViewById(R.id.img3);

        textView = findViewById(R.id.popup); // Initialize the TextView here

        // Set click listeners for each pop-up item
        sandyImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Sandy");
                popupWindow.dismiss(); // Close the pop-up
            }
        });

        sandyloamImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Sandy Loam");
                popupWindow.dismiss();
            }
        });

        loamyImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Loamy");
                popupWindow.dismiss();
            }
        });

        clayImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Clay");
                popupWindow.dismiss();
            }
        });

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] location = new int[2];
                v.getLocationOnScreen(location);

                // Calculate the center of the screen
                int centerX = getResources().getDisplayMetrics().widthPixels / 2;
                int centerY = getResources().getDisplayMetrics().heightPixels / 2;

                popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT); // Set the width to MATCH_PARENT
                popupWindow.showAtLocation(v, Gravity.NO_GRAVITY, 0, centerY);
            }
        });

        backbtn = findViewById(R.id.backbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void getLastLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                Geocoder geocoder = new Geocoder(MainActivity__c.this, Locale.getDefault());
                                List<Address> addresses = null;
                                try {
                                    addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                                    locationadd.setText(addresses.get(0).getAddressLine(0));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
        } else {
            askPermission();
        }
    }

    private void askPermission() {
        ActivityCompat.requestPermissions(MainActivity__c.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            } else {
                Toast.makeText(this, "Location permission is required.", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
