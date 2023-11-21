package com.example.farm;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class activitypagedieasesfragment extends Fragment {


    private PopupWindow popupWindow;

    public TextView showoptiondes;

    public TextView showOption1;
    public TextView showOption2;
    public TextView showOption3;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_activitypagedieasesfragment, container, false);

         showOption1= view.findViewById(R.id.showoption1);
         showOption2= view.findViewById(R.id.showoption2);
         showOption3= view.findViewById(R.id.showoption3);


        showoptiondes= view.findViewById(R.id.showoptiondes1);




        showOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(v);
            }
        });

        showOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(v);
            }
        });

        showOption3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(v);
            }
        });

        return view;
    }

    private void showPopup(View anchorView) {
        View popupView = getLayoutInflater().inflate(R.layout.activity_screen, null);

        // Create the PopupWindow with full-screen dimensions
        popupWindow = new PopupWindow(
                popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        RadioButton radio1 = popupView.findViewById(R.id.radio1);
        RadioButton radio2 = popupView.findViewById(R.id.radio2);
        RadioButton radio3 = popupView.findViewById(R.id.radio3);

        TextView opt1 = popupView.findViewById(R.id.opt1);
        TextView opt2 = popupView.findViewById(R.id.opt2);
        TextView opt3 = popupView.findViewById(R.id.opt3);

        TextView des1 = popupView.findViewById(R.id.des1);
        TextView des2 = popupView.findViewById(R.id.des2);
        TextView des3 = popupView.findViewById(R.id.des3);
        radio1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String heading = opt1.getText().toString();
                String description1 = des1.getText().toString();


                showOption1.setVisibility(View.GONE);
                showoptiondes.setVisibility(View.VISIBLE);
                showoptiondes.setText(description1);



                handleRadioButtonClick(heading);

                popupWindow.dismiss();


            }
        });

        radio2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String heading = opt2.getText().toString();
                handleRadioButtonClick(heading);
                popupWindow.dismiss();

            }
        });

        radio3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String heading = opt3.getText().toString();
                handleRadioButtonClick(heading);
                popupWindow.dismiss();



            }
        });


        // Set the background to get touch events outside the popup
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        // Set focusable to false to keep the main activity interactable
        popupWindow.setFocusable(false);

        // Show the popup
        popupWindow.showAtLocation(anchorView, Gravity.CENTER, 0, 0);


        // Prevent the popup from being automatically dismissed
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                // Do nothing here to keep the popup open
            }
        });
    }

    private void handleRadioButtonClick(String selectedOption) {
        String message = "you selected "+selectedOption;
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();

    }
}