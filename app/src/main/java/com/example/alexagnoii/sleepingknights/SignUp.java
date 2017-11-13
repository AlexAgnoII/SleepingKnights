package com.example.alexagnoii.sleepingknights;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

/**
 * Created by jessganoww on 11/12/17.
 */

public class SignUp extends AppCompatActivity {

    private EditText username;
    private Button btnsubmit;
    private RelativeLayout bg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        username = (EditText)findViewById(R.id.et_username);
        btnsubmit = (Button)findViewById(R.id.btn_submit);

        bg = (RelativeLayout) findViewById(R.id.unamebg);
        bg.setBackgroundResource(R.drawable.bgmove);

        AnimationDrawable frAnim2 = (AnimationDrawable) bg.getBackground();
        frAnim2.start();

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Add to database

                //Proceed to character creation

            }
        });


    }
}
