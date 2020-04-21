package com.example.bottomnamviagtionbar;

public class BudgetVar {
    public static float MonthlyLimit = 11111.11f;
    public static float WeeklyLimit;// = 11111.11f;
    public static float DailyLimit = 11111.11f;

    public void SetMonthlyLimit(float m){MonthlyLimit = m;}
    public void SetWeeklyLimit(float w){WeeklyLimit = w;}
    public void SetDailyLimit(float d){DailyLimit = d;}

    public float GetMonthlyLimit(){return MonthlyLimit;}
    public float GetWeeklyLimit(){return WeeklyLimit;}
    public float GetDailyLimit(){return DailyLimit;}
}
