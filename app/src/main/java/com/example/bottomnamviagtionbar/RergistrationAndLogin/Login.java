package com.example.bottomnamviagtionbar.RergistrationAndLogin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bottomnamviagtionbar.Helpers.HelperFunctions;
import com.example.bottomnamviagtionbar.Helpers.SharedPrefsUtil;
import com.example.bottomnamviagtionbar.Interfaces.User;
import com.example.bottomnamviagtionbar.MainPages.MainActivity;
import com.example.bottomnamviagtionbar.R;

import org.json.JSONException;
import org.json.JSONObject;


public class Login extends AppCompatActivity{

   EditText etEmail, etPassword;
   TextView recoveryPass, Register;
   Button ButtonLogin;
   SharedPrefsUtil sharedPrefsUtil;
   CheckBox remember;
   HelperFunctions helperFunc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

       etEmail = (EditText)findViewById(R.id.etUsername);
       etPassword = (EditText)findViewById(R.id.etPassLogin);
       Register = (TextView)findViewById(R.id.tvregister);
       recoveryPass = (TextView)findViewById(R.id.tvRecoverP);
       ButtonLogin = (Button)findViewById(R.id.btnlogin);
       remember =(CheckBox)findViewById(R.id.checkBoxRemember);

       sharedPrefsUtil = new SharedPrefsUtil(this);
       helperFunc = new HelperFunctions();
       setRememberVariables();

       ButtonLogin.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v)
           {
               String email = etEmail.getText().toString();
               String password = etPassword.getText().toString();
               String temp = sharedPrefsUtil.get("tempPassword", "");

               if(helperFunc.ValidateFields(etEmail, etPassword)){
                   if(sharedPrefsUtil.contains(email)){
                       User user = sharedPrefsUtil.get(email, User.class, new User());
                       if(helperFunc.StringEquality(password, user.getPassword())){
                           sharedPrefsUtil.put("user_email", email);
                           sharedPrefsUtil.put("user_checked", remember.isChecked());
                           sharedPrefsUtil.put("user_password",
                                   remember.isChecked() ? password : "");

                           Intent intent = new Intent(Login.this, MainActivity.class);
                           startActivity(intent);
                       }
                       else if(helperFunc.StringEquality(password, temp)){
                           Intent intent = new Intent(Login.this, ChangePassword.class);
                           startActivity(intent);
                       }
                       else {
                           Toast.makeText(Login.this, "Email and Password do not match!", Toast.LENGTH_SHORT).show();
                       }
                   }
                   else
                   {
                       Toast.makeText(Login.this, "Email or Password not valid!", Toast.LENGTH_SHORT).show();
                   }
               }
               else
               {
                   Toast.makeText(Login.this, "One or more fields are empty. Please complete the form.",Toast.LENGTH_SHORT).show();
               }
           }
       });


      Register.setOnClickListener(new View.OnClickListener(){
          @Override
          public void onClick(View v){
              Intent i = new Intent(Login.this, com.example.bottomnamviagtionbar.RergistrationAndLogin.Register.class);
              startActivity(i);
          }
      });

      recoveryPass.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent i = new Intent(Login.this, RecoveryPass.class);
              startActivity(i);
          }
      });
    }

    private void setRememberVariables() {

        if(sharedPrefsUtil.contains("user_email")){
            String email = sharedPrefsUtil.get("user_email", "");
            etEmail.setText(email);
        }

        if(sharedPrefsUtil.contains("user_password")){
            String password = sharedPrefsUtil.get("user_password", "");
            etPassword.setText(password);
        }

        if(sharedPrefsUtil.contains("user_checked")){
            boolean checked = sharedPrefsUtil.get("user_checked", false);
            remember.setChecked(checked);
        }
    }

}
