package com.example.bottomnamviagtionbar.Interfaces;

import java.util.ArrayList;
import java.util.List;

public class User {
   private String name;
   private String password;
   private String email;
   private float income;
   private float income_reset;
   private ArrayList<Bill> bills;
   private List<String> histories;

   public User(){

   }

   public User(String _name, String _password, String _email){
      name = _name;
      password = _password;
      email = _email;
      income_reset = 0;
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

   public float getIncome_reset() {
      return income_reset;
   }

   public void setIncome_reset(float income_reset) {
      this.income_reset = income_reset;
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



