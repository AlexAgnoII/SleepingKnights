package com.example.alexagnoii.sleepingknights.DialogFragments;

import android.app.DialogFragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.alexagnoii.sleepingknights.Alarm;
import com.example.alexagnoii.sleepingknights.R;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Claude on 2017-11-15.
 */

public class SettingsFragment extends DialogFragment{

    Switch switch_alarm, switch_sound;
    Button btnEdit;
    TextView tvAlarm, tvAlarmTime;
    SharedPreferences sp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.activity_settings, container);

        switch_sound = (Switch) rootView.findViewById(R.id.switch_sound);
        switch_sound.setSelected(true);
        tvAlarm = (TextView) rootView.findViewById(R.id.tv_alarm);
        btnEdit = (Button) rootView.findViewById(R.id.btn_editAlarm);
        tvAlarmTime = (TextView) rootView.findViewById (R.id.tv_alarmTime);

//        switch_alarm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if(b){
//                    switch_alarm.setText("Alarm: Enabled");
//                    tvAlarm.setTextColor(Color.BLACK);
//                    btnEdit.setClickable(true);
//                    //TODO: logic for enabling alarm
//                }
//                else{
//                    switch_alarm.setText("Alarm: Disabled");
//                    tvAlarm.setTextColor(Color.GRAY);
//                    btnEdit.setClickable(false);
//                    //TODO: logic for disabling alarm
//                }
//            }
//        });

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
        sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        if(getActivity() == null){
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
                i.setClass(getActivity().getBaseContext(), Alarm.class);
                startActivity(i);
            }
        });
        return rootView;
    }
}
