package com.konatsup.dailystudymemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class NotesFragmentPagerAdapter extends FragmentPagerAdapter {
    public NotesFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return NotesFragment.newInstance(android.R.color.holo_blue_bright);
            case 1:
                return NotesFragment.newInstance(android.R.color.holo_green_light);
            case 2:
                return NotesFragment.newInstance(android.R.color.holo_orange_light);
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String s = "";
        switch (position) {
            case 0:
                s = "数学";
                break;
            case 1:
                s = "理科";
                break;
            case 2:
                s = "世界史";
                break;
        }
        return s;
    }
}