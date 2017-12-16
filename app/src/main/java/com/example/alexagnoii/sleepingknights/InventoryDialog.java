package com.example.alexagnoii.sleepingknights;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
    Button btnEquip;
    long itemIndex = 0;
    OnClickListener onClickListener;
    public InventoryDialog(@NonNull Context context) { super(context); }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory);

        DatabaseHelper dbh = new DatabaseHelper(getContext());
        rvInventory = (RecyclerView) findViewById(R.id.rv_inventoryItems);
        rvInventory.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        ia  = new InventoryAdapter(getContext(), dbh.getAllInventoryItems());
        rvInventory.setAdapter(ia);

        ia.setOnItemClickListener(new InventoryAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(long id) {
                Log.i("LOGS|INVENTORYDIALOG", id+"");
                itemIndex = id;
            }
        });

        btnEquip = (Button) findViewById(R.id.equip);

        btnEquip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(itemIndex != 0)
                    onClickListener.onItemClick(itemIndex);
                else
                    Toast.makeText(getContext(), "Please choose an item to equip!", Toast.LENGTH_SHORT).show();
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
