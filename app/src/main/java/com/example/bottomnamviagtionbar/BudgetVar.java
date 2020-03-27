package com.example.bottomnamviagtionbar;

public class BudgetVar {
    public float MonthlyLimit;
    public float WeeklyLimit;
    public float DailyLimit;

    public void SetMonthlyLimit(float m){MonthlyLimit = m;}
    public void SetWeeklyLimit(float w){WeeklyLimit = w;}
    public void SetDailyLimit(float d){DailyLimit = d;}

    public float GetMonthlyLimit(){return MonthlyLimit;}
    public float GetWeeklyLimit(){return WeeklyLimit;}
    public float GetDailyLimit(){return DailyLimit;}
}
