package com.Save.Save_App.MainPages;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.Save.Save_App.Helpers.Frequency;
import com.Save.Save_App.Interfaces.User;
import com.Save.Save_App.Helpers.DecimalDigitsInputFilter;
import com.Save.Save_App.Helpers.HelperFunctions;
import com.Save.Save_App.Helpers.SharedPrefsUtil;
import com.Save.Save_App.MainPages.Budget.BudgetPage;
import com.Save.Save_App.Settings.NotificationPage;
import com.Save.Save_App.Interfaces.Bill;
import com.Save.Save_App.Helpers.Category;
import com.Save.Save_App.R;
import com.Save.Save_App.RergistrationAndLogin.Login;
import com.Save.Save_App.Settings.settings;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Paybills extends AppCompatActivity {

    Spinner categorySpinner, categorySpinnerRecurring;
    EditText etAddbills, etAddbalance, recurrent_amount, etDate;
    Button btnAddbill, btnAddBalance, btnRecurBill;
    SharedPrefsUtil sharedPrefsUtil;
    HelperFunctions helperFunc;
    User user;
    String email;
    private Matcher matcher;

    private String DATE_PATTERN =
            "(0[1-9]|1[0-9]|2[0-9]|3[0-1])";

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

        etAddbills = findViewById(R.id.etAmount);
        etAddbills.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(100,2)});
        btnAddbill = findViewById(R.id.addbillbutton);
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
                        String.format("%.2f",Float.valueOf(etAddbills.getText().toString())),
                    dateText,
                        String.format("%.2f",savings)
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
                String historyString = String.format("$%s added on %s.\nNew Balance %s",
                        String.format("%.2f",balance),
                        DateATime,
                        String.format("%.2f",user.getIncome()));

                //Put History into shared pref.
                user.addHistory(0, historyString);
                sharedPrefsUtil.put(email, User.class, user);
                TextView txt = (TextView) findViewById(R.id.AddBalanceTextView);
                txt.setText(historyString);
            }
        });


        categorySpinnerRecurring = findViewById(R.id.spinner4);
        etDate = findViewById(R.id.etDate);
        etDate.setInputType(InputType.TYPE_CLASS_NUMBER);
        final ArrayAdapter<String> ArrayAdapterCategories = new ArrayAdapter<>(
                Paybills.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.spinner));
        ArrayAdapterCategories.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinnerRecurring.setAdapter(arrayAdapterCategories);

        recurrent_amount = findViewById(R.id.editText3);
        recurrent_amount.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(100,2)});
        btnRecurBill = findViewById(R.id.AddRecurrBill);
        btnRecurBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Recurring Amount
                if(!helperFunc.ValidateFields(recurrent_amount)){
                    recurrent_amount.setText("0.00");
                }
                if(!helperFunc.ValidateFields(etDate)){
                    etDate.setText("01");
                }
                String recurringText = recurrent_amount.getText().toString();//amount
                String categorySpinnerText = categorySpinnerRecurring.getSelectedItem().toString();//category

                String inputDate = etDate.getText().toString();
                matcher = Pattern.compile(DATE_PATTERN).matcher(inputDate);
                if (!matcher.matches()) {
                    Toast.makeText(getApplicationContext(), "Invalid Day!", Toast.LENGTH_SHORT).show();
                }
                else {
                    // creates the bill and sets its values
                    Bill bill = new Bill();
                    int spinnerPos = categorySpinnerRecurring.getSelectedItemPosition();

                    Category categories = Category.values()[spinnerPos];

                    bill.amount = Float.valueOf(recurringText);
                    bill.category = categories;
                    bill.date = inputDate;
                    user.addBill(bill);
                    user.addRecurringBills(bill);


                    //History String
                    String historyString = String.format(
                            "Type: %s \t Amount: %s \t Due: %s",
                            categorySpinnerText,
                            recurringText,
                            inputDate + "th"
                    );

                    //Put History into shared pref.
                    user.addHistory(0, historyString);
                    user.addBllsAmount(bill.amount);
                    sharedPrefsUtil.put(email, User.class, user);
                    TextView txt = (TextView) findViewById(R.id.TempView3);
                    txt.setText(historyString);
                }
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
