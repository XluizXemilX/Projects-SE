package com.example.bottomnamviagtionbar.Home.Budget;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.bottomnamviagtionbar.Helpers.DynamicViews;
import com.example.bottomnamviagtionbar.Home.MainActivity;
import com.example.bottomnamviagtionbar.Home.Paybills;
import com.example.bottomnamviagtionbar.R;
import com.example.bottomnamviagtionbar.RergistrationAndLogin.Login;

import org.w3c.dom.Text;

public class BudgetPage extends AppCompatActivity {

    //private ActionBar toolbar;
    TextView logout, result;
    Button add, calculate;
    LinearLayout relativeLayout;




    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_page);

        //toolbar = getSupportActionBar();
        logout = (TextView)findViewById(R.id.tvLogout);
        Toolbar topbar = findViewById(R.id.topbar);
        setSupportActionBar(topbar);
        add = (Button)findViewById(R.id.addbtn);
        calculate=(Button)findViewById(R.id.calculate);
        result = (TextView)findViewById(R.id.result);




        BottomNavigationView navigation = findViewById(R.id.navigationView);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        //toolbar.setTitle("Home");
                        //loadFragment(new HomeFragment());
                        //return true;
                        Intent i = new Intent(BudgetPage.this, MainActivity.class);
                        startActivity(i);
                        break;
                    case R.id.account:
                        //toolbar.setTitle("AccountPage");
                        //loadFragment(new AccountFragment());
                        //return true;
                        Intent a = new Intent(BudgetPage.this, AccountPage.class);
                        startActivity(a);
                        break;
                    case R.id.budget:
                        //toolbar.setTitle("Budget");
                        //loadFragment(new BudgetFragment());
                        //return true;
                        Intent b = new Intent(BudgetPage.this, BudgetPage.class);
                        startActivity(b);
                        break;
                    case R.id.history:
                        //toolbar.setTitle("History");
                        //loadFragment(new HistoryFragment());
                        //return true;
                        Intent c = new Intent(BudgetPage.this, History.class);
                        startActivity(c);
                        break;
                    case R.id.paybills:
                        //toolbar.setTitle("PayBills");
                        //loadFragment(new PaybillsFragment());
                        //return true;
                        Intent d = new Intent(BudgetPage.this, Paybills.class);
                        startActivity(d);
                        break;
                }
                return false;
            }
        });

         //toolbar.setTitle("Budget");

         add.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                    EditText editText = new EditText(BudgetPage.this);

                    relativeLayout =(LinearLayout) findViewById(R.id.linearlayout);
                    relativeLayout.addView(editText);






             }
         });



        logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(BudgetPage.this, Login.class);
                startActivity(i);
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
