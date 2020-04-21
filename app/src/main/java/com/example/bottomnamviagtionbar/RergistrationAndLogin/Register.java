package com.example.bottomnamviagtionbar.RergistrationAndLogin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bottomnamviagtionbar.MainPages.SetupBudget;
import com.example.bottomnamviagtionbar.R;

public class Register extends AppCompatActivity{



    private static final int WRITE_EXTERNAL_STORAGE_CODE = 1;
    EditText _etName, etUser, etPass;
    Button bRegister;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        _etName =(EditText)findViewById(R.id.etName);
        etUser =(EditText)findViewById(R.id.etUsername);
        etPass =(EditText)findViewById((R.id.etPassLogin));
        bRegister =(Button)findViewById(R.id.btnRegister);
        preferences = getSharedPreferences("UserInfo",0);

        bRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String name = _etName.getText().toString();
                String userVal = etUser.getText().toString();
                String passVal = etPass.getText().toString();
                String verification = preferences.getString("etUser","");
                if(userVal.equals(verification)){
                    Toast.makeText(Register.this, "An account with this email already exist!", Toast.LENGTH_SHORT).show();
                }
                else if((userVal.length()>1)&& passVal.length()>5) {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("etName", name);
                    editor.putString("etUser", userVal);
                    editor.putString("etPass", passVal);
                    editor.apply();
                    Toast.makeText(Register.this, "User Registered", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Register.this, SetupBudget.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(Register.this, "One or more fields are empty. Please complete the form.",Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

}
