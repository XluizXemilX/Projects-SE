package com.example.bottomnamviagtionbar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    EditText Email, Password;
    TextView register, recoverPass;
    Button Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Email = (EditText)findViewById((R.id.etEmail));
        Password = (EditText)findViewById((R.id.etPassword));
        register = (TextView)findViewById(R.id.tvregister);
        recoverPass = (TextView)findViewById(R.id.tvRecoverP);
        Login = (Button)findViewById(R.id.btnlogin);

        Login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(Login.this, MainActivity.class);
                startActivity(i);
            }
        });

        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(Login.this, Register.class);
                startActivity(i);
            }
        });

        recoverPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this, RecoveryPass.class);
                startActivity(i);
            }
        });
    }
}
