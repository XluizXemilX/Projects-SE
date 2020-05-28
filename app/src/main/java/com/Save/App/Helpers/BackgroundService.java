package com.Save.App.Helpers;

import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.Save.App.Interfaces.User;
import com.Save.App.R;

import java.util.Calendar;

public class BackgroundService extends android.app.Service {

    protected static final int NOTIFICATION_ID = 1337;

    public BackgroundService() {
        //super("BackgroundService");
        // TODO Auto-generated constructor stub
    }


    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        SharedPrefsUtil sharedPrefsUtil = new SharedPrefsUtil(this);
        String email = sharedPrefsUtil.get("email_income","");
        User user = sharedPrefsUtil.get(email, User.class, new User());


        if(user.getRecurrent_bills() != null) {
            for (int i = 0; i < user.getRecurrent_bills().size(); i++) {
                Calendar date = Calendar.getInstance();
                String killMe = String.valueOf(date.get(Calendar.DAY_OF_MONTH));
                if (user.getRecurrent_bills().get(i).date.equals(killMe)) {
                    Notification notification = new Notification();
                    startForeground(NOTIFICATION_ID, notification.setNotification(getApplication(), "Save", "You have a bill due today!!", R.drawable.logo));

                }
            }
        }
        System.out.println("Service Started");
        // POST request code here
        return START_STICKY;
    }

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }
}