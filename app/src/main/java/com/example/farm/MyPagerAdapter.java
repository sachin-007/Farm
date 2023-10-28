package com.example.farm;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MyPagerAdapter extends FragmentStateAdapter {
    public MyPagerAdapter(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new Myfarmfragment();
            case 1:
                return new weatherfragment();
            case 2:
                return new dailyreportfragment();
            case 3:
                return new Myfarmfragment();
            case 4:
                return new CalendarFragment();
            case 5:
                return new activitypagedieasesfragment();
            case 6:
                return new PlotsFragment();
            default:
                throw new IllegalArgumentException("Invalid position");
        }
    }

    @Override
    public int getItemCount() {
        return 7;
    }
}