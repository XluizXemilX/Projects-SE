package com.example.bottomnamviagtionbar.MainPages;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bottomnamviagtionbar.Helpers.PageAdapter;
import com.example.bottomnamviagtionbar.Helpers.SharedPrefsUtil;
import com.example.bottomnamviagtionbar.Interfaces.BarGraph;
import com.example.bottomnamviagtionbar.Interfaces.PointGraph;
import com.example.bottomnamviagtionbar.Interfaces.User;
import com.example.bottomnamviagtionbar.MainPages.Budget.BudgetPage;
import com.example.bottomnamviagtionbar.R;
import com.example.bottomnamviagtionbar.RergistrationAndLogin.Login;
import com.example.bottomnamviagtionbar.Settings.ModifyIncome;
import com.example.bottomnamviagtionbar.Settings.NotificationPage;
import com.example.bottomnamviagtionbar.Settings.settings;

public class AccountPage extends AppCompatActivity {

    TextView IncomeValue, BillValue, SavingsValue;
    SharedPrefsUtil sharedPrefsUtil;

    ViewPager mPager;
    PageAdapter adapter;
    LinearLayout Dots_Layout;
    ImageView[] dots;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        mPager = findViewById(R.id.viewPager);
        adapter = new PageAdapter(getSupportFragmentManager());
        adapter.addFragment(new BarGraph(), "Bar");
        adapter.addFragment(new PointGraph(), "Point");
        mPager .setAdapter(adapter);
        Dots_Layout= findViewById(R.id.dots);
        createDots(0);
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {

                createDots(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });


        sharedPrefsUtil = new SharedPrefsUtil(this);
        String email = sharedPrefsUtil.get("user_email", "");
        User user = sharedPrefsUtil.get(email, User.class, new User());
        String incomeVal= String.valueOf(user.getIncome());
        String billVal = String.valueOf(100);
        String savingsVal = String.valueOf(user.getIncome() - 100);
        BillValue = findViewById(R.id.amount_Bills);
        BillValue.setText("$" + billVal);
        SavingsValue = findViewById(R.id.amount_Savings);
        SavingsValue.setText("$" + savingsVal);
        IncomeValue = findViewById(R.id.amount_Income);
        IncomeValue.setText("$" + incomeVal);
        IncomeValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AccountPage.this, ModifyIncome.class);
                startActivity(i);
            }
        });

        BillValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AccountPage.this, Paybills.class);
                startActivity(i);
            }
        });




        Toolbar topbar = findViewById(R.id.topbar);
        topbar.setTitle("Accounts");
        topbar.setTitleTextColor(0xFFFFFFFF);
        setSupportActionBar(topbar);
        topbar.setNavigationIcon(R.drawable.ic_notifications_black_24dp);
        topbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AccountPage.this, NotificationPage.class);
                startActivity(i);
            }
        });

        BottomNavigationView navigation = findViewById(R.id.navigationView);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:

                        Intent i = new Intent(AccountPage.this, MainActivity.class);
                        startActivity(i);
                        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                        break;
                    case R.id.account:

                        break;
                    case R.id.budget:

                        Intent b = new Intent(AccountPage.this, BudgetPage.class);
                        startActivity(b);
                        break;
                    case R.id.history:

                        Intent c = new Intent(AccountPage.this, History.class);
                        startActivity(c);
                        break;
                    case R.id.paybills:

                        Intent d = new Intent(AccountPage.this, Paybills.class);
                        startActivity(d);
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
                Intent a = new Intent(this, Login.class);
                startActivity(a);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

   private void createDots(int current_position){
       if(Dots_Layout !=null){
           Dots_Layout.removeAllViews();
       }

       dots = new ImageView[adapter.getCount()];
       for(int i = 0; i<adapter.getCount(); i++){
           dots[i] =new ImageView(this);
           if(i==current_position){
               dots[i].setImageDrawable(ContextCompat.getDrawable(this,R.drawable.active_dots));
           }
           else {
               dots[i].setImageDrawable(ContextCompat.getDrawable(this,R.drawable.unactive_dots));
           }

           LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                   ViewGroup.LayoutParams.WRAP_CONTENT);
           params.setMargins(4,0,4,0);
           Dots_Layout.addView(dots[i],params);
       }
   }
}
