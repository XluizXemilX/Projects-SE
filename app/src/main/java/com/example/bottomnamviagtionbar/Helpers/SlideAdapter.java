package com.example.bottomnamviagtionbar.Helpers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.charts.Cartesian;
import com.anychart.core.ui.table.Column;
import com.example.bottomnamviagtionbar.R;

import java.util.ArrayList;

public class SlideAdapter extends PagerAdapter {

    private  int[] charts;
    LayoutInflater layoutInflater;
    Context context;

    public SlideAdapter(int[] charts, Context context){
        this.charts = charts;
        this.context = context;
        layoutInflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return charts.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position){
        View view = layoutInflater.inflate(charts[position],container,false);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View view = (View)object;
        container.removeView(view);
    }
}


