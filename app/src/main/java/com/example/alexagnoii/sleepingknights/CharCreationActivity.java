package com.example.alexagnoii.sleepingknights;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

public class CharCreationActivity extends AppCompatActivity {
    private NumberPicker strenghtNP, defenseNP, healthNP;
    private Button doneCreate;
    private TextView tvHP, lblHP, tvATK, lblATK, tvDEF, lblDEF;
    // tvHP ATK DEF are the values, lbl are just labels


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_char_creation);

     //   strenghtNP = (NumberPicker) findViewById(R.id.numPicker_strength);
     //   defenseNP = (NumberPicker) findViewById(R.id.numPicker_defense);
     //   healthNP = (NumberPicker) findViewById(R.id.numPicker_health);

        tvHP = (TextView)findViewById(R.id.tv_HP);
        lblHP = (TextView)findViewById(R.id.lbl_HP);
        tvATK = (TextView)findViewById(R.id.tv_ATK);
        lblATK = (TextView)findViewById(R.id.lbl_ATK);
        tvDEF = (TextView)findViewById(R.id.tv_DEF);
        lblDEF = (TextView)findViewById(R.id.lbl_DEF);

        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/JourneyPS3.ttf");

        tvHP.setTypeface(tf);
        lblHP.setTypeface(tf);
        tvATK.setTypeface(tf);
        lblATK.setTypeface(tf);
        tvDEF.setTypeface(tf);
        lblDEF.setTypeface(tf);


    }
}
