package com.example.alexagnoii.sleepingknights;

import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.alexagnoii.sleepingknights.DialogFragments.HelpFragment;
import com.example.alexagnoii.sleepingknights.DialogFragments.SettingsFragment;
import com.example.alexagnoii.sleepingknights.Knight.Knight;

public class GameActivity extends AppCompatActivity {
    FragmentManager fm;
    HelpFragment hf;
    SettingsFragment sf;
    RelativeLayout bg;
    Button btnSettings, btnHelp, btnMarket, btnInventory;
    DatabaseHelper dbh;
    Knight knight;
    SharedPreferences dsp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        dbh = new DatabaseHelper(getBaseContext());
        //Get id from sharedpreference;
        dsp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        long userID = dsp.getLong("id", -1);
        updateMainMenuStats(userID);


        btnSettings = (Button)findViewById(R.id.btn_settings);
        btnHelp = (Button)findViewById(R.id.btn_help);
        btnMarket = (Button)findViewById(R.id.btn_market);
        btnInventory = (Button)findViewById(R.id.btn_inventory);

        fm = getFragmentManager();
        hf = new HelpFragment();
        sf = new SettingsFragment();

        btnInventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDialog sd = new SimpleDialog(R.layout.inventory);
                sd.show(getSupportFragmentManager(), "");

            }
        });
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sf.show(fm, "settings");
            }
        });
        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hf.show(fm, "help");


            }
        });
        btnMarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDialog sd = new SimpleDialog(R.layout.market);
                sd.show(getSupportFragmentManager(), "");

            }
        });



    }


    private void updateMainMenuStats(long userID) {
        if(userID != -1) {
            Log.i("LOGS|GAMEACTIVITY", "User found = " + userID);
            Log.i("LOGS|GAMEACTIVITY", "User name: " + dsp.getString("name", null));
            Log.i("LOGS|GAMEACTIVITY", "Health: " + dsp.getInt("hp", -1));
            Log.i("LOGS|GAMEACTIVITY", "Attack: " + dsp.getInt("attack", -1));
            Log.i("LOGS|GAMEACTIVITY", "Defense: " + dsp.getInt("defense", -1));

        }

        else {
            Log.i("LOGS|GAMEACTIVITY", "No id for some reason wtf la?");
        }


    }
}
