package com.example.bottomnamviagtionbar.RergistrationAndLogin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.bottomnamviagtionbar.Helpers.GMailSender;
import com.example.bottomnamviagtionbar.R;

public class RecoveryMessage extends AppCompatActivity {

    TextView resent, BackLogin, message;
    SharedPreferences preferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recovery_message);

        message = (TextView)findViewById((R.id.tvmessage));
        resent =(TextView)findViewById(R.id.tvResent);
        BackLogin =(TextView)findViewById(R.id.tvReturnLogin);
        preferences = getSharedPreferences("UserInfo", 0);

       resent.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v){
               String EmailVal = preferences.getString("recoverEmail", "");
               String tempPass = preferences.getString("etPass", "");
               GMailSender sender = new GMailSender("luisemil12344@gmail.com", "pegasus123");
               try {
                   sender.sendMail("Recovery Password", "This is your temporary password " + tempPass + ". Please remember to change it after login into the app.", "luisemil12344@gmail.com", EmailVal);
               } catch (Exception e) {
                   e.printStackTrace();
               }
               SharedPreferences.Editor editor = preferences.edit();


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
