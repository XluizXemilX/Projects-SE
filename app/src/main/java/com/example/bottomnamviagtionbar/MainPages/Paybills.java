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

import com.example.bottomnamviagtionbar.Helpers.Category;
import com.example.bottomnamviagtionbar.Helpers.DecimalDigitsInputFilter;
import com.example.bottomnamviagtionbar.Helpers.HelperFunctions;
import com.example.bottomnamviagtionbar.MainPages.Budget.BudgetPage;
import com.example.bottomnamviagtionbar.Settings.NotificationPage;
import com.example.bottomnamviagtionbar.Interfaces.Bill;
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

    //private ActionBar toolbar;
    public enum Category{
        Rent,  //0
        Services,
        Food,
        Entertainment,
        Clothes,
        Other
    }
    Spinner spinner,spinnermonth,spinnertype,Monthspinner,Dayspinner;
    EditText amount,AddAmount,RecurAmount;
    Button Addbill,AddBalance,RecurBill;
    SharedPreferences preferences;
    float[] categoryValues;
    ArrayList<Bill> bills = new ArrayList<>();
    public static List<String> History = new ArrayList<>();
    ArrayList<Spinner> spinners = new ArrayList<>();
    ArrayList<EditText> editTexts = new ArrayList<>();

    public float[] GetCatergoyValue()
    {
        return categoryValues;
    }

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

        History = getArrayList("History");

        preferences = getSharedPreferences("UserInfo", 0);
        final String Teemp = preferences.getString("Income", "");
        spinner = findViewById(R.id.spinner1);
        Monthspinner = findViewById(R.id.MonthSpinner);
        Dayspinner = findViewById(R.id.DaySpinner);
        amount = findViewById(R.id.etAmount);
        amount.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(100,2)});
        Addbill = findViewById(R.id.addbillbutton);
        final ArrayAdapter<String> MonthCatagory = new ArrayAdapter<>(
                Paybills.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.Months)
        );
        Monthspinner.setAdapter(MonthCatagory);
        MonthCatagory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final ArrayAdapter<String> DayCatagory = new ArrayAdapter<>(
                Paybills.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.Dates)
        );
        Dayspinner.setAdapter(DayCatagory);
        DayCatagory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final ArrayAdapter<String> arrayAdapterCategories = new ArrayAdapter<String>(
                Paybills.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.categories));
        arrayAdapterCategories.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapterCategories);
        spinners.add(spinner);
        editTexts.add(amount);
        Addbill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bills.clear();
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


                float sum =0;
                bills.clear();

                for(int i = 0; i < editTexts.size();i++) {
                    EditText et = editTexts.get(i);
                    String etString = et.getText().toString();
                    if(!etString.isEmpty())
                    {
                        float amount = Float.valueOf(etString);
                        sum += amount;
                        Spinner spinner = spinners.get(i);
                        int spinnerPos = spinner.getSelectedItemPosition();
                        com.example.bottomnamviagtionbar.Helpers.Category catergories = com.example.bottomnamviagtionbar.Helpers.Category.values()[spinnerPos];
                        Bill _bill = new Bill();
                        _bill.amount = amount;
                        _bill.category = catergories;
                        bills.add(_bill);

                    }
                }







                SharedPreferences.Editor editor = preferences.edit();
                HelperFunctions helper = new HelperFunctions();
                categoryValues = helper.AddCategories(bills);


                String rent = String.valueOf(categoryValues[0]);
                editor.putString("Rent_Ex", rent);
                String services = String.valueOf(categoryValues[1]);
                editor.putString("Services_Ex", services);
                String food = String.valueOf(categoryValues[2]);
                editor.putString("Food_Ex", food);
                String entertain = String.valueOf(categoryValues[3]);
                editor.putString("Entertainment_Ex", entertain);
                String clothes = String.valueOf(categoryValues[4]);
                editor.putString("Clothes_Ex", clothes);
                String other = String.valueOf(categoryValues[5]);
                editor.putString("Other_Ex", other);
                editor.commit();
                //Final string adding all parts.
                String SelectedString = "0";
                int selector = spinner.getSelectedItemPosition();
                if (selector == 0){
                    SelectedString = "Rent";
                }
                else if (selector == 1){
                    SelectedString = "Services";
                }
                else if (selector == 2){
                    SelectedString = "Food";
                }
                else if (selector == 3){
                    SelectedString = "Entertainment";
                }
                else if (selector == 4){
                    SelectedString = "Clothes";
                }
                else{
                    SelectedString = "Other";
                }
                String HistoryString = SelectedString + " for $" + temp + " at " + DateATime;
                History.add(HistoryString);
                //Put History into shared prefrences.
                saveArrayList(History,"History");
                TextView txt = (TextView) findViewById(R.id.HView);
                txt.setText(HistoryString);



            }
        });

        ///////////////////////////////////////////////////////////////////////////
        AddAmount = findViewById(R.id.AddBalanceAmount);
        AddAmount.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(100,2)});
        AddBalance = findViewById(R.id.AddBalanceButton);
        AddBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                //Adding amount for adding to budget
                String temp = AddAmount.getText().toString();
                if(temp.length() == 0){
                    temp = "0.00";
                }
                //Date and Time format
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();
                String DateATime = formatter.format(date);
                //History String
                Double NewBudget = Double.parseDouble(Teemp);
                Double OldBudget = Double.parseDouble(temp);
                NewBudget += OldBudget;
                String TTTT = String.valueOf(NewBudget);
                SharedPreferences.Editor Edit = preferences.edit();
                Edit.putString("Income",TTTT);
                Edit.apply();

                String HistoryString = "+$" + temp + " at " + DateATime + ".\n New Balance " + NewBudget;
                History.add(HistoryString);
                //Put History into shared pref.
                saveArrayList(History,"History");
                TextView txt = (TextView) findViewById(R.id.AddBalanceTextView);
                txt.setText(HistoryString);
            }
        });

        ///////////////////////////////////////////////////////////////////////////
        spinnermonth = findViewById(R.id.spinner3);
        spinnertype = findViewById(R.id.spinner4);
        final ArrayAdapter<String> ArrayAdapterCategories = new ArrayAdapter<>(
                Paybills.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.spinner));
        ArrayAdapterCategories.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnertype.setAdapter(arrayAdapterCategories);
        spinnermonth.setAdapter(ArrayAdapterCategories);
        RecurAmount = findViewById(R.id.editText3);
        RecurAmount.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(100,2)});
        RecurBill = findViewById(R.id.AddRecurrBill);
        RecurBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Recurring Amount
                String temp = RecurAmount.getText().toString();
                if (temp.length() == 0){
                    temp = "0.00";
                }
                //If statements for spinners
                int ExpenseTypePosition = spinnertype.getSelectedItemPosition();
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
                int ExpenseRecurPosition = spinnermonth.getSelectedItemPosition();
                String ExpenseRecur;
                if (ExpenseRecurPosition == 0){
                    ExpenseRecur = "Monthly";
                }
                else if (ExpenseRecurPosition == 1){
                    ExpenseRecur = "Weekly";
                }
                else if (ExpenseRecurPosition == 2){
                    ExpenseRecur = "Yearly";
                }
                else if (ExpenseRecurPosition == 3){
                    ExpenseRecur = "Bi-Monthly";
                }
                else {
                    ExpenseRecur = "Bi-Weekly";
                }
                //Date and Time format
                String HistoryString = ExpenseType + " = Type " + temp + " = Amount " + ExpenseRecur + " = Recur";
                History.add(HistoryString);
                saveArrayList(History,"History");
                TextView txt = (TextView) findViewById(R.id.TempView3);
                txt.setText(HistoryString);
            }
        });
        ///////////////////////////////////////////////////////////////////////////
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
    public void saveArrayList(List<String> list, String key){
        SharedPreferences prefs = getSharedPreferences("UserInfo",0);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();

    }

    public List<String> getArrayList(String key){
        SharedPreferences prefs = getSharedPreferences("UserInfo",0);
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<List<String>>(){}.getType();
        return gson.fromJson(json, type);
    }
}
