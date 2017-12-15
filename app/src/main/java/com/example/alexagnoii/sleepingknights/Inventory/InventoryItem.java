package com.example.alexagnoii.sleepingknights.Inventory;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

/**
 * Created by Claude on 2017-12-16.
 */

public class InventoryItem {

    private String itemName;
    private int itemIcon;
    RecyclerView rvHelp;

    public InventoryItem(){}

    public InventoryItem(String itemName, int itemIcon) {
        this.itemName = itemName;
        this.itemIcon = itemIcon;
    }

    public String getItemName() {return itemName;}

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemIcon() {
        return itemIcon;
    }

    public void setItemIcon(int itemIcon) {
        this.itemIcon = itemIcon;
    }
}
