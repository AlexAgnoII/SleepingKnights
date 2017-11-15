package com.example.alexagnoii.sleepingknights;

import android.app.FragmentManager;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.alexagnoii.sleepingknights.DialogFragments.HelpFragment;
import com.example.alexagnoii.sleepingknights.DialogFragments.SettingsFragment;

public class GameActivity extends AppCompatActivity {
    FragmentManager fm;
    HelpFragment hf;
    SettingsFragment sf;
    RelativeLayout bg;
    Button btnSettings, btnHelp, btnMarket, btnInventory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

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


    }
}
