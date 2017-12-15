package com.example.alexagnoii.sleepingknights;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.example.alexagnoii.sleepingknights.RingtonePlayingService;

/**
 * Created by Claude on 2017-12-05.
 */

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        final NotificationManager notificationManager;
        notificationManager =(NotificationManager) context.getSystemService(Service.NOTIFICATION_SERVICE);

        Log.e("We are in the receiver", "Yay");
        Intent service_intent = new Intent(context, RingtonePlayingService.class);
        Log.e("Intent set", "Yay!!!");
        context.startService(service_intent);
        final int notificationID = GameActivity.NOTIFICATION_ID_PB;
        final Intent splashIntent = new Intent(context, Bonus.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, Bonus.PENDINT_SA, splashIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder Builder
                = (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                .setTicker("Get Your Bonus!")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Get it now!")
                .setContentText("Get fighting!!")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        notificationManager.cancelAll();
        notificationManager.notify(notificationID, Builder.build());

        Handler h = new Handler();
        final long delayInMilliseconds = 60000;
        h.postDelayed(new Runnable() {
            public void run() {
                notificationManager.cancel(notificationID);
            }
        }, delayInMilliseconds);
    }
}
