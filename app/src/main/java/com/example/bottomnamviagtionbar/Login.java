package com.example.bottomnamviagtionbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bottomnamviagtionbar.Home.MainActivity;


public class Login extends AppCompatActivity{





   EditText etUser, etPass;
   TextView recoveryPass, Register;
   Button ButtonLogin;
   SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

       etUser = (EditText)findViewById((R.id.etUsername));
       etPass = (EditText)findViewById((R.id.etPassLogin));
       Register = (TextView)findViewById(R.id.tvregister);
       recoveryPass = (TextView)findViewById(R.id.tvRecoverP);
       ButtonLogin = (Button)findViewById(R.id.btnlogin);
       preferences = getSharedPreferences("UserInfo",0);

       ButtonLogin.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v)
           {
               String usernameVal = etUser.getText().toString();
               String passwordVal = etPass.getText().toString();
               String registeredUserEmail = preferences.getString("etUser", "");
               String registeredUserPass = preferences.getString("etPass", "");
               if(registeredUserEmail.isEmpty() || registeredUserPass.isEmpty())
               {
                   Toast.makeText(Login.this, "Email and Password do not match!", Toast.LENGTH_SHORT).show();
               }
               else if((usernameVal.toLowerCase().equals(registeredUserEmail.toLowerCase())&& passwordVal.equals(registeredUserPass))){
                   Intent i = new Intent(Login.this, MainActivity.class);
                   startActivity(i);
               }
               else{
                   Toast.makeText(Login.this, "Email and Password do not match!", Toast.LENGTH_SHORT).show();
               }
           }
       });

      Register.setOnClickListener(new View.OnClickListener(){
          @Override
          public void onClick(View v){
              Intent i = new Intent(Login.this, Register.class);
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

}
