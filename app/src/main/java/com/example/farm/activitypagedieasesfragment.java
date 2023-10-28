package com.example.farm;

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

public class activitypagedieasesfragment extends Fragment {


    private PopupWindow popupWindow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_activitypagedieasesfragment, container, false);

        TextView showOption1 = view.findViewById(R.id.showoption1);
        TextView showOption2 = view.findViewById(R.id.showoption2);
        TextView showOption3 = view.findViewById(R.id.showoption3);

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
}