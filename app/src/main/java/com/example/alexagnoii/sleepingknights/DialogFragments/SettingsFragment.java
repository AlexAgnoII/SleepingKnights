package com.example.alexagnoii.sleepingknights.DialogFragments;

import android.app.DialogFragment;
import android.content.Intent;
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

import com.example.alexagnoii.sleepingknights.Alarm;
import com.example.alexagnoii.sleepingknights.R;

import static android.app.Activity.RESULT_OK;

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
        final View rootView = inflater.inflate(R.layout.activity_settings, container);

        switch_alarm = (Switch) rootView.findViewById(R.id.switch_alarm);
        switch_alarm.setSelected(true);
        switch_sound = (Switch) rootView.findViewById(R.id.switch_sound);
        switch_sound.setSelected(true);
        tvAlarm = (TextView) rootView.findViewById(R.id.tv_alarm);
        btnEdit = (Button) rootView.findViewById(R.id.btn_editAlarm);

        switch_alarm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    switch_alarm.setText("Alarm: Enabled");
                    tvAlarm.setTextColor(Color.BLACK);
                    btnEdit.setClickable(true);
                    //TODO: logic for enabling alarm
                }
                else{
                    switch_alarm.setText("Alarm: Disabled");
                    tvAlarm.setTextColor(Color.GRAY);
                    btnEdit.setClickable(false);
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

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setAction(Intent.ACTION_CALL);
                i.setClass(getActivity().getBaseContext(), Alarm.class);
                startActivityForResult(i, 0);

            }
        });
        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 0 && resultCode == RESULT_OK){
            tvAlarm.setText("Alarm Time: " + data.getExtras().getInt("hour") + ":" + data.getExtras().getInt("minute"));
        }
    }
}
