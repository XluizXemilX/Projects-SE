package com.example.bottomnamviagtionbar.Helpers;

import com.example.bottomnamviagtionbar.Interfaces.Bill;

import java.util.ArrayList;

public class HelperFunctions {

    public float AddCategories(ArrayList<Bill> bills){
        float sum = 0;
        for(int i =0; i<bills.size();i++){
            Float  value = bills.get(i).amount;
            Category category = bills.get(i).category;
            if(category.equals("Rent")){
                sum += value;
            }
            else if(category.equals("Services")){
                sum += value;
            }
            else if(category.equals("Food")){
                sum += value;
            }
            else if(category.equals("Clothes")){
                sum += value;
            }
            else if(category.equals("Entertainment")){
                sum += value;
            }
            else{
                sum += value;
            }


        }
        return sum;
    }
}
