package com.Save.Save_App.Helpers;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import com.Save.Save_App.MainPages.MainActivity;
import com.Save.Save_App.R;

public class Notification {
    private PendingIntent notificationPendingIntent;

    /**
     * This is the method  called to create the Notification
     */
    public android.app.Notification setNotification(Context context, String title, String text, int icon) {
        if (notificationPendingIntent == null) {
            Intent notificationIntent = new Intent(context, MainActivity.class);
            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            //notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            notificationPendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);
        }

        android.app.Notification notification;

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // OREO
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            // Create the NotificationChannel, but only on API 26+ because
            // the NotificationChannel class is new and not in the support library
            CharSequence name = "Permanent Notification";
            //mContext.getString(R.string.channel_name);
            int importance = NotificationManager.IMPORTANCE_HIGH;

            String CHANNEL_ID = "com.example.bottomnamviagtionbar.channel";
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            //String description = mContext.getString(R.string.notifications_description);
            String description = "I would like to receive travel alerts and notifications for:";
            channel.setDescription(description);

            if (notificationManager != null) {
                createChannel(notificationManager);
            }
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, CHANNEL_ID);
            notificationBuilder.setAutoCancel(true);
            notification = notificationBuilder
                    //the log is PNG file format with a transparent background
                    .setSmallIcon(icon)
                    .setColor(ContextCompat.getColor(context, R.color.colorAccent))
                    .setContentTitle(title)
                    .setContentText(text)
                    .setDefaults(NotificationCompat.DEFAULT_ALL)
                    .setContentIntent(notificationPendingIntent)
                    .build();

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            notification = new NotificationCompat.Builder(context, "channel")
                    // to be defined in the MainActivity of the app
                    .setSmallIcon(icon)
                    .setContentTitle(title)
//                    .setColor(mContext.getResources().getColor(R.color.colorAccent))
                    .setContentText(text)
                    .setDefaults(NotificationCompat.DEFAULT_ALL)
                    .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                    .setPriority(NotificationCompat.PRIORITY_MAX)
                    .setContentIntent(notificationPendingIntent).build();
        } else {
            notification = new NotificationCompat.Builder(context, "channel")
                    // to be defined in the MainActivity of the app
                    .setSmallIcon(icon)
                    .setContentTitle(title)
                    .setContentText(text)
                    .setDefaults(NotificationCompat.DEFAULT_ALL)
                    .setPriority(NotificationCompat.PRIORITY_MAX)
                    .setContentIntent(notificationPendingIntent).build();
        }

        return notification;
    }

    @TargetApi(26)
    private void createChannel(NotificationManager notificationManager) {
        String name = "FileDownload";
        String description = "Notifications for download status";
        int importance = NotificationManager.IMPORTANCE_HIGH;

        NotificationChannel mChannel = new NotificationChannel("com.example.bottomnamviagtionbar.channel", name, importance);
        mChannel.setDescription(description);
        mChannel.enableLights(true);
        mChannel.setLightColor(Color.BLUE);
        notificationManager.createNotificationChannel(mChannel);
    }
}