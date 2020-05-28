package com.Save.App.Interfaces;


import java.util.ArrayList;
import java.util.List;

public class User {
   private String name;
   private String password;
   private String email;
   private float income;
   private ArrayList<Bill> bills;
   private ArrayList<Bill> recurrent_bills;
   private List<String> histories;
   private float originalIncome;
   private String currentMonth;
   private float billsAmount;
   private float[] monthBills;

   public User(){
      recurrent_bills = new ArrayList<>();
      originalIncome = 0.0f;
      currentMonth = null;
      billsAmount = 0.0f;
      monthBills = new float[12];
   }

   public User(String _name, String _password, String _email){
      name = _name;
      password = _password;
      email = _email;
      income = 0;
      bills = null;
      histories = null;
      originalIncome = 0;
      currentMonth = null;
      billsAmount = 0;
      recurrent_bills = new ArrayList<>();
      monthBills = new float[12];
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

   public ArrayList<Bill> getRecurrent_bills(){
      return recurrent_bills;
   }

   public void setRecurrent_bills(ArrayList<Bill> recurrent_bills) {
      this.recurrent_bills = recurrent_bills;
   }

   public float getOriginalIncome() {
      return originalIncome;
   }

   public String getCurrentMonth() {
      return currentMonth;
   }

   public float getBillsAmount() {
      return billsAmount;
   }

   public float getMonthBills(int index) {
      return monthBills[index];
   }

   public void setBills(ArrayList<Bill> bills) {
      this.bills = bills;
   }

   public void addBill(Bill bill) {
      this.bills.add(bill);
   }

   public void addRecurringBills(Bill bill) {
      this.recurrent_bills.add(bill);
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

   public void setOriginalIncome(float originalIncome){
      this.originalIncome = originalIncome;
   }

   public void setCurrentMonth(String currentMonth) {
      this.currentMonth = currentMonth;
   }

   public void setBillsAmount(float billsAmount) {
      this.billsAmount = billsAmount;
   }

   public void addBllsAmount(float billsAmount) {
      this.billsAmount += billsAmount;
   }

   public void setMonthBills(float monthBills, int index) {
      this.monthBills[index] = monthBills;
   }

}


