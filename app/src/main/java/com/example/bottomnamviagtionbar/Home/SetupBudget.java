package com.example.bottomnamviagtionbar.Home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
        temp1 = ((EditText)findViewById(R.id.Monthly_Limit)).getText().toString().trim();
        temp2 = ((EditText)findViewById(R.id.Weekly_Limit)).getText().toString().trim();
        temp3 = ((EditText)findViewById(R.id.Daily_Limit)).getText().toString().trim();

        monthlyLim = Integer.decode(temp1);
        WeeklyLim = Integer.decode(temp2);
        DailyLim = Integer.decode(temp3);

        BudgetVar BV = new BudgetVar();
        BV.MonthlyLimit = monthlyLim;
        //BV.SetMonthlyLimit(monthlyLim);
        BV.SetWeeklyLimit(WeeklyLim);
        BV.SetDailyLimit(DailyLim);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
