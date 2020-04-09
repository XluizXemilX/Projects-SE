package com.example.bottomnamviagtionbar.Home.Budget;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.bottomnamviagtionbar.Helpers.DynamicViews;
import com.example.bottomnamviagtionbar.Home.MainActivity;
import com.example.bottomnamviagtionbar.Home.Paybills;
import com.example.bottomnamviagtionbar.R;
import com.example.bottomnamviagtionbar.RergistrationAndLogin.Login;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class BudgetPage extends AppCompatActivity {

    //private ActionBar toolbar;
    TextView  result;
    Toolbar topbar;
    EditText Type, Amount,Type2, Amount2, MainBudget;
    Button add, calculate;
    LinearLayout relativeLayout;
    Spinner Spinner1, Spinner2, BudgetSpin;
    ArrayList<EditText> editTexts = new ArrayList<EditText>();
    ArrayList<EditText> Types = new ArrayList<EditText>();





    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_page);

        //toolbar = getSupportActionBar();

        topbar = findViewById(R.id.topbar);
        setSupportActionBar(topbar);
        add = (Button)findViewById(R.id.addbtn);
        calculate=(Button)findViewById(R.id.calculate);
        result = (TextView)findViewById(R.id.result);
        Type = (EditText)findViewById(R.id.etType);
        Amount = (EditText)findViewById(R.id.etAmount);
        Type2 = (EditText)findViewById(R.id.etType2);
        Amount2 = (EditText)findViewById(R.id.etAmount2);
        Spinner1 = (Spinner)findViewById(R.id.spinnerFrequency);
        Spinner2 = (Spinner)findViewById(R.id.spinnerFrequency2);
        BudgetSpin =(Spinner)findViewById(R.id.SpinnerMainBudget);
        MainBudget =(EditText)findViewById(R.id.etMainBudget);

        String typeVal = Type.getText().toString().trim();
        Types.add(Type);
        String AmountVal = Amount.getText().toString().trim();
        editTexts.add(Amount);
        String typeVal2 = Type2.getText().toString().trim();
        Types.add(Type2);
        String AmountVal2 = Amount2.getText().toString().trim();
        editTexts.add(Amount2);


        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(BudgetPage.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.spinner));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner1.setAdapter(arrayAdapter);
        Spinner2.setAdapter(arrayAdapter);
        BudgetSpin.setAdapter(arrayAdapter);



        calculate.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view){
                float sum =0;


                for(EditText et : editTexts){
                    sum += Float.valueOf(et.getText().toString());

                }
                TextView result = (TextView)findViewById(R.id.result);
                result.setText("Total: $"+ sum);

            }

        });



        BottomNavigationView navigation = findViewById(R.id.navigationView);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        topbar.setTitle("Home");

                        Intent i = new Intent(BudgetPage.this, MainActivity.class);
                        startActivity(i);
                        break;
                    case R.id.account:
                        topbar.setTitle("AccountPage");
                        Intent a = new Intent(BudgetPage.this, AccountPage.class);
                        startActivity(a);
                        break;
                    case R.id.budget:
                        topbar.setTitle("Budget");

                        Intent b = new Intent(BudgetPage.this, BudgetPage.class);
                        startActivity(b);
                        break;
                    case R.id.history:
                        topbar.setTitle("History");
                        Intent c = new Intent(BudgetPage.this, History.class);
                        startActivity(c);
                        break;
                    case R.id.paybills:
                        topbar.setTitle("PayBills");
                        Intent d = new Intent(BudgetPage.this, Paybills.class);
                        startActivity(d);
                        break;
                }
                return false;
            }
        });

         //topbar.setTitle("Budget");

         add.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 LinearLayout layout = new LinearLayout(BudgetPage.this);
                 layout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                 layout.setOrientation(LinearLayout.HORIZONTAL);

                 EditText name = new EditText(BudgetPage.this);
                 name.setLayoutParams(new LinearLayout.LayoutParams(0,ViewGroup.LayoutParams.WRAP_CONTENT,.30f));
                 name.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);

                 EditText value = new EditText(BudgetPage.this);
                 value.setLayoutParams(new LinearLayout.LayoutParams(0,ViewGroup.LayoutParams.WRAP_CONTENT, .30f));
                 value.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

                 Spinner spin = new Spinner(BudgetPage.this);
                 spin.setLayoutParams(new LinearLayout.LayoutParams(0,ViewGroup.LayoutParams.WRAP_CONTENT,.40f));
                 spin.setAdapter(arrayAdapter);
                 relativeLayout =(LinearLayout) findViewById(R.id.linearlayoutBudget);
                 layout.addView(name);
                 layout.addView(value);
                 relativeLayout.addView(layout);
                 editTexts.add(value);
                 Types.add(name);

             }
         });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top, menu);
        return true;


    }
}
