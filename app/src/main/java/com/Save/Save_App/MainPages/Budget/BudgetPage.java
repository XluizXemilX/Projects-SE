package com.Save.Save_App.MainPages.Budget;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.Save.Save_App.Helpers.Category;
import com.Save.Save_App.Helpers.Frequency;
import com.Save.Save_App.Helpers.HelperFunctions;
import com.Save.Save_App.Helpers.PieChartData;
import com.Save.Save_App.Helpers.SharedPrefsUtil;
import com.Save.Save_App.Helpers.SpinnersHelper;
import com.Save.Save_App.Interfaces.Bill;
import com.Save.Save_App.Interfaces.User;
import com.Save.Save_App.MainPages.AccountPage;
import com.Save.Save_App.MainPages.History;
import com.Save.Save_App.MainPages.MainActivity;
import com.Save.Save_App.MainPages.Paybills;
import com.Save.Save_App.R;
import com.Save.Save_App.RergistrationAndLogin.Login;
import com.Save.Save_App.Settings.NotificationPage;
import com.Save.Save_App.Settings.settings;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BudgetPage extends AppCompatActivity {
    TextView  result;
    Toolbar topbar;
    EditText  Amount, Amount2, MainBudget;
    Button add, calculate;
    LinearLayout dynamicLayout;
    Spinner Spinner1, Spinner2, IncomeSpin;
    ArrayList<EditText> editTexts = new ArrayList<>();

    ArrayList<Spinner> spinners = new ArrayList<>();
    ArrayList<Bill> bills = new ArrayList<>();
    Frequency current_frequency;
    SpinnersHelper spinnersHelper;
    float[] categoryValues;

    AnyChartView pieChart;
    PieChartData data;
    Pie pie;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_page);

        pieChart = findViewById(R.id.any_chart_view_budget);
        pie = AnyChart.pie();
        pieChart.setChart(pie);
        data = new PieChartData();

        topbar = findViewById(R.id.topbar);
        topbar.setTitle("Budget");
        topbar.setTitleTextColor(0xFFFFFFFF);
        setSupportActionBar(topbar);
        topbar.setNavigationIcon(R.drawable.ic_notifications_black_24dp);
        topbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(BudgetPage.this, NotificationPage.class);
                startActivity(i);
            }
        });

        add = findViewById(R.id.btnAdd);
        calculate=findViewById(R.id.btnCalculate);
        result = findViewById(R.id.tvResult);

        Amount = findViewById(R.id.etAmount);
        Amount.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(100,2)});
        Amount2 = findViewById(R.id.etAmount2);
        Amount2.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(100,2)});
        Spinner1 = findViewById(R.id.spinner1);
        Spinner2 = findViewById(R.id.spinner2);

        //income fields
        IncomeSpin =findViewById(R.id.spinner);
        MainBudget =findViewById(R.id.editText2);
        MainBudget.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(100,2)});

        SharedPrefsUtil sharedPrefsUtil = new SharedPrefsUtil(this);
        String email = sharedPrefsUtil.get("email_income", "");
        User user = sharedPrefsUtil.get(email, User.class, new User());
        MainBudget.setText(String.valueOf(user.getIncome()));

        editTexts.add(Amount);
        editTexts.add(Amount2);

        //Budget spin adapter
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                BudgetPage.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.spinner));
        current_frequency = Frequency.Monthly;
        spinnersHelper = new SpinnersHelper();

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        IncomeSpin.setAdapter(arrayAdapter);
        //Categories Spin Adapter
        final ArrayAdapter<String> arrayAdapterCategories = new ArrayAdapter<String>(
                BudgetPage.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.categories));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner1.setAdapter(arrayAdapterCategories);
        spinners.add(Spinner1);

        Spinner2.setAdapter(arrayAdapterCategories);
        spinners.add(Spinner2);

        IncomeSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String BudgetString = MainBudget.getText().toString();

                switch(current_frequency){
                    case Weekly:
                        for(EditText et : editTexts){
                            String etString = et.getText().toString();
                            if(etString.isEmpty())
                            {
                                continue;
                            }

                            float amount =  Float.valueOf(etString);
                            amount = spinnersHelper.ConvertBudgetWeekly(i, amount);
                            et.setText(String.format("%.2f",amount));
                        }

                        if(!BudgetString.isEmpty()){
                            float value =  Float.valueOf(BudgetString);
                            value = spinnersHelper.ConvertBudgetWeekly(i, value);
                            MainBudget.setText(String.format("%.2f",value));
                        }
                        break;

                    case Monthly:
                        for(EditText et : editTexts){
                            String etString = et.getText().toString();
                            if(etString.isEmpty())
                            {
                                continue;
                            }

                            float amount =  Float.valueOf(etString);
                            amount = spinnersHelper.ConvertBudgetMonthly(i, amount);
                            et.setText(String.format("%.2f",amount));
                        }
                        if(!BudgetString.isEmpty()){
                            float value =  Float.valueOf(BudgetString);
                            value = spinnersHelper.ConvertBudgetMonthly(i, value);
                            MainBudget.setText(String.format("%.2f", value));
                        }
                        break;

                    case Yearly:
                        for(EditText et : editTexts){
                            String etString = et.getText().toString();
                            if(etString.isEmpty())
                            {
                                continue;
                            }

                            float amount =  Float.valueOf(etString);
                            amount = spinnersHelper.ConvertBudgetYearly(i, amount);
                            et.setText(String.format("%.2f",amount));
                        }
                        if(!BudgetString.isEmpty()){
                            float value =  Float.valueOf(BudgetString);
                            value = spinnersHelper.ConvertBudgetYearly(i, value);
                            MainBudget.setText(String.format("%.2f", value));
                        }

                        break;

                    case Bi_Weekly:
                        for(EditText et : editTexts){
                            String etString = et.getText().toString();
                            if(etString.isEmpty())
                            {
                                continue;
                            }

                            float amount =  Float.valueOf(etString);
                            amount = spinnersHelper.ConvertBudgetBiWeekly(i, amount);
                            et.setText(String.format("%.2f",amount));
                        }

                        if(!BudgetString.isEmpty()){
                            float value =  Float.valueOf(BudgetString);
                            value = spinnersHelper.ConvertBudgetBiWeekly(i, value);
                            MainBudget.setText(String.format("%.2f",value));
                        }
                        break;

                    case Bi_Monthly:
                        for(EditText et : editTexts){
                            String etString = et.getText().toString();
                            if(etString.isEmpty())
                            {
                                continue;
                            }

                            float amount =  Float.valueOf(etString);
                            amount = spinnersHelper.ConvertBudgetBiMonthly(i, amount);
                            et.setText(String.format("%.2f",amount));
                        }
                        if(!BudgetString.isEmpty()){
                            float value =  Float.valueOf(BudgetString);
                            value = spinnersHelper.ConvertBudgetBiMonthly(i, value);
                            MainBudget.setText(String.format("%.2f", value));
                        }
                        break;
                    default:
                        break;
                }
                current_frequency = Frequency.values()[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        calculate.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view){
                float sum =0;
                bills.clear();

                for(int i = 0; i < editTexts.size();i++){

                    EditText et = editTexts.get(i);
                    String etString = et.getText().toString();
                    if(!etString.isEmpty())
                    {
                        float amount = Float.valueOf(etString);
                        sum += amount;
                        Spinner spinner = spinners.get(i);
                        int spinnerPos = spinner.getSelectedItemPosition();
                        Category catergories = Category.values()[spinnerPos];
                        Bill _bill = new Bill();
                        _bill.amount = amount;
                        _bill.category = catergories;
                        bills.add(_bill);
                    }
                }

                HelperFunctions helper = new HelperFunctions();
                categoryValues = helper.mapBillCategories(bills);

                String total = "You have $";
                String budget = MainBudget.getText().toString();
                String savings = null;
                if(!TextUtils.isEmpty(budget)){
                    savings = String.valueOf(Float.valueOf(budget) - sum);
                }

                TextView result = findViewById(R.id.tvResult);
                TextView TotalBud =findViewById(R.id.totaltv);
                TextView Projected =findViewById(R.id.ProjectedSaving);

                if(budget.isEmpty()){
                    Toast.makeText(BudgetPage.this, "Enter a budget amount.",Toast.LENGTH_SHORT).show();
                }
                else if(!budget.isEmpty() && sum >Float.valueOf(budget)){
                    result.setText(total + String.format("%.2f",Float.valueOf(budget) - sum) + " remaining after bills!");
                    result.setTextColor(Color.RED);
                    TotalBud.setText("Total Budget\n$"+String.format("%.2f",sum));
                    TotalBud.setBackgroundDrawable(getResources().getDrawable(R.drawable.box));
                    Projected.setText("Projected Savings\n$"+String.format("%.2f",Float.valueOf(budget) - sum));
                    Projected.setBackgroundDrawable(getResources().getDrawable(R.drawable.box));
                }
                else{
                    result.setText(total + String.format("%.2f",Float.valueOf(budget) - sum) + " remaining after bills!");
                    result.setTextColor(Color.rgb(0,128,0));
                    TotalBud.setText("Total Budget\n$"+String.format("%.2f",sum));
                    TotalBud.setBackgroundDrawable(getResources().getDrawable(R.drawable.box));
                    Projected.setText("Projected Savings\n$"+String.format("%.2f",Float.valueOf(budget) - sum));
                    Projected.setBackgroundDrawable(getResources().getDrawable(R.drawable.box));
                }

                //chart
                setPieChart(pie, categoryValues, Float.valueOf(savings));
            }

        });

        BottomNavigationView navigation = findViewById(R.id.navigationView);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        Intent i = new Intent(BudgetPage.this, MainActivity.class);
                        startActivity(i);
                        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                        break;
                    case R.id.account:
                        Intent a = new Intent(BudgetPage.this, AccountPage.class);
                        startActivity(a);
                        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                        break;
                    case R.id.budget:
                        break;
                    case R.id.history:
                        Intent c = new Intent(BudgetPage.this, History.class);
                        startActivity(c);
                        break;
                    case R.id.paybills:
                        Intent d = new Intent(BudgetPage.this, Paybills.class);
                        startActivity(d);
                        break;
                }
                return false;
            }
        });

         add.setOnClickListener(new View.OnClickListener() {
             @RequiresApi(api = Build.VERSION_CODES.O)
             @Override
             public void onClick(View view) {

                 //linear layout creation
                 final LinearLayout layout = createLinearLayout();
                 layout.setOrientation(LinearLayout.HORIZONTAL);

                 //bill category spinner creation
                 final Spinner category = createdSpinner();
                 category.setAdapter(arrayAdapterCategories);
                 category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                     @Override
                     public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                         ((TextView) view).setSingleLine(true);
                         ((TextView) view).setEllipsize(TextUtils.TruncateAt.END);
                     }

                     @Override
                     public void onNothingSelected(AdapterView<?> adapterView) {

                     }
                 });

                 //bill amount edittext creation
                 final EditText value = createEditText();
                 value.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                 value.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(100,2)});

                 //create the delete textview
                 TextView delete = createDeleteTv();

                 dynamicLayout =findViewById(R.id.linearLayoutDynamic);
                 layout.addView(category);
                 layout.addView(value);

                 layout.addView(delete);
                 dynamicLayout.addView(layout);
                 editTexts.add(value);

                 //Types.add(name);
                 spinners.add(category);

                 delete.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         dynamicLayout.removeView(layout);
                         editTexts.remove(value);
                         spinners.remove(category);
                     }
                 });
             }
         });
    }

    public void setPieChart(final Pie pie, float[] categoryValues, float savings){
        final List<DataEntry> dataEntries = new ArrayList<>();

        float rent = categoryValues[0];
        dataEntries.add(new ValueDataEntry("Rent", rent));

        float food = categoryValues[1];
        dataEntries.add(new ValueDataEntry("Food", food));

        float services = categoryValues[2];
        dataEntries.add(new ValueDataEntry("Services", services));

        float entertainment = categoryValues[3];
        dataEntries.add(new ValueDataEntry("Entertainment", entertainment));

        float clothes = categoryValues[4];
        dataEntries.add(new ValueDataEntry("Clothes", clothes));

        float others = categoryValues[5];
        dataEntries.add(new ValueDataEntry("Other", others));

        dataEntries.add(new ValueDataEntry("Savings", savings));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pie.data(dataEntries);

            }
        }, 100);
    }

    //creation methods
    public EditText createEditText(){
        EditText name = new EditText(BudgetPage.this);
        name.setLayoutParams(new LinearLayout.LayoutParams(0,ViewGroup.LayoutParams.WRAP_CONTENT,.4f));
        return name;
    }

    public LinearLayout createLinearLayout(){
        LinearLayout layout = new LinearLayout(BudgetPage.this);
        layout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return layout;
    }

    public Spinner createdSpinner(){
        Spinner spin = new  Spinner(BudgetPage.this);
        spin.setLayoutParams(new LinearLayout.LayoutParams(0,150,.55f));
        return spin;
    }

    public TextView createDeleteTv(){
        TextView delete = new TextView(BudgetPage.this);
        delete.setLayoutParams(new LinearLayout.LayoutParams(0,ViewGroup.LayoutParams.WRAP_CONTENT, .1f));
        delete.setText("X");
        delete.setTextSize(24.0f);
        return delete;
    }

    //setting the top toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top, menu);
        return true;
    }

    //topbar menu selection
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

    //sets input to 2 decimals places
    public class DecimalDigitsInputFilter implements InputFilter {
        Pattern mPattern;

        public DecimalDigitsInputFilter(int digitsBeforeZero,int digitsAfterZero) {
            mPattern= Pattern.compile("[0-9]{0," + (digitsBeforeZero-1) + "}+((\\.[0-9]{0," + (digitsAfterZero-1) + "})?)||(\\.)?");
        }

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            Matcher matcher=mPattern.matcher(dest);
            if(!matcher.matches())
                return "";
            return null;
        }
    }
}
