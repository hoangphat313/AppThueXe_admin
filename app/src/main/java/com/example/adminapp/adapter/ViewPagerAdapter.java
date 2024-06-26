package com.example.adminapp.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.adminapp.fragment.CurrentlyRentingFragment;
import com.example.adminapp.fragment.LastRentedFragment;
import com.example.adminapp.fragment.NewlyRentedFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new NewlyRentedFragment();
            case 1:
                return new CurrentlyRentingFragment();
            case 2:
                return new LastRentedFragment();
            default:
                return new NewlyRentedFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "New Rented";
                break;
            case 1:
                title = "Renting";
                break;
            case 2:
                title = "Last Rented";
                break;
        }
        return title;
    }
}
