package com.example.alexagnoii.sleepingknights;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class Bonus extends AppCompatActivity {
    public static final int PENDINT_SA = 0;
    TextView goldBonus, randomStat, statBonus;
    String[] statList = {"ATK", "DEF", "HP"};
    Button btnProceed;

    protected void getBonusStat(){
        int index = new Random().nextInt(statList.length);
        String chosenStat = statList[index];
        randomStat.setText(chosenStat);
    }

    protected void getBonusGold(){
        int randomGold = new Random().nextInt(100);
        goldBonus.setText(String.valueOf(randomGold));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bonus);

        goldBonus = (TextView) findViewById(R.id.tv_goldBonus);
        statBonus = (TextView) findViewById(R.id.tv_randomStatBonus);
        randomStat = (TextView) findViewById(R.id.lbl_randomStat);
        btnProceed = (Button) findViewById(R.id.btn_bonusProceed);

        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/JourneyPS3.ttf");

        goldBonus.setTypeface(tf);
        statBonus.setTypeface(tf);
        randomStat.setTypeface(tf);
        btnProceed.setTypeface(tf);

        getBonusGold();
        getBonusStat();

        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setClass(getBaseContext(), GameActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
}
