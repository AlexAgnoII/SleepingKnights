package com.example.alexagnoii.sleepingknights.DialogFragments;

import android.app.DialogFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.alexagnoii.sleepingknights.R;

/**
 * Created by Claude on 2017-11-15.
 */

public class SettingsFragment extends DialogFragment{

    Switch switch_alarm, switch_sound;
    Button btnEdit;
    TextView tvAlarm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_settings, container);

        switch_alarm = (Switch) rootView.findViewById(R.id.switch_alarm);
        switch_alarm.setSelected(true);
        switch_sound = (Switch) rootView.findViewById(R.id.switch_sound);
        switch_sound.setSelected(true);
        tvAlarm = (TextView) rootView.findViewById(R.id.tv_alarm);

        switch_alarm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    switch_alarm.setText("Alarm: Enabled");
                    tvAlarm.setTextColor(Color.BLACK);
                    //TODO: logic for enabling alarm
                }
                else{
                    switch_alarm.setText("Alarm: Disabled");
                    tvAlarm.setTextColor(Color.GRAY);
                    //TODO: logic for disabling alarm
                }
            }
        });

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
        return rootView;
    }
}
