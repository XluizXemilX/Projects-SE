package com.example.bottomnamviagtionbar.MainPages.Budget;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
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
import com.example.bottomnamviagtionbar.Helpers.SpinnersHelper;
import com.example.bottomnamviagtionbar.MainPages.AccountPage;
import com.example.bottomnamviagtionbar.MainPages.History;
import com.example.bottomnamviagtionbar.MainPages.MainActivity;
import com.example.bottomnamviagtionbar.MainPages.Paybills;
import com.example.bottomnamviagtionbar.Settings.NotificationPage;
import com.example.bottomnamviagtionbar.R;
import com.example.bottomnamviagtionbar.RergistrationAndLogin.Login;
import com.example.bottomnamviagtionbar.Settings.settings;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class BudgetPage extends AppCompatActivity {


    TextView  result;
    Toolbar topbar;
    EditText Type, Amount,Type2, Amount2, MainBudget;
    Button add, calculate;
    LinearLayout dynamicLayout;
    Spinner Spinner1, Spinner2, BudgetSpin;
    ArrayList<EditText> editTexts = new ArrayList<EditText>();
    ArrayList<EditText> Types = new ArrayList<EditText>();
    ArrayList<Spinner> spinners = new ArrayList<Spinner>();
    SharedPreferences preferences;




    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_page);


        topbar = findViewById(R.id.topbar);
        topbar.setTitle("Budget");
        setSupportActionBar(topbar);
        topbar.setNavigationIcon(R.drawable.ic_notifications_black_24dp);
        topbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(BudgetPage.this, NotificationPage.class);
                startActivity(i);
            }
        });

        add = (Button)findViewById(R.id.btnAdd);
        calculate=(Button)findViewById(R.id.btnCalculate);
        result = (TextView)findViewById(R.id.tvResult);

        //default fields
        Type = (EditText)findViewById(R.id.etType);
        Amount = (EditText)findViewById(R.id.etAmount);
        Type2 = (EditText)findViewById(R.id.etType2);
        Amount2 = (EditText)findViewById(R.id.etAmount2);
        Spinner1 = (Spinner)findViewById(R.id.spinner1);
        Spinner2 = (Spinner)findViewById(R.id.spinner2);

        //income fields
        BudgetSpin =(Spinner)findViewById(R.id.spinner);
        MainBudget =(EditText)findViewById(R.id.editText2);

        preferences = getSharedPreferences("UserInfo",0);
        MainBudget.setText(preferences.getString("Income", ""));

        String typeVal = Type.getText().toString().trim();
        Types.add(Type);
        String AmountVal = Amount.getText().toString().trim();
        editTexts.add(Amount);

        String typeVal2 = Type2.getText().toString().trim();
        Types.add(Type2);
        String AmountVal2 = Amount2.getText().toString().trim();
        editTexts.add(Amount2);

        final SharedPreferences.Editor editor = preferences.edit();


        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(BudgetPage.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.spinner));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner1.setAdapter(arrayAdapter);
        spinners.add(Spinner1);
        Spinner2.setAdapter(arrayAdapter);
        spinners.add(Spinner2);
        BudgetSpin.setAdapter(arrayAdapter);

        BudgetSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int pos =0;
                switch(i){
                    case 0:
                        break;
                    case 1:
                        SpinnersHelper spin = new SpinnersHelper();

                        break;
                    case 2:

                        break;
                    case 3:
                        break;
                }
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
                    result.setText(total + String.valueOf(Float.valueOf(budget) - sum) + " remaining after bills!");
                    result.setTextColor(Color.RED);
                    TotalBud.setText("Total Budget\n$"+paid);
                    TotalBud.setBackgroundDrawable(getResources().getDrawable(R.drawable.box));
                    Projected.setText("Projected Savings\n$"+savings);
                    Projected.setBackgroundDrawable(getResources().getDrawable(R.drawable.box));
                }
                else{
                    result.setText(total + String.valueOf(Float.valueOf(budget) - sum) + " remaining after bills!");
                    result.setTextColor(Color.rgb(0,128,0));
                    TotalBud.setText("Total Budget\n$"+paid);
                    TotalBud.setBackgroundDrawable(getResources().getDrawable(R.drawable.box));
                    Projected.setText("Projected Savings\n$"+savings);
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
                 final EditText name = createEditText();
                 name.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);

                 //bill amount edittext creation
                 final EditText value = createEditText();
                 value.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

                 //create the bill spinner
                 Spinner spin = createdSpinner();
                 spin.setAdapter(arrayAdapter);

                 //create the delete textview
                 TextView delete = createDeleteTv();

                 dynamicLayout =findViewById(R.id.linearLayoutDynamic);
                 layout.addView(name);
                 layout.addView(value);
                 layout.addView(spin);

                 layout.addView(delete);
                 dynamicLayout.addView(layout);
                 editTexts.add(value);
                 Types.add(name);
                 spinners.add(spin);

                 delete.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         dynamicLayout.removeView(layout);
                         editTexts.remove(value);
                         Types.remove(name);
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




}
