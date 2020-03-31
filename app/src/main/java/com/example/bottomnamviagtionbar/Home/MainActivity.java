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
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bottomnamviagtionbar.R;
import com.example.bottomnamviagtionbar.RergistrationAndLogin.Login;



public class MainActivity extends AppCompatActivity {

    private Button createBudgetButton;
    private ActionBar toolbar;
    TextView logout;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = getSupportActionBar();
        logout = (TextView)findViewById(R.id.tvLogout);


        BottomNavigationView navigation = findViewById(R.id.navigationView);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        toolbar.setTitle("Home");
        loadFragment(new HomeFragment());
        logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(MainActivity.this, Login.class);
                startActivity(i);
            }
        });



    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home:
                    toolbar.setTitle("Home");
                    loadFragment(new HomeFragment());
                    return true;
                case R.id.account:
                    toolbar.setTitle("Account");
                    loadFragment(new AccountFragment());
                    return true;
                case R.id.budget:
                    toolbar.setTitle("Budget");
                    loadFragment(new BudgetFragment());
                    return true;
                case R.id.history:
                    toolbar.setTitle("History");
                    loadFragment(new HistoryFragment());
                    return true;
                case R.id.paybills:
                    toolbar.setTitle("PayBills");
                    loadFragment(new PaybillsFragment());
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
