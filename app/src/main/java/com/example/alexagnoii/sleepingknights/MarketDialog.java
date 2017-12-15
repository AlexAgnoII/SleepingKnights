package com.example.alexagnoii.sleepingknights;

import android.app.Dialog;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.alexagnoii.sleepingknights.Help.HelpAdapter;
import com.example.alexagnoii.sleepingknights.Market.MarketAdapter;
import com.example.alexagnoii.sleepingknights.Market.MarketItem;

import java.util.ArrayList;

/**
 * Created by jessganoww on 12/5/17.
 */

public class MarketDialog extends Dialog {

    private TextView lblMarket;
    private RecyclerView rvMarket;
    ArrayList<MarketItem> marketList;
    MarketAdapter ma;

    public MarketDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.market);

        rvMarket = (RecyclerView) findViewById(R.id.rv_marketItems);
        rvMarket.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        ma  = new MarketAdapter(getContext(), marketList);
        rvMarket.setAdapter(ma);

       // lblMarket = (TextView)findViewById(R.id.lblmarket);


       // Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/JourneyPS3.ttf");
       //     lblMarket.setTypeface(tf);

    }

}
