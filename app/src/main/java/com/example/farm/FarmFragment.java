package com.example.farm;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class FarmFragment extends Fragment {
    public FarmFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_farm, container, false);

        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        ViewPager2 viewPager = view.findViewById(R.id.viewPager);

        // Set up your ViewPager2 and TabLayout using the activity's FragmentManager
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

        Typeface customFont = ResourcesCompat.getFont(requireContext(), R.font.urbanist_bold);

        // Iterate through the tabs and set the custom font for each tab's text
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                View customTabView = LayoutInflater.from(requireContext()).inflate(R.layout.custom_tab_layout, null);
                TextView tabTextView = customTabView.findViewById(R.id.tab_title);
                tabTextView.setText(tab.getText());
                tabTextView.setTypeface(customFont);
                // Set the custom view for the tab
                tab.setCustomView(customTabView);
            }
        }

        viewPager.setCurrentItem(0);

        return view;
    }
}
