package com.example.bottomnamviagtionbar.MainPages;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.charts.Pie;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.example.bottomnamviagtionbar.Helpers.BackgroundService;
import com.example.bottomnamviagtionbar.Helpers.HelperFunctions;
import com.example.bottomnamviagtionbar.Helpers.PieChartData;
import com.example.bottomnamviagtionbar.Helpers.SharedPrefsUtil;
import com.example.bottomnamviagtionbar.Interfaces.Bill;
import com.example.bottomnamviagtionbar.Interfaces.User;
import com.example.bottomnamviagtionbar.MainPages.Budget.BudgetPage;
import com.example.bottomnamviagtionbar.R;
import com.example.bottomnamviagtionbar.RergistrationAndLogin.Login;
import com.example.bottomnamviagtionbar.Settings.NotificationPage;
import com.example.bottomnamviagtionbar.Interfaces.User;
import com.example.bottomnamviagtionbar.Settings.settings;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import java.util.Date;

import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    CalendarView calendar;
    Calendar cal;
    SharedPrefsUtil sharedPrefsUtil;
    HelperFunctions helperFunc;
    User user;
    AnyChartView pieChart;
    Pie pie;
    String Month;
    float budget;

    Timer timer = new Timer();
    final Handler handler = new Handler();

    TimerTask timertask = new TimerTask() {
        @Override
        public void run() {
            handler.post(new Runnable() {
                public void run() {
                    startService(new Intent(getApplicationContext(),
                            BackgroundService.class));
                }
            });
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helperFunc = new HelperFunctions();
        sharedPrefsUtil = new SharedPrefsUtil(this);
        String email = sharedPrefsUtil.get("email_income", "");
        user = sharedPrefsUtil.get(email, User.class, new User());

        //chart
        pieChart = findViewById(R.id.any_chart_view);
        pie = AnyChart.pie();
        pieChart.setChart(pie);
        final ArrayList<DataEntry> dataEntries = new ArrayList<>();

        /////
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM");
        String DateATime = formatter.format(date);


        String CurrentMonth = user.getCurrentMonth();
        if (!CurrentMonth.equals(DateATime)){
            user.setIncome(user.getOriginalIncome());
            user.setCurrentMonth(DateATime);
            user.setBillsAmount(0);
            sharedPrefsUtil.put(email, User.class, user);
        }
        ////

        ArrayList<Bill> bills = user.getBills();
        if(bills != null)
        {
            float[] billCategoryMap = helperFunc.mapBillCategories(bills);
            String []categoryString = {"Rent", "Services", "Food", "Entertainment", "Clothes", "Other"};
            float billTotal = 0;
            for(int i = 0; i < billCategoryMap.length; ++i ){
                billTotal += billCategoryMap[i];
                dataEntries.add(new ValueDataEntry(categoryString[i], billCategoryMap[i]));
            }
            dataEntries.add(new ValueDataEntry("Savings", user.getIncome() - billTotal));
        }

        pie.data(dataEntries);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pie.data(dataEntries);

            }
        }, 100);

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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Intent i = new Intent(this, settings.class);
                startActivity(i);
                break;
            case R.id.item2:
                Intent b = new Intent(this, Login.class);
                startActivity(b);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();

        timer.schedule(timertask, 0,  5 * 1000);
    }


    @Override
    protected void onStop() {
        super.onStop();
        timer.cancel();
    }
}
