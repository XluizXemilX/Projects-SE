package com.Save.App.Settings.ProfileFolder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.Save.App.Helpers.HelperFunctions;
import com.Save.App.Helpers.SharedPrefsUtil;
import com.Save.App.Interfaces.User;
import com.Save.App.R;
import com.Save.App.RergistrationAndLogin.Login;

public class ChangePasswordProfile extends AppCompatActivity {

    SharedPrefsUtil sharedPrefsUtil;
    HelperFunctions helperFunctions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password_profile);

        final EditText current = (EditText)findViewById(R.id.etCurrentPassword);
        final EditText newPassword = (EditText)findViewById(R.id.etNewPasswordProfile);
        final EditText confirm = (EditText)findViewById(R.id.etConfirmNPasswordProfile);
        Button save =(Button)findViewById(R.id.btnSaveProfile);
        TextView Exit = (TextView)findViewById(R.id.ExitChangePassword);

        sharedPrefsUtil = new SharedPrefsUtil(this);
        helperFunctions = new HelperFunctions();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currVal = current.getText().toString().trim();
                String newPassVal = newPassword.getText().toString().trim();
                String confirmVal = confirm.getText().toString().trim();
                String email = sharedPrefsUtil.get("email_income", "");

                if(helperFunctions.ValidateFields(current,newPassword,confirm)){
                    User user = sharedPrefsUtil.get(email, User.class, new User());
                    if(helperFunctions.StringEquality(currVal, user.getPassword())&& helperFunctions.StringEquality(newPassVal,confirmVal)){
                        user.setPassword(newPassVal);
                        sharedPrefsUtil.put(email, User.class, user);
                        sharedPrefsUtil.put("user_password", newPassVal);
                        Intent i = new Intent(ChangePasswordProfile.this, Login.class);
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(ChangePasswordProfile.this, "Passwords do not match or too short.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
