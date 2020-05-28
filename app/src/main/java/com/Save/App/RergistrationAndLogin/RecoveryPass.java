package com.Save.App.RergistrationAndLogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.Save.App.Helpers.GMailSender;
import com.Save.App.Helpers.SharedPrefsUtil;
import com.Save.App.Interfaces.User;
import com.Save.App.R;

import java.util.Random;

public class RecoveryPass extends AppCompatActivity {

    EditText etUser;
    Button Send;
    String tempPass;
    SharedPrefsUtil sharedPrefsUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recovery_pass);

        etUser =(EditText)findViewById(R.id.etRecoveyP);
        Send = (Button)findViewById(R.id.send);
        tempPass = getRandString();
        sharedPrefsUtil = new SharedPrefsUtil(this);

        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String EmailVal = etUser.getText().toString();
                if(!EmailVal.isEmpty()) {
                    GMailSender sender = new GMailSender("savemoneyapp79@gmail.com", "pegasus2001");
                    try {
                        sender.sendMail("Recovery Password", "This is your temporary password " + tempPass +
                                ". Please remember to change it after login into the app.",
                                "luisemil12344@gmail.com", EmailVal);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    String email = sharedPrefsUtil.get("email_income", "");
                    User user = sharedPrefsUtil.get(email, User.class, new User());
                    user.setPassword(tempPass);
                    sharedPrefsUtil.put(email, User.class, new User());
                    sharedPrefsUtil.put("tempPassword", tempPass);
                    Intent i = new Intent(RecoveryPass.this, RecoveryMessage.class);
                    startActivity(i);
                }else{
                    Toast.makeText(RecoveryPass.this, "Enter an Email!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    protected String getRandString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
}
