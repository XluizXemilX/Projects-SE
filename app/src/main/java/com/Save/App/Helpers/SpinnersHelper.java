package com.Save.App.Helpers;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.Save.App.Interfaces.Bill;
import com.Save.App.Interfaces.Clothes;
import com.Save.App.Interfaces.Entertainment;
import com.Save.App.Interfaces.Food;
import com.Save.App.Interfaces.Others;
import com.Save.App.Interfaces.Rent;
import com.Save.App.Interfaces.Services;

import java.util.ArrayList;

public class SpinnersHelper {

    public void SaveCategories(Spinner spinner, final int category, final Bill bill, final ArrayList<Bill> bills){
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch(category){
                    case 0:
                        Rent rent = new Rent();
                        rent = (Rent) bill;
                        bills.add(rent);
                        break;
                    case 1:
                        Services services = new Services();
                        services = (Services) bill;
                        bills.add(services);
                        break;
                    case 2:
                        Food food = new Food();
                        food = (Food) bill;
                        bills.add(food);
                        break;
                    case 3:
                        Entertainment entertainment = new Entertainment();
                        entertainment = (Entertainment) bill;
                        bills.add(entertainment);
                        break;
                    case 4:
                        Clothes clothes = new Clothes();
                        clothes = (Clothes) bill;
                        bills.add(clothes);
                        break;
                    case 5:
                        Others others = new Others();
                        others = (Others) bill;
                        bills.add(others);
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public float ConvertBudgetWeekly(int frequency,float budget){
        switch (frequency){
            case 0: // monthly
                budget *= 4.348214;
                break;
            case 1: // weekly
                break;
            case 2: // yearly
                budget *= 52.17857;
                break;
            case 3:// bi-monthly
                budget *=8.696429;
                break;
            case 4:// bi-weekly
                budget *=2;
        }
        return budget;
    }

    public float ConvertBudgetMonthly(int frequency, float budget){
        switch (frequency){
            case 0: // monthly
                break;
            case 1: // weekly
                budget /= 4.348214;
                break;
            case 2: // yearly
                budget *= 12;
                break;
            case 3:// bi-monthly
                budget *=2;
                break;
            case 4:// bi-weekly
                budget /= 2.174107;
                break;

        }
        return budget;
    }

    public float ConvertBudgetYearly(int frequency,float budget){
        switch (frequency){
            case 0: // monthly
                budget /= 12;
                break;
            case 1: // weekly
                budget /= 52.17857;
                break;
            case 2: // yearly
                break;
            case 3://bi-monthly
                budget /=6;
                break;
            case 4://bi-weekly
                budget /=26.08929;
                break;
        }
        return budget;
    }

    public float ConvertBudgetBiMonthly(int frequency,float budget){
        switch (frequency){
            case 0: // monthly
                budget /= 2;
                break;
            case 1: // weekly
                budget /= 8.696429;
                break;
            case 2: // yearly
                budget *= 6;
                break;
            case 3://bi-Monthly
                break;
            case 4://bi-Weekly
                budget /=4.348214;
                break;

        }
        return budget;
    }

    public float ConvertBudgetBiWeekly(int frequency,float budget){
        switch (frequency){
            case 0: // monthly
                budget *= 2.174107;
                break;
            case 1: // weekly
                budget /= 2;
                break;
            case 2: // yearly
                budget *= 26.08929;
                break;
            case 3://bi-Monthly
                budget *=4.348214;
                break;
            case 4://bi-weekly
                break;
        }
        return budget;
    }


}
