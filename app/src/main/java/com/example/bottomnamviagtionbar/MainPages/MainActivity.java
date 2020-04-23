package com.example.bottomnamviagtionbar.MainPages;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;


import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.example.bottomnamviagtionbar.MainPages.Budget.BudgetPage;
import com.example.bottomnamviagtionbar.Settings.NotificationPage;
import com.example.bottomnamviagtionbar.R;
import com.example.bottomnamviagtionbar.RergistrationAndLogin.Login;
import com.example.bottomnamviagtionbar.Settings.settings;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    CalendarView calendar;
    SharedPreferences preference;
    AnyChartView pieChart;




    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        preference = getSharedPreferences("UserInfo", 0);
        String BudVal = preference.getString("Income", "");

        //chart
        pieChart = findViewById(R.id.any_chart_view);
        setPieChart();

        Toolbar topbar = findViewById(R.id.topbar);
        topbar.setTitle("Home");
        topbar.setTitleTextColor(0xFFFFFFFF);
        setSupportActionBar(topbar);
        topbar.setNavigationIcon(R.drawable.ic_notifications_black_24dp);
        topbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, NotificationPage.class);
                startActivity(i);
            }
        });

        BottomNavigationView navigation = findViewById(R.id.navigationView);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:


                        break;
                    case R.id.account:

                        Intent a = new Intent(MainActivity.this, AccountPage.class);
                        startActivity(a);
                        break;
                    case R.id.budget:

                        Intent b = new Intent(MainActivity.this, BudgetPage.class);
                        startActivity(b);
                        break;
                    case R.id.history:

                        Intent c = new Intent(MainActivity.this, History.class);
                        startActivity(c);
                        break;
                    case R.id.paybills:

                        Intent d = new Intent(MainActivity.this, Paybills.class);
                        startActivity(d);
                        break;
                }
                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top, menu);
        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                Intent i = new Intent(this, settings.class);
                startActivity(i);
                break;
            case R.id.item2:
                Intent b = new Intent(this, Login.class);
                startActivity(b);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
        }
        return super.onOptionsItemSelected(item);
    }

    //chart method
    public void setPieChart(){

        Pie pie = AnyChart.pie();
        List<DataEntry>dataEntries= new ArrayList<>();
        //Income
        String val = preference.getString("Income", "");
        Float income = Float.valueOf(val);
        //Paid Bills
        //String paid = preference.getString("Paid", "");
        //Float paidB = Float.valueOf(paid);
        ////left for speding or savings
        //String save = preference.getString("Savings", "");
        //Float savings = Float.valueOf(save);

        dataEntries.add(new ValueDataEntry("Income", income));
        //dataEntries.add(new ValueDataEntry("Bills",paidB));
        //dataEntries.add(new ValueDataEntry("Saving", savings));
        pie.data(dataEntries);
        pieChart.setChart(pie);

    }
    
}
