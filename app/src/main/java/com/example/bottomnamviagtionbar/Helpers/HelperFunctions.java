package com.example.bottomnamviagtionbar.Helpers;

import com.example.bottomnamviagtionbar.Interfaces.Bill;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class HelperFunctions {

    public float[] AddCategories(ArrayList<Bill> bills){
        final int count = Category.values().length;
        float[] results = new float[count];

        for(Bill bill: bills){
            int index = bill.category.ordinal();
            results[index] += bill.amount;
        }
        return results;
    }
}
