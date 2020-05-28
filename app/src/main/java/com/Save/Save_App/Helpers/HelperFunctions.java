package com.Save.Save_App.Helpers;

import android.widget.EditText;

import com.Save.Save_App.Interfaces.Bill;

import java.util.ArrayList;

public class HelperFunctions {

    public float[] mapBillCategories(ArrayList<Bill> bills){
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
