package com.example.bottomnamviagtionbar.Home;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.bottomnamviagtionbar.Home.Budget.AccountPage;
import com.example.bottomnamviagtionbar.Home.Budget.BudgetPage;
import com.example.bottomnamviagtionbar.Home.Budget.History;
import com.example.bottomnamviagtionbar.R;
import com.example.bottomnamviagtionbar.RergistrationAndLogin.Login;
import com.example.bottomnamviagtionbar.Settings.preferances;
import com.example.bottomnamviagtionbar.Settings.privacy;


public class MainActivity extends AppCompatActivity {

    private Button createBudgetButton;
    private ActionBar toolbar;



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //toolbar = getSupportActionBar();

        Toolbar topbar = findViewById(R.id.topbar);
        setSupportActionBar(topbar);
        //topbar.setTitle("Home");



        BottomNavigationView navigation = findViewById(R.id.navigationView);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        //toolbar.setTitle("Home");
                        //loadFragment(new HomeFragment());
                        //return true;
                        Intent i = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(i);
                        break;
                    case R.id.account:
                        //toolbar.setTitle("AccountPage");
                        //loadFragment(new AccountFragment());
                        //return true;
                        Intent a = new Intent(MainActivity.this, AccountPage.class);
                        startActivity(a);
                        break;
                    case R.id.budget:
                        //toolbar.setTitle("Budget");
                        //loadFragment(new BudgetFragment());
                        //return true;
                        Intent b = new Intent(MainActivity.this, BudgetPage.class);
                        startActivity(b);
                        break;
                    case R.id.history:
                        //toolbar.setTitle("History");
                        //loadFragment(new HistoryFragment());
                        //return true;
                        Intent c = new Intent(MainActivity.this, History.class);
                        startActivity(c);
                        break;
                    case R.id.paybills:
                        //toolbar.setTitle("PayBills");
                        //loadFragment(new PaybillsFragment());
                        //return true;
                        Intent d = new Intent(MainActivity.this, Paybills.class);
                        startActivity(d);
                        break;
                }
                return false;
            }
        });

       // toolbar.setTitle("Home");
       // loadFragment(new HomeFragment());




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
            case R.id.subitem2:
                Intent i = new Intent(this, preferances.class);
                startActivity(i);
                break;
            case R.id.subitem3:
                Intent a = new Intent(this, privacy.class);
                startActivity(a);
            case R.id.subitem4:
                Intent b = new Intent(this, Login.class);
                startActivity(b);
        }
        return super.onOptionsItemSelected(item);
    }




}
