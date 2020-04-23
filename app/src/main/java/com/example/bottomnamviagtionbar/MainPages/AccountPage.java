package com.example.bottomnamviagtionbar.MainPages;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.bottomnamviagtionbar.MainPages.Budget.BudgetPage;
import com.example.bottomnamviagtionbar.Settings.NotificationPage;
import com.example.bottomnamviagtionbar.BudgetVar;

import com.example.bottomnamviagtionbar.R;
import com.example.bottomnamviagtionbar.RergistrationAndLogin.Login;
import com.example.bottomnamviagtionbar.Settings.settings;

public class AccountPage extends AppCompatActivity {

    //private ActionBar toolbar;



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        

        //toolbar = getSupportActionBar();

        Toolbar topbar = findViewById(R.id.topbar);
        topbar.setTitle("Accounts");
        topbar.setTitleTextColor(0xFFFFFFFF);
        setSupportActionBar(topbar);
        topbar.setNavigationIcon(R.drawable.ic_notifications_black_24dp);
        topbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AccountPage.this, NotificationPage.class);
                startActivity(i);
            }
        });

        BottomNavigationView navigation = findViewById(R.id.navigationView);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        //toolbar.setTitle("Home");
                        //loadFragment(new HomeFragment());
                        //return true;
                        Intent i = new Intent(AccountPage.this, MainActivity.class);
                        startActivity(i);
                        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                        break;
                    case R.id.account:
                        //toolbar.setTitle("AccountPage");
                        //loadFragment(new AccountFragment());
                        //return true;
                        break;
                    case R.id.budget:
                        //toolbar.setTitle("Budget");
                        //loadFragment(new BudgetFragment());
                        //return true;
                        Intent b = new Intent(AccountPage.this, BudgetPage.class);
                        startActivity(b);
                        break;
                    case R.id.history:
                        //toolbar.setTitle("History");
                        //loadFragment(new HistoryFragment());
                        //return true;
                        Intent c = new Intent(AccountPage.this, History.class);
                        startActivity(c);
                        break;
                    case R.id.paybills:
                        //toolbar.setTitle("PayBills");
                        //loadFragment(new PaybillsFragment());
                        //return true;
                        Intent d = new Intent(AccountPage.this, Paybills.class);
                        startActivity(d);
                        break;
                }
                return false;
            }
        });

         //toolbar.setTitle("Account");

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
                Intent a = new Intent(this, Login.class);
                startActivity(a);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
