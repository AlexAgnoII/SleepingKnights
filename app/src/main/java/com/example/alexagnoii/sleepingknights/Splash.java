package com.example.alexagnoii.sleepingknights;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.ImageView;

/**
 * Created by jessganoww on 11/14/17.
 */

public class Splash extends Activity {

    public final int duration = 4000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        ImageView knight, zzz;

        knight = (ImageView)findViewById(R.id.knightsplash);
        zzz = (ImageView)findViewById(R.id.zzzsplash);

        AnimationDrawable frAnim1 = (AnimationDrawable) knight.getBackground();
        AnimationDrawable frAnim2 = (AnimationDrawable) zzz.getBackground();

        frAnim1.start();
        frAnim2.start();

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent i = new Intent(Splash.this, GameActivity.class);
                Splash.this.startActivity(i);
                Splash.this.finish();
            }
        }, duration);
    }
}
