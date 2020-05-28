package com.Save.Save_App.RergistrationAndLogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.Save.Save_App.Helpers.HelperFunctions;
import com.Save.Save_App.Helpers.SharedPrefsUtil;
import com.Save.Save_App.Interfaces.User;
import com.Save.Save_App.MainPages.SetupBudget;
import com.Save.Save_App.R;

import java.util.regex.Pattern;

public class Register extends AppCompatActivity{

    EditText etName, etEmail, etPassword;
    Button bRegister;
    SharedPrefsUtil sharedPrefsUtil;
    User user;
    HelperFunctions helperFunctions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName =(EditText)findViewById(R.id.etName);
        etEmail =(EditText)findViewById(R.id.etUsername);
        etPassword =(EditText)findViewById((R.id.etPassLogin));
        bRegister =(Button)findViewById(R.id.btnRegister);


        user = new User();
        sharedPrefsUtil = new SharedPrefsUtil(this);
        helperFunctions = new HelperFunctions();

        bRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String email = etEmail.getText().toString();
                if(!helperFunctions.ValidateFields(etName, etEmail, etPassword)){
                    Toast.makeText(Register.this, "One or more fields are empty. Please complete the form.",Toast.LENGTH_SHORT).show();
                }
                else if(ValidateEmail(email)){
                    if(sharedPrefsUtil.contains(email)) {
                        Toast.makeText(Register.this, "An account with this email already exist!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                       user.setEmail(email);
                       user.setPassword(etPassword.getText().toString());
                       user.setName(etName.getText().toString());
                       user.setIncome(0);
                       user.setBills(null);
                       sharedPrefsUtil.put("email_income", email);
                       sharedPrefsUtil.put(email,User.class,user);
                       Toast.makeText(Register.this, "User Registered", Toast.LENGTH_SHORT).show();
                       Intent intent = new Intent(Register.this, SetupBudget.class);
                       startActivity(intent);
                    }
                }
                else{
                    Toast.makeText(Register.this, "Invalid email!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    // validate email
    private boolean ValidateEmail(String email){
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
}


