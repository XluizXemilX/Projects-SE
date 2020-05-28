package com.Save.Save_App.Interfaces;

public class Income implements Comparable <Income>{
    public float amount;
    public String date;

    @Override
    public int compareTo(Income bill) {
        return date.compareTo(bill.date);
    }
}
