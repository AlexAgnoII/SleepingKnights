package com.example.alexagnoii.sleepingknights;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.alexagnoii.sleepingknights.Help.HelpAdapter;
import com.example.alexagnoii.sleepingknights.Help.HelpItems;

import java.util.ArrayList;

/**
 * Created by jessganoww on 12/5/17.
 */

public class HelpDialog extends Dialog {

    ArrayList<HelpItems> helpList = new ArrayList<>();
    RecyclerView rvHelp;
    HelpAdapter ha;

    public HelpDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        helpList.add(new HelpItems("Knight", "This is your character. Your goal is to defeat as many" +
                " monsters as you can in this nightmare", R.drawable.w6));
      /*  helpList.add(new HelpItems("Inventory","This is your inventory. You can store a maximum of " +
                "10 items here", R.drawable.ic_inventory));
        helpList.add(new HelpItems("Market","The market is where you can buy items to aid you " +
                "in your adventure", R.drawable.ic_market)); */
        helpList.add(new HelpItems("Field","In this game, you must navigate from room to room," +
                " and try to defeat as many monters as possible. Once you leave a room, you can't go back", R.drawable.bgn1));
        helpList.add(new HelpItems("D-pad", "Use the dpad to move your character across the map", R.drawable.ic_help));
        helpList.add(new HelpItems("Potions","Using potions restores your knight's health by 40%. Use them wisely!", R.drawable.potion));
        helpList.add(new HelpItems("Gold", "The currency used in the game. You can find gold in rooms. Use them to buy potions or new weapons", R.drawable.gold));
        helpList.add(new HelpItems("Monsters", "Monsters are the primary enemy in the game. You must defeat as many as you can", R.drawable.ic_help));
        helpList.add(new HelpItems("Weapons", "Weapons increase your attack points", R.drawable.sword1));
        helpList.add(new HelpItems("Armor and shield", "Armors and shields increase your defense points", R.drawable.armour));

        rvHelp = (RecyclerView) findViewById(R.id.rv_helpItems);
        rvHelp.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        ha  = new HelpAdapter(getContext(), helpList);
        rvHelp.setAdapter(ha);
    }

}
