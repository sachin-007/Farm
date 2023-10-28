package com.example.farm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager2 viewPager = findViewById(R.id.viewPager);

        MyPagerAdapter adapter = new MyPagerAdapter(this);
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    switch (position) {
                        case 0:
                            tab.setText("My farm");
                            break;
                        case 1:
                            tab.setText("Weather");
                            break;
                        case 2:
                            tab.setText("Daily Report");
                            break;
                    }
                }
        ).attach();

        ImageView profilenavigation = findViewById(R.id.profilenavigation);
        profilenavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profilenavigationIntent = new Intent(MainActivity.this, profilenavigation.class);
                startActivity(profilenavigationIntent);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation_view);

        for (int i = 3; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                tab.view.setVisibility(View.GONE);
            }
        }

        viewPager.setCurrentItem(0);


        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int selectedFragmentPosition;

            if (item.getItemId() == R.id.nav_home) {
                selectedFragmentPosition = 0;
            } else if (item.getItemId() == R.id.nav_wishlist) {
                selectedFragmentPosition = 4;
            } else if (item.getItemId() == R.id.nav_diesease) {
                selectedFragmentPosition = 5;
            } else if (item.getItemId() == R.id.nav_search) {
                selectedFragmentPosition = 6;
            } else {
                selectedFragmentPosition = 0; // Default position
            }

            if (selectedFragmentPosition > 3) {
                tabLayout.setVisibility(View.GONE);
            } else {
                tabLayout.setVisibility(View.VISIBLE);
            }


            viewPager.setCurrentItem(selectedFragmentPosition);
            return true;
        });

        // Find the LinearLayout by its ID
        LinearLayout myDeviceLayout = findViewById(R.id.adddivice);

        // Set an OnClickListener to navigate to MyDeviceActivity
        myDeviceLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start MyDeviceActivity
                Intent intent = new Intent(MainActivity.this, adddevice.class);
                startActivity(intent);
            }
        });
    }
}
