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

import com.example.bottomnamviagtionbar.Interfaces.User;
import com.example.bottomnamviagtionbar.Helpers.DecimalDigitsInputFilter;
import com.example.bottomnamviagtionbar.Helpers.HelperFunctions;
import com.example.bottomnamviagtionbar.Helpers.SharedPrefsUtil;
import com.example.bottomnamviagtionbar.MainPages.Budget.BudgetPage;
import com.example.bottomnamviagtionbar.Settings.NotificationPage;
import com.example.bottomnamviagtionbar.Interfaces.Bill;
import com.example.bottomnamviagtionbar.Helpers.Category;
import com.example.bottomnamviagtionbar.R;
import com.example.bottomnamviagtionbar.RergistrationAndLogin.Login;
import com.example.bottomnamviagtionbar.Settings.settings;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Paybills extends AppCompatActivity {

    Spinner categorySpinner, frequencySpinner, categorySpinnerRecurring, monthSpinner, daySpinner;
    EditText etAddbills, etAddbalance, etAddrecurring;
    Button btnAddbill, btnAddBalance, btnRecurBill;
    SharedPrefsUtil sharedPrefsUtil;
    HelperFunctions helperFunc;
    User user;
    String email;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paybills);

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

        helperFunc = new HelperFunctions();
        sharedPrefsUtil = new SharedPrefsUtil(this);
        email = sharedPrefsUtil.get("email_income", "");
        user = sharedPrefsUtil.get(email, User.class, new User());

        if(user.getHistories() == null)
        {
            user.setHistories(new ArrayList<String>());
        }

        if(user.getBills() == null)
        {
            user.setBills(new ArrayList<Bill>());
        }

        categorySpinner = findViewById(R.id.spinner1);
        monthSpinner = findViewById(R.id.MonthSpinner);
        daySpinner = findViewById(R.id.DaySpinner);
        etAddbills = findViewById(R.id.etAmount);
        etAddbills.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(100,2)});
        btnAddbill = findViewById(R.id.addbillbutton);
        final ArrayAdapter<String> MonthCatagory = new ArrayAdapter<>(
                Paybills.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.Months)
        );
        monthSpinner.setAdapter(MonthCatagory);
        MonthCatagory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final ArrayAdapter<String> DayCatagory = new ArrayAdapter<>(
                Paybills.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.Dates)
        );
        daySpinner.setAdapter(DayCatagory);
        DayCatagory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final ArrayAdapter<String> arrayAdapterCategories = new ArrayAdapter<String>(
                Paybills.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.categories));
        arrayAdapterCategories.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(arrayAdapterCategories);

        btnAddbill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Converting input to string getting ready for format.
                //Format: ("Type of expense"  "Amount"  "Date & Time")
                //Amount to string
                if(!helperFunc.ValidateFields(etAddbills)){
                    etAddbills.setText("0.00");
                }

                //Date and time into string
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();
                String dateText = formatter.format(date);

                String categorySpinnerText = categorySpinner.getSelectedItem().toString();
                int spinnerPos = categorySpinner.getSelectedItemPosition();
                Category catergories = Category.values()[spinnerPos];

                String billText = etAddbills.getText().toString();
                Bill bill = new Bill();
                bill.amount = Float.valueOf(billText);
                bill.category = catergories;
                bill.date = dateText;
                user.addBill(bill);

                // TODO: Figure out a better way for savings
                user.addBllsAmount(bill.amount);
                float savings = user.getIncome() - user.getBillsAmount();

                //History String
                String historyString = String.format(
                    "%s bill of $%s was added on %s. Current savings is %s",
                    categorySpinnerText,
                    billText,
                    dateText,
                    Float.toString(savings)
                );

                //Put History into shared pref.
                user.addHistory(0, historyString);

                sharedPrefsUtil.put(email, User.class, user);
                TextView txt = (TextView) findViewById(R.id.HView);
                txt.setText(historyString);
            }
        });

        etAddbalance = findViewById(R.id.AddBalanceAmount);
        etAddbalance.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(100,2)});
        btnAddBalance = findViewById(R.id.AddBalanceButton);
        btnAddBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                //Adding amount for adding to budget
                if(!helperFunc.ValidateFields(etAddbalance)){
                    etAddbalance.setText("0.00");
                }
                float balance = Float.valueOf(etAddbalance.getText().toString());
                user.setIncome(user.getIncome() + balance);

                //Date and Time format
                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                String DateATime = formatter.format(date);

                //History String
                String historyString = String.format("$%s added on %s.\nNew Balance %s", balance, DateATime,Float.toString(user.getIncome()));

                //Put History into shared pref.
                user.addHistory(0, historyString);
                sharedPrefsUtil.put(email, User.class, user);
                TextView txt = (TextView) findViewById(R.id.AddBalanceTextView);
                txt.setText(historyString);
            }
        });

        frequencySpinner = findViewById(R.id.spinner3);
        categorySpinnerRecurring = findViewById(R.id.spinner4);
        final ArrayAdapter<String> ArrayAdapterCategories = new ArrayAdapter<>(
                Paybills.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.spinner));
        ArrayAdapterCategories.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinnerRecurring.setAdapter(arrayAdapterCategories);
        frequencySpinner.setAdapter(ArrayAdapterCategories);
        etAddrecurring = findViewById(R.id.editText3);
        etAddrecurring.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(100,2)});
        btnRecurBill = findViewById(R.id.AddRecurrBill);
        btnRecurBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Recurring Amount
                if(!helperFunc.ValidateFields(etAddrecurring)){
                    etAddrecurring.setText("0.00");
                }
                String recurringText = etAddrecurring.getText().toString();
                String categorySpinnerText = categorySpinnerRecurring.getSelectedItem().toString();
                String frequencySpinnerText = frequencySpinner.getSelectedItem().toString();

                //History String
                String historyString = String.format(
                    "Type: %s\tAmount: %s\tRecurring Type: %s",
                    categorySpinnerText,
                    recurringText,
                    frequencySpinnerText
                );

                //Put History into shared pref.
                user.addHistory(0, historyString);
                user.addBllsAmount(Float.parseFloat(etAddrecurring.getText().toString()));
                sharedPrefsUtil.put(email, User.class, user);
                TextView txt = (TextView) findViewById(R.id.TempView3);
                txt.setText(historyString);
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
                        Intent i = new Intent(Paybills.this, MainActivity.class);
                        startActivity(i);
                        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                        break;
                    case R.id.account:
                        Intent a = new Intent(Paybills.this, AccountPage.class);
                        startActivity(a);
                        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                        break;
                    case R.id.budget:
                        Intent b = new Intent(Paybills.this, BudgetPage.class);
                        startActivity(b);
                        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                        break;
                    case R.id.history:
                        Intent c = new Intent(Paybills.this, History.class);
                        startActivity(c);
                        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                        break;
                    case R.id.paybills:
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
}
