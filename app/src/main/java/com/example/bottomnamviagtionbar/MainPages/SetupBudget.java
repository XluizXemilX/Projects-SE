package com.example.bottomnamviagtionbar.MainPages;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bottomnamviagtionbar.Helpers.DecimalDigitsInputFilter;
import com.example.bottomnamviagtionbar.Helpers.HelperFunctions;
import com.example.bottomnamviagtionbar.Helpers.SharedPrefsUtil;
import com.example.bottomnamviagtionbar.Interfaces.User;
import com.example.bottomnamviagtionbar.R;

import java.text.SimpleDateFormat;
import java.util.Date;


public class SetupBudget extends AppCompatActivity {
    SharedPrefsUtil sharedPrefsUtil;
    HelperFunctions helperFunc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_budget);

        Button Save = (Button)findViewById(R.id.TomainButton);
        final EditText etIncome = (EditText)findViewById(R.id.Monthly_Limit);
        etIncome.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(100,2)});
        sharedPrefsUtil = new SharedPrefsUtil(this);
        helperFunc = new HelperFunctions();

        Save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(!helperFunc.ValidateFields(etIncome)){
                    etIncome.setText("0.00");
                }

                float income = Float.valueOf(etIncome.getText().toString());

                if(income <= 0.0f){
                    Toast.makeText(SetupBudget.this, "Enter your income!", Toast.LENGTH_SHORT).show();
                }
                else{

                    String email = sharedPrefsUtil.get("email_income", "");
                    User user = sharedPrefsUtil.get(email, User.class, new User());
                    user.setIncome(income);
                    user.setOriginalIncome(income);
                    Date date = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("MM");
                    String DateATime = formatter.format(date);
                    user.setCurrentMonth(DateATime);
                    sharedPrefsUtil.put(email, User.class, user);
                    Intent intent = new Intent(SetupBudget.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}


