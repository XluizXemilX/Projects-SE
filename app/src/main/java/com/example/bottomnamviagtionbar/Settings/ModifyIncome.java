package com.example.bottomnamviagtionbar.Settings;

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
import com.example.bottomnamviagtionbar.MainPages.AccountPage;
import com.example.bottomnamviagtionbar.MainPages.MainActivity;
import com.example.bottomnamviagtionbar.MainPages.SetupBudget;
import com.example.bottomnamviagtionbar.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModifyIncome extends AppCompatActivity {
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_income);

        Button Save = (Button)findViewById(R.id.saveIncomeChange);
        final EditText income = (EditText)findViewById(R.id.changeIncome);
        income.setFilters(new InputFilter[] {new com.example.bottomnamviagtionbar.Helpers.DecimalDigitsInputFilter(100,2)});
        preferences = getSharedPreferences("UserInfo",0);

        Save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String value = income.getText().toString();
                if(value.isEmpty()){
                    Toast.makeText(ModifyIncome.this, "Enter an amount!", Toast.LENGTH_SHORT).show();
                }
                else{

                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("Income", value);
                    editor.apply();
                    Intent i = new Intent(ModifyIncome.this, AccountPage.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                }
            }
        });

    }

}



