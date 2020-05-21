package com.example.bottomnamviagtionbar.Settings.ProfileFolder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.bottomnamviagtionbar.Helpers.HelperFunctions;
import com.example.bottomnamviagtionbar.Helpers.SharedPrefsUtil;
import com.example.bottomnamviagtionbar.Interfaces.User;
import com.example.bottomnamviagtionbar.R;
import com.example.bottomnamviagtionbar.RergistrationAndLogin.Login;
import com.example.bottomnamviagtionbar.RergistrationAndLogin.Register;

import java.util.regex.Pattern;

public class ProfileInfo extends AppCompatActivity {

    SharedPrefsUtil sharedPrefsUtil;
    HelperFunctions helperFunctions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_info);

        final EditText name =(EditText)findViewById(R.id.etNameProfile);
        final EditText etEmail =(EditText)findViewById(R.id.etEmailProfile);
        TextView changePass =(TextView)findViewById(R.id.ChangePasswordProfile);
        TextView delete = (TextView)findViewById(R.id.DeleteAccount);
        TextView Exit =(TextView)findViewById(R.id.Exit);

        sharedPrefsUtil = new SharedPrefsUtil(this);
        helperFunctions = new HelperFunctions();
        final String email = sharedPrefsUtil.get("user_email", "");

        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(ProfileInfo.this, ChangePasswordProfile.class);
                startActivity(i);

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPrefsUtil.deleteSavedData(email);
                Intent i = new Intent(ProfileInfo.this, Login.class);
                startActivity(i);
            }
        });


        Exit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String nameval = name.getText().toString();
                String emailVal = etEmail.getText().toString();
                if(!helperFunctions.ValidateFields(etEmail, name)){
                    Toast.makeText(ProfileInfo.this,"Make sure that all fields are completed",Toast.LENGTH_SHORT).show();
                }
                else if(ValidateEmail(emailVal)){
                    User user = sharedPrefsUtil.get(email,User.class, new User());
                    user.setName(nameval);
                    user.setEmail(emailVal);
                    sharedPrefsUtil.put(emailVal,User.class, user);
                    sharedPrefsUtil.put("user_email", emailVal);
                    Toast.makeText(ProfileInfo.this, "Changes made!", Toast.LENGTH_SHORT).show();
                }
                finish();

            }
        });

    }

    private boolean ValidateEmail(String email){
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
}
