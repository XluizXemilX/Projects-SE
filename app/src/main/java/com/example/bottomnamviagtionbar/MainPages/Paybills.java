package com.example.bottomnamviagtionbar.MainPages;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.bottomnamviagtionbar.Helpers.DecimalDigitsInputFilter;
import com.example.bottomnamviagtionbar.MainPages.Budget.BudgetPage;
import com.example.bottomnamviagtionbar.Settings.NotificationPage;
import com.example.bottomnamviagtionbar.R;
import com.example.bottomnamviagtionbar.RergistrationAndLogin.Login;
import com.example.bottomnamviagtionbar.Settings.settings;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Paybills extends AppCompatActivity {

    //private ActionBar toolbar;
    public enum Category{
        Rent,  //0
        Services,
        Food,
        Entertainment,
        Clothes,
        Other
    }
    Spinner spinner;
    EditText amount;
    Button Addbill;
    ArrayList<String> History;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paybills);

        //toolbar = getSupportActionBar();

        Toolbar topbar = findViewById(R.id.topbar);
        topbar.setTitle("Bill");
        topbar.setTitleTextColor(0xFFFFFFFF);
        setSupportActionBar(topbar);
        topbar.setNavigationIcon(R.drawable.ic_notifications_black_24dp);
        topbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Paybills.this, NotificationPage.class);
                startActivity(i);
            }
        });

        spinner = findViewById(R.id.spinner1);
        amount = findViewById(R.id.etAmount);
        amount.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(100,2)});
        Addbill = findViewById(R.id.addbillbutton);
        final ArrayAdapter<String> arrayAdapterCategories = new ArrayAdapter<String>(
                Paybills.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.categories));
        arrayAdapterCategories.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapterCategories);
        Addbill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Converting input to string getting ready for format.
                //Format: ("Type of expense"  "Amount"  "Date & Time")
                //Amount to string
                String temp = amount.getText().toString();
                if (temp.length() == 0){
                    temp = "0.00";
                }
                //Date and time into string
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();
                String DateATime = formatter.format(date);
                //Expense Type to string
                int ExpenseTypePosition = spinner.getSelectedItemPosition();
                String ExpenseType;
                if (ExpenseTypePosition == 0){
                    ExpenseType = "Rent";
                }
                else if (ExpenseTypePosition == 1){
                    ExpenseType = "Services";
                }
                else if (ExpenseTypePosition == 2){
                    ExpenseType = "Food";
                }
                else if (ExpenseTypePosition == 3){
                    ExpenseType = "Entertainment";
                }
                else if (ExpenseTypePosition == 4){
                    ExpenseType = "Clothes";
                }
                else{
                    ExpenseType = "Other";
                }
                //Final string adding all parts.
                String HistoryString = ExpenseType + " for $" + temp + " at " + DateATime;
                //History.add(HistoryString);
                TextView txt = (TextView) findViewById(R.id.HView);
                txt.setText(HistoryString);
            }
        });

        BottomNavigationView navigation = findViewById(R.id.navigationView);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(4);
        menuItem.setChecked(true);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        //toolbar.setTitle("Home");
                        //loadFragment(new HomeFragment());
                        //return true;
                        Intent i = new Intent(Paybills.this, MainActivity.class);
                        startActivity(i);
                        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                        break;
                    case R.id.account:
                        //toolbar.setTitle("AccountPage");
                        //loadFragment(new AccountFragment());
                        //return true;
                        Intent a = new Intent(Paybills.this, AccountPage.class);
                        startActivity(a);
                        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                        break;
                    case R.id.budget:
                        //toolbar.setTitle("Budget");
                        //loadFragment(new BudgetFragment());
                        //return true;
                        Intent b = new Intent(Paybills.this, BudgetPage.class);
                        startActivity(b);
                        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                        break;
                    case R.id.history:
                        //toolbar.setTitle("History");
                        //loadFragment(new HistoryFragment());
                        //return true;
                        Intent c = new Intent(Paybills.this, History.class);
                        startActivity(c);
                        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                        break;
                    case R.id.paybills:
                        //toolbar.setTitle("PayBills");
                        //loadFragment(new PaybillsFragment());
                        //return true;
                        break;
                }
                return false;
            }
        });

         //toolbar.setTitle("Paybills");

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
}
