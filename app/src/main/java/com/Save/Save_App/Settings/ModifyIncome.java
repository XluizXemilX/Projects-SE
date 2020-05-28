package com.Save.Save_App.Settings;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.Save.Save_App.Helpers.HelperFunctions;
import com.Save.Save_App.Helpers.SharedPrefsUtil;
import com.Save.Save_App.Interfaces.User;
import com.Save.Save_App.MainPages.AccountPage;
import com.Save.Save_App.R;

public class ModifyIncome extends AppCompatActivity {
    SharedPrefsUtil sharedPrefsUtil;
    HelperFunctions helperFunctions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_income);

        Button Save = (Button)findViewById(R.id.saveIncomeChange);
        final EditText etIncome = (EditText)findViewById(R.id.changeIncome);
        etIncome.setFilters(new InputFilter[] {new com.Save.Save_App.Helpers.DecimalDigitsInputFilter(100,2)});

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



