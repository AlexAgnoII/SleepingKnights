package com.example.alexagnoii.sleepingknights;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private ImageView ivPerson;
    private Button btnplay;
    private RelativeLayout bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //test push

        ivPerson = (ImageView)findViewById(R.id.ivPerson);
        btnplay = (Button)findViewById(R.id.btn_play);
        ivPerson.setBackgroundResource(R.drawable.walking);

        bg = (RelativeLayout) findViewById(R.id.mainbg);
        bg.setBackgroundResource(R.drawable.bgmove);


        AnimationDrawable frAnim1 = (AnimationDrawable) ivPerson.getBackground();
        AnimationDrawable frAnim2 = (AnimationDrawable) bg.getBackground();

        frAnim1.start();
        frAnim2.start();

        btnplay.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                DatabaseHelper dm = new DatabaseHelper(getBaseContext());
                //CHECK IF DATABASE HAS A PLAYER

                //if doesnt have, go to char creation
                Intent i = new Intent();
                i.setClass(getBaseContext(), SignUp.class);
                startActivity(i);
                finish(); //finish this activity.

                //if have, go to gameactivity
            }
        });
    }
}
