package com.example.bottomnamviagtionbar.Helpers;

public class SpinnersHelper {

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
        }
        return budget;
    }
}
