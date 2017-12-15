package com.example.alexagnoii.sleepingknights;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.alexagnoii.sleepingknights.Help.HelpAdapter;
import com.example.alexagnoii.sleepingknights.Inventory.InventoryAdapter;
import com.example.alexagnoii.sleepingknights.Inventory.InventoryItem;

import java.util.ArrayList;

/**
 * Created by jessganoww on 12/16/17.
 */

public class InventoryDialog extends Dialog {

    RecyclerView rvInventory;
    InventoryAdapter ia;
    ArrayList<InventoryItem> inventoryItems;

    public InventoryDialog(@NonNull Context context) { super(context); }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory);

        rvInventory = (RecyclerView) findViewById(R.id.rv_inventoryItems);
        rvInventory.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        ia  = new InventoryAdapter(getContext(), inventoryItems);
        rvInventory.setAdapter(ia);


    }
}
