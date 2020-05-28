package com.Save.App.Settings.ProfileFolder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.Save.App.Helpers.HelperFunctions;
import com.Save.App.Helpers.SharedPrefsUtil;
import com.Save.App.Interfaces.User;
import com.Save.App.R;
import com.Save.App.RergistrationAndLogin.Login;

import java.util.regex.Pattern;

public class ProfileInfo extends AppCompatActivity {

    SharedPrefsUtil sharedPrefsUtil;
    HelperFunctions helperFunctions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_info);

        final EditText name =(EditText)findViewById(R.id.etNameProfile);
        final EditText etEmail =(EditText)findViewById(R.id.etEmailProfile);
        TextView changePass =(TextView)findViewById(R.id.ChangePasswordProfile);
        TextView delete = (TextView)findViewById(R.id.DeleteAccount);
        TextView Exit =(TextView)findViewById(R.id.Exit);

        sharedPrefsUtil = new SharedPrefsUtil(this);
        helperFunctions = new HelperFunctions();
        final String email = sharedPrefsUtil.get("email_income", "");
        final User user = sharedPrefsUtil.get(email,User.class, new User());
        name.setText(user.getName());
        etEmail.setText(user.getEmail());

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
                sharedPrefsUtil.deleteSavedData(email);
                Intent i = new Intent(ProfileInfo.this, Login.class);
                startActivity(i);
            }
        });


        Exit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String nameval = name.getText().toString();
                String emailVal = etEmail.getText().toString();
                if(!helperFunctions.ValidateFields(etEmail, name)){
                    Toast.makeText(ProfileInfo.this,"Make sure that all fields are completed",Toast.LENGTH_SHORT).show();
                }
                else if(ValidateEmail(emailVal)){

                    user.setName(nameval);
                    user.setEmail(emailVal);
                    sharedPrefsUtil.put(emailVal,User.class, user);
                    sharedPrefsUtil.put("user_email", emailVal);
                    Toast.makeText(ProfileInfo.this, "Changes made!", Toast.LENGTH_SHORT).show();
                }
                finish();

            }
        });

    }

    private boolean ValidateEmail(String email){
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
}
