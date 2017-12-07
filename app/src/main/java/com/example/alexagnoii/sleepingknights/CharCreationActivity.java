package com.example.alexagnoii.sleepingknights;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alexagnoii.sleepingknights.Knight.Knight;

public class CharCreationActivity extends AppCompatActivity{

    private final int maxStatAdded = 5,
                      minHealth = 50,
                      min = 1;
    private String userName;
    private DatabaseHelper dbHelper;
    private Button btnCCproceed;
    private RelativeLayout bg;
    private TextView tvHP, lblHP, tvATK, lblATK, tvDEF, lblDEF, lblMsg, lblPts,
                     tvHPUP, tvHPDOWN,
                     tvATKUP, tvATKDOWN,
                     tvDEFUP, tvDEFDOWN;

    // tvHP ATK DEF are the values, lbl are just labels
    //lblMsg is the message shown to the user.
    //lmlPts is the remaining points that the user can use.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_char_creation);

        //Retrieve entered name from main activity
        userName = getIntent().getExtras().getString("username");
        Log.i("Inside charcreation: ", userName);

        bg = (RelativeLayout) findViewById(R.id.mainbg);
        bg.setBackgroundResource(R.drawable.bgmove);

        AnimationDrawable frAnim1 = (AnimationDrawable) bg.getBackground();
        frAnim1.start();

        dbHelper = new DatabaseHelper(getBaseContext());

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
        lblMsg.setTypeface(tf);
        lblPts.setTypeface(tf);

        lblMsg.setText("Hello " + userName + "! Your remaining points to add:  ");
        lblPts.setText(maxStatAdded+"");

        //Do some stuff that would calculate the stats added.
        tvATKUP = (TextView) findViewById(R.id.btn_ATKplus);
        tvATKDOWN = (TextView) findViewById(R.id.btn_ATKminus);

        tvDEFUP = (TextView) findViewById(R.id.btn_DEFplus);
        tvDEFDOWN = (TextView) findViewById(R.id.btn_DEFminus);

        tvHPUP = (TextView) findViewById(R.id.btn_HPplus);
        tvHPDOWN = (TextView) findViewById(R.id.btn_HPminus);

        tvATKUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int original = parseStringToInt(tvATK.getText());
                int remaining = parseStringToInt(lblPts.getText());

                if (remaining != 0) {
                    Log.i("LOGS|AddStat", "Can add atk.");
                    remaining--;
                    original++;

                    tvATK.setText(original+"");
                    lblPts.setText(remaining+"");
                }

                else {
                    Log.i("LOGS|AddStat", "Can no longer add atk.");
                }
            }
        });

        tvATKDOWN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int original = parseStringToInt(tvATK.getText());
                int remaining = parseStringToInt(lblPts.getText());

                if (remaining != maxStatAdded && original != min) {
                    Log.i("LOGS|lowerStat", "Can lower atk.");
                    remaining++;
                    original--;

                    tvATK.setText(original+"");
                    lblPts.setText(remaining+"");
                }

                else {
                    Log.i("LOGS|lowerStat", "Can no longer lower atk.");
                }

            }
        });

        tvDEFUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int original = parseStringToInt(tvDEF.getText());
                int remaining = parseStringToInt(lblPts.getText());

                if (remaining != 0) {
                    Log.i("LOGS|AddStat", "Can add");
                    remaining--;
                    original++;

                    tvDEF.setText(original+"");
                    lblPts.setText(remaining+"");
                }

                else {
                    Log.i("LOGS|AddStat", "Can no longer add");
                }
            }
        });

        tvDEFDOWN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int original = parseStringToInt(tvDEF.getText());
                int remaining = parseStringToInt(lblPts.getText());

                if (remaining != maxStatAdded && original != min) {
                    Log.i("LOGS|lowerStat", "Can lower");
                    remaining++;
                    original--;

                    tvDEF.setText(original+"");
                    lblPts.setText(remaining+"");
                }

                else {
                    Log.i("LOGS|lowerStat", "Can no longer lower");
                }

            }
        });

        tvHPUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int original = parseStringToInt(tvHP.getText());
                int remaining = parseStringToInt(lblPts.getText());

                if (remaining != 0) {
                    Log.i("LOGS|AddStat", "Can add");
                    remaining--;
                    original++;

                    tvHP.setText(original+"");
                    lblPts.setText(remaining+"");
                }

                else {
                    Log.i("LOGS|AddStat", "Can no longer add");
                }
            }
        });

        tvHPDOWN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int original = parseStringToInt(tvHP.getText());
                int remaining = parseStringToInt(lblPts.getText());

                if (remaining != maxStatAdded && original != minHealth) {
                    Log.i("LOGS|lowerStat", "Can lower");
                    remaining++;
                    original--;

                    tvHP.setText(original+"");
                    lblPts.setText(remaining+"");
                }

                else {
                    Log.i("LOGS|lowerStat", "Can no longer lower ");
                }

            }
        });

        btnCCproceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Save knight to db + save the ID on shared preference.
                if(parseStringToInt(lblPts.getText()) == 0) {
                    addUser();
                }

                else {
                    Toast.makeText(getBaseContext(), "Please don't waste your points!", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    private void addUser() {
        Knight k = new Knight(userName, parseStringToInt(tvHP.getText()),
                parseStringToInt(tvATK.getText()),
                parseStringToInt(tvDEF.getText()));
        dbHelper.addKnight(k);
        long id = dbHelper.getKnightID();

        if(id != -1) {
            SharedPreferences dsp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
            SharedPreferences.Editor dspEditor = dsp.edit();

            Log.i("LOGS|CHARCREATE", " id in preference: " + id);
            dspEditor.putLong("id", id);
            dspEditor.apply();

            //Redirect to GameActivity
            Intent i = new Intent();
            i.setClass(getBaseContext(), Splash.class);
            startActivity(i);
            finish();
        }

        else {
            Log.i("LOGS|CHARCREATE", "ID OF KNIGHT NOT FOUND: " + id);
        }
    }


    private int parseStringToInt(CharSequence value) {
        return Integer.parseInt(value.toString());
    }

}
