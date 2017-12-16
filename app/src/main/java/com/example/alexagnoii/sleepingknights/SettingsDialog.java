package com.example.alexagnoii.sleepingknights;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

/**
 * Created by jessganoww on 12/16/17.
 */

public class SettingsDialog extends Dialog {

    Switch switch_alarm, switch_sound;
    Button btnEdit;
    TextView tvAlarm, tvAlarmTime;
    SharedPreferences sp;
    OnClickListener onClickListener;


    public SettingsDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        switch_sound = (Switch)findViewById(R.id.switch_sound);
        switch_sound.setSelected(true);
        tvAlarm = (TextView)findViewById(R.id.tv_alarm);
        btnEdit = (Button)findViewById(R.id.btn_editAlarm);
        tvAlarmTime = (TextView)findViewById (R.id.tv_alarmTime);

        switch_sound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    switch_sound.setText("Sound: Enabled");
                    //TODO: logic for enabling sound
                }
                else{
                    switch_sound.setText("Sound: Disabled");
                    //TODO: logic for disabling sound
                }
            }
        });
        sp = PreferenceManager.getDefaultSharedPreferences(getContext());
        if(getContext() == null){
            Log.e("XD", "getActivity is null");
        }
        else Log.e("getActivity ain't null", "boi");
        int hour, minute;
        String hourString, minuteString;
        String amOrPm;
        hour = sp.getInt("Hour", 0);
        minute = sp.getInt("Minute", 0);
        amOrPm = sp.getString("AmOrPm", null);

        if(hour > 12){
            hourString = String.valueOf(hour - 12);
        }
        else {
            hourString = String.valueOf(hour);
        };
        if(minute < 10)
            minuteString = "0" + String.valueOf(minute);
        else minuteString = String.valueOf(minute);

        if(amOrPm != null) {
            tvAlarmTime.setText(hourString + ":" + minuteString + " " + amOrPm);
        }
        else{
            tvAlarmTime.setText("Alarm not set!");
        }
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
              //  i.setClass(get.getBaseContext(), Alarm.class);
               // startActivity(i);
            }
        });
    }

    // interface to be implemented to know if an item has been clicked or not
    public void setOnClickListener(SettingsDialog.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener{
        public void onItemClick(long id, int kind);
    }
}
