package com.example.alexagnoii.sleepingknights;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CharCreationActivity extends AppCompatActivity {

    private final int maxStatAdded = 5;
    private Button btnCCproceed;
    private RelativeLayout bg;
    private TextView tvHP, lblHP, tvATK, lblATK, tvDEF, lblDEF, lblMsg, lblPts;
    // tvHP ATK DEF are the values, lbl are just labels
    //lblMsg is the message shown to the user.
    //lmlPts is the remaining points that the user can use.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_char_creation);

        //Retrieve entered name from main activity
        String userName = getIntent().getExtras().getString("username");
        Log.i("Inside charcreation: ", userName);


        bg = (RelativeLayout) findViewById(R.id.mainbg);
        bg.setBackgroundResource(R.drawable.bgmove);

        AnimationDrawable frAnim1 = (AnimationDrawable) bg.getBackground();
        frAnim1.start();

        btnCCproceed = (Button)findViewById(R.id.btn_CCproceed);

        tvHP = (TextView)findViewById(R.id.tv_HP);
        lblHP = (TextView)findViewById(R.id.lbl_HP);
        tvATK = (TextView)findViewById(R.id.tv_ATK);
        lblATK = (TextView)findViewById(R.id.lbl_ATK);
        tvDEF = (TextView)findViewById(R.id.tv_DEF);
        lblDEF = (TextView)findViewById(R.id.lbl_DEF);

        lblMsg = (TextView) findViewById(R.id.createMessage);
        lblPts = (TextView) findViewById(R.id.pointsRemaining);

        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/JourneyPS3.ttf");

        tvHP.setTypeface(tf);
        lblHP.setTypeface(tf);
        tvATK.setTypeface(tf);
        lblATK.setTypeface(tf);
        tvDEF.setTypeface(tf);
        lblDEF.setTypeface(tf);

        lblMsg.setText("Hello " + userName + " here is your remaining points: ");
        lblPts.setText(maxStatAdded+"");
        btnCCproceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Save knight to db

                //Redirect to GameActivity
            }
        });

    }
}
