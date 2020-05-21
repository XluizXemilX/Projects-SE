package com.example.bottomnamviagtionbar.Helpers;

import android.widget.EditText;

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

    // validate edittext(check if edittexts are empty)
    public boolean ValidateFields(EditText... editTexts){
        for(EditText editText: editTexts){
            if(editText != null
                    && editText.getText() != null
                    && editText.getText().toString().trim().length() == 0){
                return false;
            }
        }
        return true;
    }

    public boolean StringEquality(String a, String b){
        return a.equals(b);
    }
}
