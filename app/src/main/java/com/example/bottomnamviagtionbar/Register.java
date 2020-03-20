package com.example.bottomnamviagtionbar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity {

    EditText Email, Name, Password;
    Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Email = (EditText)findViewById((R.id.etEmail));
        Password = (EditText)findViewById((R.id.etPassword));
        Name = (EditText) findViewById(R.id.etName);
        register = (Button)findViewById(R.id.btnRegister);

        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(Register.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}
