package com.yourevent.mobilevideoinvitation;

/**
 * Created by imjalpreet on 14-11-2014.
 */

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class CreateNotificationActivity extends Activity {
    long diff;
    Button home;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notify);
        home = (Button) findViewById(R.id.button1);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent("android.intent.action.HOME");
                startActivity(j);
            }
        });
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateandTime = sdf.format(new Date());
        try {
            Date date1 = sdf.parse(currentDateandTime);
            Date date2 = sdf.parse("2013-11-16");
            Calendar thatDay = Calendar.getInstance();
            thatDay.setTime(date2);
            Calendar today = Calendar.getInstance();
            thatDay.set(Calendar.YEAR, today.get(Calendar.YEAR));
            if (thatDay.getTimeInMillis() - today.getTimeInMillis() < 0)
            {
                thatDay.set(Calendar.YEAR, today.get(Calendar.YEAR) + 1);
                diff = thatDay.getTimeInMillis() - today.getTimeInMillis();
            }
            else
            {
                diff = thatDay.getTimeInMillis() - today.getTimeInMillis();
            }
            createNotification(diff);
            /*long days = diff/(24*60*60*1000);
            if(days < 12)
            {
                Log.d("Date", "Your birthday is far away");
            }*/
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //Log.d("Date: ", ""+date);
        //In this we have to check with the bdate of the user instead of 15

    }

    public void createNotification(long delay) {
        // Prepare intent which is triggered if the
        // notification is selected
        Intent i = new Intent(this, Home.class);
        //intent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1);
        //intent.putExtra(NotificationPublisher.NOTIFICATION, notification);
        //PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pIntent2 = PendingIntent.getActivity(this, 0, i, 0);

        // Build notification
        // Actions are just fake
        Notification noti =  new Notification.Builder(this)
                .setContentTitle("Your Birthday is about to come!")
                .setContentText("Invite your Friends, family!").setSmallIcon(R.drawable.ic_launcher)
                .setContentIntent(pIntent2)
                /*.addAction(R.drawable.vinvite, "Call", pIntent)
                .addAction(R.drawable.vinvite, "More", pIntent)
                .addAction(R.drawable.vinvite, "And more", pIntent)*/.build();
        //NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // hide the notification after its selected
        noti.flags |= Notification.FLAG_AUTO_CANCEL;

        //notificationManager.notify(0, noti);

        Intent intent = new Intent(this, NotificationPublisher.class);
        intent.putExtra(NotificationPublisher.NOTIFICATION_ID, 0);
        intent.putExtra(NotificationPublisher.NOTIFICATION, noti);
        PendingIntent pIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pIntent);

    }
}
