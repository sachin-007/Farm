package com.example.farm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class spreytiming extends AppCompatActivity {
    private PopupWindow popupWindow;
    private LinearLayout cardtouch;
    private ImageView popupspraytiming;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spreytiming);

        popupspraytiming = findViewById(R.id.popupspraytiming);

        popupspraytiming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(v);
            }
        });
    }

    private void showPopup(View anchorView) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.activity_popupthing, null);
        boolean focusable = true;
        popupWindow = new PopupWindow(
                popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, focusable);

        cardtouch = popupView.findViewById(R.id.cardtouch);

        cardtouch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                popupWindow.dismiss();
                return true;
            }
        });

        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        // Show the popup
        popupWindow.showAtLocation(anchorView, Gravity.TOP, 0, 0);
    }
}