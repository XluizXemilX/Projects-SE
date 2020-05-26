package com.example.bottomnamviagtionbar.Helpers;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.bottomnamviagtionbar.R;

public class BackgroundService extends android.app.Service {

    protected static final int NOTIFICATION_ID = 1337;

    public BackgroundService() {
        //super("BackgroundService");
        // TODO Auto-generated constructor stub
    }

    //@Override
    protected void onHandleIntent(Intent intent) {
        // TODO Auto-generated method stub
        //Toast.makeText(getApplicationContext(), "Service Started", Toast.LENGTH_SHORT).show();

          Notification notification = new Notification();
          startForeground(NOTIFICATION_ID, notification.setNotification(this, "Save", "Service restarted!", R.drawable.logo));

      System.out.println("Service Started");
        // POST request code here
    }
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);


                  Notification notification = new Notification();
          startForeground(NOTIFICATION_ID, notification.setNotification(getApplication(), "Save", "Service restarted!", R.drawable.logo));

      System.out.println("Service Started");


        return START_STICKY;
    }

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }
}