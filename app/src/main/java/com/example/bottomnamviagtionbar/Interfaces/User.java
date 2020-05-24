package com.example.bottomnamviagtionbar.Interfaces;

import android.content.Intent;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.ArrayList;

public class User {
   private String name;
   private String password;
   private String email;
   private float income;
   private ArrayList<Bill> bills;
   private List<String> histories;

   public User(){
      name = null;
      password =null;
      email =null;
      income = 0.0f;
      bills = null;
      histories = null;
   }

   public User(String _name, String _password, String _email){
      name = _name;
      password = _password;
      email = _email;
      income = 0;
      bills = null;
      histories = null;
   }

   public String getEmail() {
      return email;
   }

   public String getName() {
      return name;
   }

   public String getPassword() {
      return password;
   }

   public float getIncome() {
      return income;
   }

   public ArrayList<Bill> getBills() {
      return bills;
   }

   public List<String> getHistories() {
      return histories;
   }

   public void setBills(ArrayList<Bill> bills) {
      this.bills = bills;
   }

   public void addBill(Bill bill) {
      this.bills.add(bill);
   }

    public void removeBill(Bill bill) {
      this.bills.remove(bill);
   }

   public void setHistories(List<String> histories) {
      this.histories = histories;
   }

   public void addHistory(String history) {
      this.histories.add(history);
   }

   public void addHistory(int index, String history) {
      this.histories.add(index, history);
   }

   public void removeHistory(String history) {
      this.histories.remove(history);
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public void setIncome(float income) {
      this.income = income;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setPassword(String password) {
      this.password = password;
   }

}



