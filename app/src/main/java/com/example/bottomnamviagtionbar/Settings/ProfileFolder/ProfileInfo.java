package com.example.bottomnamviagtionbar.Settings.ProfileFolder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.bottomnamviagtionbar.R;
import com.example.bottomnamviagtionbar.RergistrationAndLogin.Login;

public class ProfileInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_info);

        final EditText name =(EditText)findViewById(R.id.etNameProfile);
        final EditText email =(EditText)findViewById(R.id.etEmailProfile);
        final EditText phone =(EditText)findViewById(R.id.etPhoneProfile);
        TextView changePass =(TextView)findViewById(R.id.ChangePasswordProfile);
        TextView delete = (TextView)findViewById(R.id.DeleteAccount);
        TextView Exit =(TextView)findViewById(R.id.Exit);
        SharedPreferences preferences = getSharedPreferences("UserInfo",0);
        final SharedPreferences.Editor editor = preferences.edit();

        name.setText(preferences.getString("etName", ""));
        email.setText(preferences.getString("etUser",""));
        phone.setText(preferences.getString("Phone",""));

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
                editor.clear();
                editor.apply();
                Intent i = new Intent(ProfileInfo.this, Login.class);
                startActivity(i);
            }
        });

        Exit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String nameval = name.getText().toString();
                String emailVal = email.getText().toString();
                String phoneVal = phone.getText().toString();
                if(emailVal.isEmpty() || nameval.isEmpty()){
                    Toast.makeText(ProfileInfo.this,"Make sure that all fields are completed",Toast.LENGTH_SHORT).show();
                }
                else if(emailVal.length()>1){
                    editor.putString("etName", nameval);
                    editor.putString("etUser", emailVal);
                    editor.putString("Phone", phoneVal);
                    editor.apply();
                }
                finish();

            }
        });

    }
}
