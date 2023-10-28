package com.example.farm;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;

public class spreytiming extends AppCompatActivity {
    private PopupWindow popupWindow;



    private ImageView popupspraytiming;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spreytiming);

        popupspraytiming=findViewById(R.id.popupspraytiming);

        popupspraytiming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(v);
            }
        });



    }

    private void showPopup(View anchorView) {
        View popupView = getLayoutInflater().inflate(R.layout.activity_popupthing, null);


        popupWindow = new PopupWindow(
                popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

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