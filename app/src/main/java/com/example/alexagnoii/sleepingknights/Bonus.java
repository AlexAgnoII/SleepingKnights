package com.example.alexagnoii.sleepingknights;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class Bonus extends AppCompatActivity {
    public static final int PENDINT_SA = 0;
    TextView goldBonus, randomStat, statBonus, tvCongrats, lblGold;
    Button btnProceed;
    SharedPreferences sp;
    SharedPreferences.Editor spEditor;
    long randomGold;

    protected void setBonusStat(){
        randomStat.setText("ATK");
    }

    protected void getBonusGold(){
        randomGold = new Random().nextInt(100);
        goldBonus.setText(String.valueOf(randomGold));
    }

    protected void giveReward(String randomStat){
        sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        spEditor = sp.edit();
        long currentGold = sp.getLong("gold", 0);
        int currentStat;
        spEditor.putLong("gold", currentGold + randomGold);
        currentStat = sp.getInt("attack", 0);
        spEditor.putInt("attack", currentStat + 1);
        Log.i("CURRENT ATK: ", Integer.toString(currentStat));
        spEditor.apply();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bonus);

        goldBonus = (TextView) findViewById(R.id.tv_goldBonus);
        statBonus = (TextView) findViewById(R.id.tv_randomStatBonus);
        randomStat = (TextView) findViewById(R.id.lbl_randomStat);
        btnProceed = (Button) findViewById(R.id.btn_bonusProceed);
        tvCongrats = (TextView) findViewById(R.id.tv_congratulations);
        lblGold = (TextView) findViewById(R.id.lbl_Gold);

        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/JourneyPS3.ttf");

        goldBonus.setTypeface(tf);
        statBonus.setTypeface(tf);
        randomStat.setTypeface(tf);
        tvCongrats.setTypeface(tf);
        lblGold.setTypeface(tf);

        getBonusGold();
        setBonusStat();
        giveReward(randomStat.getText().toString());
        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setClass(getBaseContext(), GameActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            }
        });

    }
}
