package com.example.farm;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
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
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.farm.controllers.ApiService;
import com.example.farm.controllers.YourResponseClass;
import com.example.farm.model.FarmRequest;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Add_plot extends AppCompatActivity {

    private SharedPreferencesManager sharedPreferencesManager;

    EditText farmname, totalfarmarea, plotname, soiltype, plotarea, location;

    TextView popupsoiltype;
    private TextView textView;
    private PopupWindow popupWindow;
    ImageView backbtn;

    FusedLocationProviderClient fusedLocationProviderClient;
    EditText locationadd;
    private final static int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_plot);

        sharedPreferencesManager = new SharedPreferencesManager(this);

        SharedPreferencesManager.User userData = sharedPreferencesManager.getUserData();

        locationadd = findViewById(R.id.location);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        locationadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLastLocation();
            }
        });

        popupsoiltype = findViewById(R.id.popupsoiltype);

        farmname = findViewById(R.id.farmname);
        totalfarmarea = findViewById(R.id.totalfarmarea);
        plotname = findViewById(R.id.plotname);
        plotarea = findViewById(R.id.plotarea);
        location = findViewById(R.id.location);  // Initialize the location EditText

        View popupView = getLayoutInflater().inflate(R.layout.popup_layout, null);
        popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);

        ImageView sandyImage = popupView.findViewById(R.id.img);
        ImageView sandyloamImage = popupView.findViewById(R.id.img1);
        ImageView loamyImage = popupView.findViewById(R.id.img2);
        ImageView clayImage = popupView.findViewById(R.id.img3);

        textView = findViewById(R.id.popupsoiltype);

        setSoilTypeClickListener(sandyImage, "Sandy");
        setSoilTypeClickListener(sandyloamImage, "Sandy Loam");
        setSoilTypeClickListener(loamyImage, "Loamy");
        setSoilTypeClickListener(clayImage, "Clay");

        popupsoiltype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(v);
            }
        });

        backbtn = findViewById(R.id.backbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        AppCompatButton savebtn = findViewById(R.id.savebtn);

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePlotData();
            }
        });
    }

    private void setSoilTypeClickListener(ImageView imageView, final String soilType) {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(soilType);
                popupWindow.dismiss();
            }
        });
    }

    private void showPopupWindow(View anchorView) {
        int[] location = new int[2];
        anchorView.getLocationOnScreen(location);

        int centerX = getResources().getDisplayMetrics().widthPixels / 2;
        int centerY = getResources().getDisplayMetrics().heightPixels / 2;

        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.showAtLocation(anchorView, Gravity.NO_GRAVITY, 0, centerY);
    }

    private void savePlotData() {
        String userId = sharedPreferencesManager.getUserId();
        String ffname = farmname.getText().toString();
        String ftoalfarma = totalfarmarea.getText().toString();
        String fplotname = plotname.getText().toString();
        String fsoiltype = popupsoiltype.getText().toString();
        String fplotarea = plotarea.getText().toString();
        String flocaltion = location.getText().toString();



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dev.cofastudio.com/my_farm/myfarm.php/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);


        FarmRequest farmRequest = new FarmRequest(userId, ffname, ftoalfarma, fplotname, fsoiltype, flocaltion,fplotarea );


        Call<YourResponseClass> call = apiService.addFarm(farmRequest);

        call.enqueue(new Callback<YourResponseClass>() {
            @Override
            public void onResponse(Call<YourResponseClass> call, Response<YourResponseClass> response) {
                if (response.isSuccessful()) {
                    YourResponseClass responseBody = response.body();
                    Toast.makeText(Add_plot.this,"plot added succesfull: "+fplotname,Toast.LENGTH_SHORT).show();
                } else {
                    // Handle unsuccessful response
                    Toast.makeText(Add_plot.this, "Failed to add plot", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<YourResponseClass> call, Throwable t) {
                // Handle failure
                Toast.makeText(Add_plot.this, "Failed to add plot", Toast.LENGTH_SHORT).show();
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
                                updateLocationText(location);
                            }
                        }
                    });
        } else {
            askPermission();
        }
    }

    private void updateLocationText(Location location) {
        Geocoder geocoder = new Geocoder(Add_plot.this, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            locationadd.setText(addresses.get(0).getAddressLine(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void askPermission() {
        ActivityCompat.requestPermissions(Add_plot.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
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
