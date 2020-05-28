package com.Save.Save_App.Settings;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.Save.Save_App.Helpers.HelperFunctions;
import com.Save.Save_App.Helpers.SharedPrefsUtil;
import com.Save.Save_App.Interfaces.User;
import com.Save.Save_App.MainPages.Budget.BudgetPage;
import com.Save.Save_App.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NotificationPage extends AppCompatActivity {

    SharedPrefsUtil sharedPrefsUtil;
    User user;
    HelperFunctions helperFunc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_page);

        helperFunc = new HelperFunctions();
        sharedPrefsUtil = new SharedPrefsUtil(this);
        String email = sharedPrefsUtil.get("email_income", "");
        user = sharedPrefsUtil.get(email, User.class, new User());

        if(user.getHistories() == null)
        {
            user.setHistories(new ArrayList<String>());
        }

        LinearLayout notificationsLayout = findViewById(R.id.notifications_layout);

        for(int i =0; i<user.getRecurrent_bills().size(); i++){
            Calendar date = Calendar.getInstance();
            String killMe = String.valueOf(date.get(Calendar.DAY_OF_MONTH));
            if(user.getRecurrent_bills().get(i).date.equals(killMe)){

                LinearLayout layout = new LinearLayout(this);
                layout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                layout.setOrientation(LinearLayout.HORIZONTAL);
                layout.setGravity(Gravity.CENTER);
                layout.setBackgroundResource(R.drawable.box);

                TextView type = new TextView(this);
                type.setLayoutParams(new LinearLayout.LayoutParams(0,ViewGroup.LayoutParams.WRAP_CONTENT, .1f));
                type.setText(String.valueOf(user.getRecurrent_bills().get(i).category));
                type.setTextSize(20.0f);
                type.setLines(1);
                type.setGravity(Gravity.CENTER);
                layout.addView(type);

                TextView amount = new TextView(this);
                amount.setLayoutParams(new LinearLayout.LayoutParams(0,ViewGroup.LayoutParams.WRAP_CONTENT, .1f));
                amount.setText(String.valueOf(user.getRecurrent_bills().get(i).amount));
                amount.setTextSize(20.0f);
                amount.setGravity(Gravity.CENTER);
                layout.addView(amount);

                TextView dateBill = new TextView(this);
                dateBill.setLayoutParams(new LinearLayout.LayoutParams(0,ViewGroup.LayoutParams.WRAP_CONTENT, .1f));
                dateBill.setText(String.valueOf(user.getRecurrent_bills().get(i).date));
                dateBill.setTextSize(20.0f);
                dateBill.setGravity(Gravity.CENTER);
                layout.addView(dateBill);

                notificationsLayout.addView(layout);

            }
            else {
                notificationsLayout.removeAllViews();
            }
        }

        TextView exit = findViewById(R.id.ExitNotification);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
