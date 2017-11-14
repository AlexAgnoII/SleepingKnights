package com.example.alexagnoii.sleepingknights;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.alexagnoii.sleepingknights.Knight.Knight;

/**
 * Created by jessganoww on 11/12/17.
 */

public class SignUp extends AppCompatActivity {

    private EditText etUsername;
    private Button btnSubmit;
    private RelativeLayout bg;
    Typeface tf;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        etUsername = (EditText)findViewById(R.id.et_username);
        btnSubmit = (Button)findViewById(R.id.btn_submit);

        bg = (RelativeLayout) findViewById(R.id.bg);
        bg.setBackgroundResource(R.drawable.bgmove);

        tf = Typeface.createFromAsset(getAssets(), "fonts/JourneyPS3.ttf");

        etUsername.setTypeface(tf);

        AnimationDrawable frAnim2 = (AnimationDrawable) bg.getBackground();
        frAnim2.start();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Initialize user
                String userName = etUsername.getText().toString();
                Log.i("LOGS|SIGNUP ACTIVITY", "username entered-> " + userName);

                //Proceed to character creation (Send name as intent)
                Intent i = new Intent();
                i.putExtra("username", userName);
                i.setClass(getBaseContext(), CharCreationActivity.class);
                startActivity(i);
                finish(); //finish this activity.

            }
        });


    }
}
