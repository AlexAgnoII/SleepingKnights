package com.example.alexagnoii.sleepingknights.Market;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Claude on 2017-12-16.
 */

public class MarketItem {

    private int itemIcon;
    private TextView name;
    private  TextView price;

    private MarketItem(){}

    public MarketItem(int itemIcon, TextView name, TextView price) {
        this.itemIcon = itemIcon;
        this.name = name;
        this.price = price;
    }

    public int getItemIcon() {
        return itemIcon;
    }

    public void setItemIcon(int itemIcon) {
        this.itemIcon = itemIcon;
    }

    public TextView getName() {
        return name;
    }

    public void setName(TextView name) {
        this.name = name;
    }

    public TextView getPrice() {
        return price;
    }

    public void setPrice(TextView price) {
        this.price = price;
    }
}
