package com.example.bottomnamviagtionbar.RergistrationAndLogin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bottomnamviagtionbar.R;

public class ChangePassword extends AppCompatActivity {

    EditText newPassword, confirmPassword;
    Button Save;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        newPassword =(EditText)findViewById(R.id.etNewPassword);
        confirmPassword =(EditText)findViewById(R.id.etConfirmNPassword);
        Save =(Button)findViewById(R.id.btnSave);
        preferences = getSharedPreferences("UserInfo", 0);

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newPassVal = newPassword.getText().toString().trim();
                String confirmVal = confirmPassword.getText().toString().trim();

                if((newPassVal.length()>5) && newPassVal.equals(confirmVal)){
                    SharedPreferences.Editor editor= preferences.edit();
                    editor.putString("etPass", newPassVal);
                    editor.apply();
                    Intent i = new Intent(ChangePassword.this, Login.class);
                    startActivity(i);

                }
                else{
                    Toast.makeText(ChangePassword.this, "Passwords do not match or too short.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
