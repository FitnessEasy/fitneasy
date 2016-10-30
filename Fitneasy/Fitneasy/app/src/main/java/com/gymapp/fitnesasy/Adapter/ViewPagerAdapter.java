package com.gymapp.fitnesasy.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.gymapp.fitnesasy.Fragment.MuscleTypeFragment;
import com.gymapp.fitnesasy.Fragment.NutitionFragment;
import com.gymapp.fitnesasy.Fragment.ScheduleFragment;

import java.util.ArrayList;
import java.util.List;



public class ViewPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> listFragment = new ArrayList<Fragment>();
    List<String> titleFragment = new ArrayList<String>();


    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);




        




        listFragment.add(new MuscleTypeFragment());
        listFragment.add(new NutitionFragment());
        listFragment.add(new ScheduleFragment());











        titleFragment.add("Danh sách bài tập");
        titleFragment.add("Dinh dưỡng");
        titleFragment.add("Lịch tập");











    }

    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleFragment.get(position);
    }
}
