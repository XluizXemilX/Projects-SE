package com.example.bottomnamviagtionbar.MainPages.Budget;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
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

import com.example.bottomnamviagtionbar.Helpers.SpinnersHelper;
import com.example.bottomnamviagtionbar.MainPages.AccountPage;
import com.example.bottomnamviagtionbar.MainPages.History;
import com.example.bottomnamviagtionbar.MainPages.MainActivity;
import com.example.bottomnamviagtionbar.MainPages.Paybills;
import com.example.bottomnamviagtionbar.R;
import com.example.bottomnamviagtionbar.RergistrationAndLogin.Login;
import com.example.bottomnamviagtionbar.Settings.NotificationPage;
import com.example.bottomnamviagtionbar.Settings.settings;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BudgetPage extends AppCompatActivity {

    public enum Frequency {
        Monthly, // 0
        Weekly,
        Yearly
    }
    public enum Category{
        Rent,  //0
        Services,
        Food,
        Entertainment,
        Clothes,
        Other
    }

    TextView  result;
    Toolbar topbar;
    EditText Type, Amount,Type2, Amount2, MainBudget;
    Button add, calculate;
    LinearLayout dynamicLayout;
    Spinner Spinner1, Spinner2, IncomeSpin;
    ArrayList<EditText> editTexts = new ArrayList<>();
    //ArrayList<EditText> Types = new ArrayList<EditText>();
    ArrayList<Spinner> spinners = new ArrayList<>();
    // Categories
    ArrayList<EditText> Rent = new ArrayList<>();
    ArrayList<EditText> servies = new ArrayList<>();
    ArrayList<EditText> Food = new ArrayList<>();
    ArrayList<EditText> Entertainment = new ArrayList<>();
    ArrayList<EditText> Clothes = new ArrayList<>();
    ArrayList<EditText> Others = new ArrayList<>();
    SharedPreferences preferences;
    Frequency current_frequency;
    SpinnersHelper spinnersHelper;
    Category current_category;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_page);




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

        //default fields
        //Type = findViewById(R.id.etType);
        Amount = findViewById(R.id.etAmount);
        Amount.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(100,2)});
        //Type2 = findViewById(R.id.etType2);
        Amount2 = findViewById(R.id.etAmount2);
        Amount2.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(100,2)});
        Spinner1 = findViewById(R.id.spinner1);
        Spinner2 = findViewById(R.id.spinner2);

        //income fields
        IncomeSpin =findViewById(R.id.spinner);
        MainBudget =findViewById(R.id.editText2);
        MainBudget.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(100,2)});

        preferences = getSharedPreferences("UserInfo",0);
        MainBudget.setText(preferences.getString("Income", ""));

        editTexts.add(Amount);
        editTexts.add(Amount2);

        final SharedPreferences.Editor editor = preferences.edit();


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

                for(EditText et : editTexts){
                    String etString = et.getText().toString();
                    if(etString.isEmpty())
                    {
                        continue;
                    }

                    sum += Float.valueOf(etString);

                }

                TextView result = findViewById(R.id.tvResult);
                TextView TotalBud =findViewById(R.id.totaltv);
                TextView Projected =findViewById(R.id.ProjectedSaving);

                String total = "You have $";
                String budget = MainBudget.getText().toString();
                String savings = String.valueOf(Float.valueOf(budget) - sum);
                editor.putString("Saving", savings);
                String paid = String.valueOf(sum);
                editor.putString("Paid", paid);
                editor.apply();

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
                        //topbar.setTitle("History");
                        Intent c = new Intent(BudgetPage.this, History.class);
                        startActivity(c);
                        break;
                    case R.id.paybills:
                        //topbar.setTitle("PayBills");
                        Intent d = new Intent(BudgetPage.this, Paybills.class);
                        startActivity(d);
                        break;
                }
                return false;
            }
        });

         //

         add.setOnClickListener(new View.OnClickListener() {
             @RequiresApi(api = Build.VERSION_CODES.O)
             @Override
             public void onClick(View view) {

                 //linear layout creation
                 final LinearLayout layout = createLinearLayout();
                 layout.setOrientation(LinearLayout.HORIZONTAL);

                 //bill name edittext creation
                 final Spinner name = createdSpinner();
                 name.setAdapter(arrayAdapterCategories);

                 //final EditText name = createEditText();
                 //name.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);

                 //bill amount edittext creation
                 final EditText value = createEditText();
                 value.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                 value.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(100,2)});

                 //create the bill spinner
                 //Spinner spin = createdSpinner();
                 //spin.setAdapter(arrayAdapter);

                 //create the delete textview
                 TextView delete = createDeleteTv();

                 dynamicLayout =findViewById(R.id.linearLayoutDynamic);
                 layout.addView(name);
                 layout.addView(value);
                 //layout.addView(spin);

                 layout.addView(delete);
                 dynamicLayout.addView(layout);
                 editTexts.add(value);
                 //Types.add(name);
                 spinners.add(name);

                 delete.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         dynamicLayout.removeView(layout);
                         editTexts.remove(value);
                         spinners.remove(name);
                     }
                 });
             }
         });
    }


    //creation methods
    public EditText createEditText(){
        EditText name = new EditText(BudgetPage.this);
        name.setLayoutParams(new LinearLayout.LayoutParams(0,ViewGroup.LayoutParams.WRAP_CONTENT,1));
        return name;
    }
    public LinearLayout createLinearLayout(){
        LinearLayout layout = new LinearLayout(BudgetPage.this);
        layout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return layout;
    }
    public Spinner createdSpinner(){
        Spinner spin = new  Spinner(BudgetPage.this);
        spin.setLayoutParams(new LinearLayout.LayoutParams(0,ViewGroup.LayoutParams.WRAP_CONTENT,1));
        return spin;
    }
    public TextView createDeleteTv(){
        TextView delete = new TextView(BudgetPage.this);
        delete.setLayoutParams(new LinearLayout.LayoutParams(0,ViewGroup.LayoutParams.WRAP_CONTENT, .25f));
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

    //separates the bills categories
    public void  CategoriesSelection( Spinner spin, EditText value){
        switch(current_category){
            case Rent:
                Rent.add(value);
                break;
            case Services:
                servies.add(value);
                break;
            case Food:
                Food.add(value);
                break;
            case Entertainment:
                Entertainment.add(value);
                break;
            case Clothes:
                Clothes.add(value);
                break;
            case Other:
                Others.add(value);
                break;

        }

    }


}
