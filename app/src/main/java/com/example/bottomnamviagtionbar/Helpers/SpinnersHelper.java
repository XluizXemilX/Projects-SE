package com.example.bottomnamviagtionbar.Helpers;

import android.widget.Spinner;
import android.widget.Toast;

public class SpinnersHelper {
    Spinner spin;

    float ConvertBudgetWeekly(int rate,float budget){
        switch (rate){
            case 0:
                break;
            case 1:
                break;
            case 2:
                budget = budget/4;
                break;
            case 3:
                budget = budget/52;
                break;
        }
        return budget;
    }

    float ConvertBudgetMonthly(int rate,float budget){
        switch (rate){
            case 0:
                break;
            case 1:
                budget = budget * 4;
                break;
            case 2:
                break;
            case 3:
                budget = budget/12;
                break;
        }
        return budget;
    }

    float ConvertBudgetYearly(int rate,float budget){
        switch (rate){
            case 0:
                break;
            case 1:
                budget = budget * 52;
                break;
            case 2:
                budget = budget * 12;
                break;
            case 3:
                break;
        }
        return budget;
    }
}
