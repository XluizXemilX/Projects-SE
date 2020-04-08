package com.example.bottomnamviagtionbar.Home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bottomnamviagtionbar.BudgetVar;
import com.example.bottomnamviagtionbar.R;


public class SetupBudget extends AppCompatActivity {
    public float monthlyLim;
    public float WeeklyLim;
    public float DailyLim;
    public String temp1;
    public String temp2;
    public String temp3;

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_budget);
        button = (Button) findViewById(R.id.TomainButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMain();
            }
        });
    }
    public void openMain(){
        //temp1 = findViewById(R.id.Monthly_Limit).toString();
        //temp2 = findViewById(R.id.Weekly_Limit).toString();
        //temp3 = findViewById(R.id.Daily_Limit).toString();

        monthlyLim = Integer.parseInt(temp1); //I need to find a way to get the user input into a float and not a view
        //WeeklyLim = Integer.parseInt(temp2);
        //DailyLim = Integer.parseInt(temp3);


        BudgetVar BV = new BudgetVar();
        BV.SetMonthlyLimit(monthlyLim);
        BV.SetWeeklyLimit(WeeklyLim);
        BV.SetDailyLimit(DailyLim);

        System.out.print("Limits");
        System.out.print(temp1);
        System.out.print(WeeklyLim);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
