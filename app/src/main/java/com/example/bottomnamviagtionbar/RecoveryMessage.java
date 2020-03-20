package com.example.bottomnamviagtionbar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RecoveryMessage extends AppCompatActivity {

    TextView resent, BackLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recovery_message);

        resent =(TextView)findViewById(R.id.tvResent);
        BackLogin =(TextView)findViewById(R.id.tvReturnLogin);

        resent.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });

        BackLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(RecoveryMessage.this, Login.class);
                startActivity(i);
            }
        });
    }
}
