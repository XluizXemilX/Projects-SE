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
    public View WeeklyLim;
    public View DailyLim;

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
        monthlyLim = 12.3f; //I need to find a way to get the user input into a float and not a view
        WeeklyLim = findViewById(R.id.Weekly_Limit);
        DailyLim = findViewById(R.id.Daily_Limit);
        BudgetVar BV = new BudgetVar();
        BV.SetMonthlyLimit(monthlyLim);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
