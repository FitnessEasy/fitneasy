package com.gymapp.fitnesasy.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;



public class ViewPagerSliderAdapter extends FragmentStatePagerAdapter {

    List<Fragment> fragments;

    public ViewPagerSliderAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        fragments = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
