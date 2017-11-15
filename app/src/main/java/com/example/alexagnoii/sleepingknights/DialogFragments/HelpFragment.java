package com.example.alexagnoii.sleepingknights.DialogFragments;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alexagnoii.sleepingknights.Help.HelpAdapter;
import com.example.alexagnoii.sleepingknights.Help.HelpItems;
import com.example.alexagnoii.sleepingknights.R;

import java.util.ArrayList;

/**
 * Created by Claude on 2017-11-14.
 */

public class HelpFragment extends DialogFragment{

    ArrayList<HelpItems> helpList = new ArrayList<>();
    RecyclerView rvHelp;
    HelpAdapter ha;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_help, container);

        helpList.add(new HelpItems("Knight", "This is your character. Your goal is to defeat as many" +
                " monsters as you can in this nightmare", R.drawable.w6));
        helpList.add(new HelpItems("Inventory","This is your inventory. You can store a maximum of " +
                "10 items here", R.drawable.inv));
        helpList.add(new HelpItems("Market","The market is where you can buy items to aid you " +
                "in your adventure", R.drawable.market));
        helpList.add(new HelpItems("Field","In this game, you must navigate from room to room," +
                " and try to defeat as many monters as possible. Once you leave a room, you can't go back", R.drawable.bgn1));
        helpList.add(new HelpItems("D-pad", "Use the dpad to move your character across the map", R.drawable.help));
        helpList.add(new HelpItems("Potions","Using potions restores your knight's health by 40%. Use them wisely!", R.drawable.help));
        helpList.add(new HelpItems("Monsters", "Monsters are the primary enemy in the game. You must defeat as many as you can", R.drawable.help));
        helpList.add(new HelpItems("Weapons", "Weapons increase your attack points", R.drawable.help));
        helpList.add(new HelpItems("Armor and shield", "Armors and shields increase your defense points", R.drawable.help));

        rvHelp = (RecyclerView) rootView.findViewById(R.id.rv_helpItems);
        rvHelp.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false));
        ha  = new HelpAdapter(this.getActivity(), helpList);
        rvHelp.setAdapter(ha);

        return rootView;
    }
}
