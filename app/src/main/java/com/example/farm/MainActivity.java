package com.example.farm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
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
//
//        TabLayout tabLayout = findViewById(R.id.tabLayout);
//        ViewPager2 viewPager = findViewById(R.id.viewPager);
//
//
//        MyPagerAdapter adapter = new MyPagerAdapter(this);
//        viewPager.setAdapter(adapter);
//
//        new TabLayoutMediator(tabLayout, viewPager,
//                (tab, position) -> {
//                    switch (position) {
//                        case 0:
//                            tab.setText("My farm");
//                            break;
//                        case 1:
//                            tab.setText("Weather");
//                            break;
//                        case 2:
//                            tab.setText("Daily Report");
//                            break;
//                    }
//                }
//        ).attach();
//
//        Typeface customFont = ResourcesCompat.getFont(this, R.font.urbanist_bold);
//
//
//        // Iterate through the tabs and set the custom font for each tab's text
//        for (int i = 0; i < tabLayout.getTabCount(); i++) {
//            TabLayout.Tab tab = tabLayout.getTabAt(i);
//            if (tab != null) {
//                View customTabView = LayoutInflater.from(this).inflate(R.layout.custom_tab_layout, null);
//                TextView tabTextView = customTabView.findViewById(R.id.tab_title);
//                tabTextView.setText(tab.getText());
//                tabTextView.setTypeface(customFont);
//                // Set the custom view for the tab
//                tab.setCustomView(customTabView);
//            }
//        }

        ImageView profilenavigation = findViewById(R.id.profilenavigation);
        profilenavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profilenavigationIntent = new Intent(MainActivity.this, profilenavigation.class);
                startActivity(profilenavigationIntent);
            }
        });





//        for (int i = 3; i < tabLayout.getTabCount(); i++) {
//            TabLayout.Tab tab = tabLayout.getTabAt(i);
//            if (tab != null) {
//                tab.view.setVisibility(View.GONE);
//            }
//        }





//        viewPager.setCurrentItem(0);


//        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
//            int selectedFragmentPosition;
//
//            if (item.getItemId() == R.id.nav_home) {
//                selectedFragmentPosition = 0;
//            } else if (item.getItemId() == R.id.nav_wishlist) {
//                selectedFragmentPosition = 4;
//            } else if (item.getItemId() == R.id.nav_diesease) {
//                selectedFragmentPosition = 5;
//            } else if (item.getItemId() == R.id.nav_search) {
//                selectedFragmentPosition = 6;
//            } else {
//                selectedFragmentPosition = 0; // Default position
//            }
//
////            if (selectedFragmentPosition > 3) {
////                tabLayout.setVisibility(View.GONE);
////            } else {
////                tabLayout.setVisibility(View.VISIBLE);
////            }
////
////
////            viewPager.setCurrentItem(selectedFragmentPosition);
//            return true;
//        });
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.framecontainer, new FarmFragment())
                .commit();


        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation_view);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                int itemId = item.getItemId();

                if (itemId == R.id.nav_home) {
                    selectedFragment = new FarmFragment();
                } else if (itemId == R.id.nav_calender) {
                    selectedFragment = new CalendarFragment();
                } else if (itemId == R.id.nav_diesease) {
                    selectedFragment = new activitypagedieasesfragment();
                } else if (itemId == R.id.nav_plots) {
                    selectedFragment = new PlotsFragment();
                }


                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.framecontainer, selectedFragment)
                            .commit();
                }

                return true;
            }
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
