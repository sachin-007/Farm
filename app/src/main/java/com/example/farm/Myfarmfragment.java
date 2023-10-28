package com.example.farm;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;


public class Myfarmfragment extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View rootView = inflater.inflate(R.layout.fragment_myfarmfragment, container, false);

            // Find the LinearLayout by its ID
            LinearLayout myDeviceLayout = rootView.findViewById(R.id.myDeviceLayout);

            Button cropstage =  rootView.findViewById(R.id.cropstage);
            Button activitypage =  rootView.findViewById(R.id.activitypage);
            Button spraytiming =  rootView.findViewById(R.id.spreytiming);
            Button popupthings =  rootView.findViewById(R.id.popupting);




            // Set an OnClickListener to navigate to MyDeviceActivity
            myDeviceLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Create an Intent to start MyDeviceActivity
                    Intent intent = new Intent(getActivity(), adddevice.class);
                    startActivity(intent);
                }
            });

            cropstage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent cropint = new Intent(getActivity(),Crop_Stage.class);
                    startActivity(cropint);

                }
            });


            activitypage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent cropint = new Intent(getActivity(),activitypage.class);
                    startActivity(cropint);

                }
            });

            spraytiming.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent cropint = new Intent(getActivity(), spreytiming.class);
                    startActivity(cropint);

                }
            });

            popupthings.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent cropint = new Intent(getActivity(), popupthing.class);
                    startActivity(cropint);

                }
            });


            return rootView;
        }
    }