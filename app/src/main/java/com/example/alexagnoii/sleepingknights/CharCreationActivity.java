package com.example.alexagnoii.sleepingknights;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.NumberPicker;

public class CharCreationActivity extends AppCompatActivity {
    private NumberPicker strenghtNP, defenseNP, healthNP;
    private Button doneCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_char_creation);

        strenghtNP = (NumberPicker) findViewById(R.id.numPicker_strength);
        defenseNP = (NumberPicker) findViewById(R.id.numPicker_defense);
        healthNP = (NumberPicker) findViewById(R.id.numPicker_health);



    }
}
