package com.Save.Save_App.Helpers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class PageAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> charts;
    ArrayList<String> name;

    public PageAdapter(FragmentManager manager){
        super(manager);
        charts = new ArrayList<>();
        name = new ArrayList<>();

    }
    @Override
    public Fragment getItem(int i) {
        return charts.get(i);
    }

    @Override
    public int getCount() {
        return charts.size();
    }

    public  void addFragment(Fragment fragment, String name){
        charts.add(fragment);
        this.name.add(name);
    }

    public CharSequence getPageTitle(int i){
        return name.get(i);
    }
}
