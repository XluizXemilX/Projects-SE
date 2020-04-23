package com.example.bottomnamviagtionbar.MainPages;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bottomnamviagtionbar.BudgetVar;
import com.example.bottomnamviagtionbar.MainPages.Budget.BudgetPage;
import com.example.bottomnamviagtionbar.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SetupBudget extends AppCompatActivity {
    SharedPreferences preferences;

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_budget);

        Button Save = (Button)findViewById(R.id.TomainButton);
        final EditText income = (EditText)findViewById(R.id.Monthly_Limit);
        income.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(100,2)});
        preferences = getSharedPreferences("UserInfo",0);

        Save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String value = income.getText().toString();
                if(value.isEmpty()){
                    Toast.makeText(SetupBudget.this, "Enter an amount!", Toast.LENGTH_SHORT).show();
                }
                else{
                    ///////
                    BudgetVar BV = new BudgetVar();
                    String tmpp = income.getText().toString();
                    BV.MonthlyLimit = Float.valueOf(tmpp);
                    ///////
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("Income", value);
                    editor.apply();
                    Intent i = new Intent(SetupBudget.this, BudgetPage.class);
                    startActivity(i);
                }
            }
        });

    }

}

class DecimalDigitsInputFilter implements InputFilter {

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
