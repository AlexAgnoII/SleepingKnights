package com.example.alexagnoii.sleepingknights.Knight;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.alexagnoii.sleepingknights.RingtonePlayingService;

/**
 * Created by Claude on 2017-12-05.
 */

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e("We are in the receiver", "Yay");
        Intent service_intent = new Intent(context, RingtonePlayingService.class);
        Log.e("Intent set", "Yay!!!");
        context.startService(service_intent);
    }
}
