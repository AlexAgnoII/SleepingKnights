package com.example.alexagnoii.sleepingknights;

import android.app.Dialog;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
    Button btnBuy;
    OnClickListener onClickListener;
    long buyingIndex = 0;

    public MarketDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.market);

        DatabaseHelper dbh = new DatabaseHelper(getContext());

        rvMarket = (RecyclerView) findViewById(R.id.rv_marketItems);
        rvMarket.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        ma  = new MarketAdapter(getContext(), dbh.getAllItems());
        rvMarket.setAdapter(ma);

        ma.setOnItemClickListener(new MarketAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(long id) {
                buyingIndex = id;
            }
        });

        // lblMarket = (TextView)findViewById(R.id.lblmarket);


        // Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/JourneyPS3.ttf");
        //     lblMarket.setTypeface(tf);
        btnBuy = (Button) findViewById(R.id.btn_buy);
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(buyingIndex != 0) {
                    onClickListener.onItemClick(buyingIndex);
                }
                else {
                    Toast.makeText(getContext(), "Please select an item to buy!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    // interface to be implemented to know if an item has been clicked or not
    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener{
        public void onItemClick(long id);
    }

}
