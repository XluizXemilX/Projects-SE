package com.example.bottomnamviagtionbar.Settings;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bottomnamviagtionbar.BudgetVar;
import com.example.bottomnamviagtionbar.Helpers.HelperFunctions;
import com.example.bottomnamviagtionbar.Helpers.SharedPrefsUtil;
import com.example.bottomnamviagtionbar.Interfaces.User;
import com.example.bottomnamviagtionbar.MainPages.AccountPage;
import com.example.bottomnamviagtionbar.MainPages.MainActivity;
import com.example.bottomnamviagtionbar.MainPages.SetupBudget;
import com.example.bottomnamviagtionbar.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModifyIncome extends AppCompatActivity {
    SharedPrefsUtil sharedPrefsUtil;
    HelperFunctions helperFunctions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_income);

        Button Save = (Button)findViewById(R.id.saveIncomeChange);
        final EditText etIncome = (EditText)findViewById(R.id.changeIncome);
        etIncome.setFilters(new InputFilter[] {new com.example.bottomnamviagtionbar.Helpers.DecimalDigitsInputFilter(100,2)});

        sharedPrefsUtil = new SharedPrefsUtil(this);
        helperFunctions = new HelperFunctions();

        Save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(!helperFunctions.ValidateFields(etIncome)){
                    etIncome.setText("0.00");
                }

                float income = Float.valueOf(etIncome.getText().toString());

                if(income <= 0.0f){
                    Toast.makeText(ModifyIncome.this, "Enter your income!", Toast.LENGTH_SHORT).show();
                }
                else{

                    String email = sharedPrefsUtil.get("email_income", "");
                    User user = sharedPrefsUtil.get(email, User.class, new User());
                    user.setIncome(income);
                    sharedPrefsUtil.put(email, User.class, user);
                    Intent intent = new Intent(ModifyIncome.this, AccountPage.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                }
            }
        });
    }
}



