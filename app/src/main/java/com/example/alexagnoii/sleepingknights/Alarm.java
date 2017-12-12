package com.example.alexagnoii.sleepingknights;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.alexagnoii.sleepingknights.Knight.AlarmReceiver;

import java.util.Calendar;

public class Alarm extends AppCompatActivity {

    TimePicker timePicker;
    Button btnEnable, btnDisable;
    AlarmManager alarmManager;
    TextView alarmStatus;
    PendingIntent pending_intent;
    SharedPreferences sp;
    SharedPreferences.Editor spEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        spEditor = sp.edit();
        final Calendar calendar = Calendar.getInstance();
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        btnEnable = (Button) findViewById(R.id.btn_enable);
        btnDisable = (Button) findViewById(R.id.btn_disable);
        alarmStatus = (TextView) findViewById(R.id.tv_alarmStatus);
        final Intent alarmManagerIntent = new Intent(getBaseContext(), AlarmReceiver.class);

        int timePickerHour, timePickerMinute;
        String spAmOrPm = sp.getString("AmOrPm", null);
        timePickerHour = sp.getInt("Hour", 0);
        timePickerMinute = sp.getInt("Minute", 0);

        if(spAmOrPm != null){
            if(Build.VERSION.SDK_INT >= 23) {
                timePicker.setHour(timePickerHour);
                timePicker.setMinute(timePickerMinute);
            }
            else{
                timePicker.setCurrentHour(timePickerHour);
                timePicker.setCurrentMinute(timePickerMinute);
            }
        }
        
        btnEnable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hour = 0, minute = 0; String hourString, minuteString;
                String amOrPm;

                if(Build.VERSION.SDK_INT >= 23) {
                    calendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
                    calendar.set(Calendar.MINUTE, timePicker.getMinute());
                    hour = timePicker.getHour();
                    minute = timePicker.getMinute();
                }
                else{
                    calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
                    calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());
                    minute = timePicker.getCurrentMinute();
                    hour = timePicker.getCurrentHour();
                }
                if(hour > 12){
                    amOrPm = "PM";
                    hourString = String.valueOf(hour - 12);
                }
                else {
                    amOrPm = "AM";
                    hourString = String.valueOf(hour);
                };
                if(minute < 10)
                    minuteString = "0" + String.valueOf(minute);
                else minuteString = String.valueOf(minute);

                pending_intent = PendingIntent.getBroadcast(Alarm.this, 0, alarmManagerIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending_intent);


                spEditor.putInt("Minute", minute);
                spEditor.putInt("Hour", hour);
                spEditor.putString("AmOrPm", amOrPm);
                spEditor.commit();
                spEditor.apply();
                alarmStatus.setText("Alarm set at " + hourString + ":" + minuteString + " " + amOrPm);
            }
        });
        btnDisable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pending_intent = PendingIntent.getBroadcast(Alarm.this, 0, alarmManagerIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                spEditor.remove("Minute");
                spEditor.remove("Hour");
                spEditor.remove("AmOrPm");
                spEditor.apply();
                alarmStatus.setText("Alarm is turned off");
                alarmManager.cancel(pending_intent);
            }
        });
    }
}
