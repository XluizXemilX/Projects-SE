package com.example.bottomnamviagtionbar.Helpers;

import android.content.SharedPreferences;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.example.bottomnamviagtionbar.Interfaces.Services;

import java.util.ArrayList;
import java.util.List;

public class PieChartData {

    public void setPieChart(SharedPreferences preferences, AnyChartView pieChart) {
        Pie pie = AnyChart.pie();
        List<DataEntry> dataEntries= new ArrayList<>();


        //Paid Bill
        // String paid = preference.getString("Paid", "");
        // if(!paid.isEmpty()){
        //     Float paidB = Float.valueOf(paid);
        //     dataEntries.add(new ValueDataEntry("Bill",paidB));
        // }
        //Rent
        String rent = preferences.getString("Rent_Ex", "");
        if(!rent.isEmpty()){
            Float rentVal = Float.valueOf(rent);
            dataEntries.add(new ValueDataEntry("Rent",rentVal));
        }
        //Food
        String food = preferences.getString("Food_Ex", "");
        if(!food.isEmpty()){
            Float foodVal = Float.valueOf(food);
            dataEntries.add(new ValueDataEntry("Food",foodVal));
        }
        //Services
        String services = preferences.getString("Services_Ex", "");
        if(!services.isEmpty()){
            Float serviceVal = Float.valueOf(services);
            dataEntries.add(new ValueDataEntry("Services",serviceVal));
        }
        //Entertainment
        String entertain = preferences.getString("Entertainment_Ex", "");
        if(!entertain.isEmpty()){
            Float entertainVal = Float.valueOf(entertain);
            dataEntries.add(new ValueDataEntry("Entertainment",entertainVal));
        }
        //Clothes
        String clothes = preferences.getString("Clothes_Ex", "");
        if(!clothes.isEmpty()){
            Float clothesVal = Float.valueOf(clothes);
            dataEntries.add(new ValueDataEntry("Clothes",clothesVal));
        }
        //Others
        String others = preferences.getString("Other_Ex", "");
        if(!others.isEmpty()){
            Float othersVal = Float.valueOf(others);
            dataEntries.add(new ValueDataEntry("Other",othersVal));
        }
        ////left for speding or savings
        String save = preferences.getString("Saving", "");
        if(!save.isEmpty()) {
            Float savings = Float.valueOf(save);
            dataEntries.add(new ValueDataEntry("Savings", savings));

        }

        pie.data(dataEntries);
        pieChart.setChart(pie);
    }
}
