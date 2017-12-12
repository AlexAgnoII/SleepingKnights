package com.example.alexagnoii.sleepingknights;

import android.app.Dialog;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.TextView;

/**
 * Created by jessganoww on 12/5/17.
 */

public class MarketDialog extends Dialog {

    private TextView lblMarket;

    public MarketDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.market);

       // lblMarket = (TextView)findViewById(R.id.lblmarket);


       // Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/JourneyPS3.ttf");
       //     lblMarket.setTypeface(tf);

    }

}
