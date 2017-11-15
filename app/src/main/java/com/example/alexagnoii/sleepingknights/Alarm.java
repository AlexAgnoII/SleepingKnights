package com.example.alexagnoii.sleepingknights;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

public class Alarm extends AppCompatActivity {

    TimePicker timePicker;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        btnSave = (Button) findViewById(R.id.btn_saveTime);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data = new Intent(Intent.ACTION_SEND);

                data.putExtra("hour",timePicker.getCurrentHour());
                data.putExtra("minute", timePicker.getCurrentMinute());

                setResult(Activity.RESULT_OK, data);
                finish();
            }
        });
    }
}
