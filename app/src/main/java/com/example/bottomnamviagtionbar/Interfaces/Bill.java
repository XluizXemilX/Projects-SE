package com.example.bottomnamviagtionbar.Interfaces;


import com.example.bottomnamviagtionbar.Helpers.Category;

public class Bill implements Comparable <Bill>{

    public Category category;
    public float amount;
    public String date;

    @Override
    public int compareTo(Bill bill) {
        return date.compareTo(bill.date);
    }
}
