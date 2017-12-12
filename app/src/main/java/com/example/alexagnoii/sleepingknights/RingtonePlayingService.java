package com.example.alexagnoii.sleepingknights;

import android.app.Service;
import android.bluetooth.BluetoothClass;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Claude on 2017-12-06.
 */

public class RingtonePlayingService extends Service {

    MediaPlayer mediaPlayer;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("on Start command", "YAYYYYYYYYYYYYYYYYYY");
        mediaPlayer = MediaPlayer.create(this, R.raw.demonstrative);
        mediaPlayer.start();
        return START_NOT_STICKY;
    }
}
