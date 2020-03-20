package com.example.bottomnamviagtionbar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RecoveryPass extends AppCompatActivity {

    EditText Email;
    Button Send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recovery_pass);

        Email =(EditText)findViewById(R.id.etRecoveyP);
        Send = (Button)findViewById(R.id.send);

        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RecoveryPass.this, RecoveryMessage.class);
                startActivity(i);
            }
        });
    }
}
