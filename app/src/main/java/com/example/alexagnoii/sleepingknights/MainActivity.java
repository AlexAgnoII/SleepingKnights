package com.example.alexagnoii.sleepingknights;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private ImageView ivPerson;
    private Button btnPlay;
    private RelativeLayout bg;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //test push

        ivPerson = (ImageView)findViewById(R.id.ivPerson);
        btnPlay = (Button)findViewById(R.id.btn_play);
        ivPerson.setBackgroundResource(R.drawable.walking);

        bg = (RelativeLayout) findViewById(R.id.mainbg);
        bg.setBackgroundResource(R.drawable.bgmove);


        AnimationDrawable frAnim1 = (AnimationDrawable) ivPerson.getBackground();
        AnimationDrawable frAnim2 = (AnimationDrawable) bg.getBackground();

        frAnim1.start();
        frAnim2.start();


        dbHelper = new DatabaseHelper(getBaseContext());
        //dbHelper.deleteAll(); //temporary deletes the knights, call this for testing of preference.
        Log.i("mainActivity", "main");
        btnPlay.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                SharedPreferences dsp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                long userID = dsp.getLong("id", -1);
                Intent i = new Intent();


                //CHECK IF SHAREDPREFERENCE HAS A PLAYER
                if(userID == -1) {
                    //if doesnt have, go to char creation
                    Log.i("LOGS|MAINACTIVITY", "no user found from preference");
                    i.setClass(getBaseContext(), SignUp.class);
                    startActivity(i);
                    finish(); //finish this activity.

                }

                //if have, go to gameactivity
                else {
                    Log.i("LOGS|MAINACTIVITY", "user found from preference with ID: " + userID);
                    i.setClass(getBaseContext(), Splash.class);
                    startActivity(i);
                    finish();
                }

            }
        });
    }
}
