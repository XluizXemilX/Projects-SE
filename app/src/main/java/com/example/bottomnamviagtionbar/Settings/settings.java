package com.example.bottomnamviagtionbar.Settings;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.bottomnamviagtionbar.R;
import com.example.bottomnamviagtionbar.Settings.ProfileFolder.ProfileInfo;

public class settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        TextView Profile = (TextView)findViewById(R.id.Profile);
        TextView Privacy = (TextView)findViewById(R.id.Privacy);
        TextView Preferances =(TextView)findViewById(R.id.Preferance);
        TextView TermsOfServices = (TextView)findViewById(R.id.Terms_of_Services);
        TextView Exit = (TextView)findViewById(R.id.ExitSettings);

        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(settings.this, ProfileInfo.class);
                startActivity(i);

            }
        });

        Privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(settings.this, privacy.class);
                startActivity(i);

            }
        });

        Preferances.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(settings.this, preferances.class);
                startActivity(i);

            }
        });

        TermsOfServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(settings.this, TermsOfService.class);
                startActivity(i);

            }
        });
    }
}
