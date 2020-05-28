package com.Save.App.RergistrationAndLogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.Save.App.Helpers.GMailSender;
import com.Save.App.Helpers.SharedPrefsUtil;
import com.Save.App.R;

public class RecoveryMessage extends AppCompatActivity {

    TextView resent, BackLogin, message;
    SharedPrefsUtil sharedPrefsUtil;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recovery_message);

        message = (TextView)findViewById((R.id.tvmessage));
        resent =(TextView)findViewById(R.id.tvResent);
        BackLogin =(TextView)findViewById(R.id.tvReturnLogin);
        sharedPrefsUtil = new SharedPrefsUtil(this);


       resent.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v){
               String EmailVal = sharedPrefsUtil.get("email_income", "");
               String tempPass = sharedPrefsUtil.get("tempPassword", "");
               GMailSender sender = new GMailSender("savemoneyapp79@gmail.com", "pegasus2001");
               try {
                   sender.sendMail("Recovery Password", "This is your temporary password " + tempPass +
                           ". Please remember to change it after login into the app.", "luisemil12344@gmail.com", EmailVal);
               } catch (Exception e) {
                   e.printStackTrace();
               }



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
