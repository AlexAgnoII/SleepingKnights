package com.example.alexagnoii.sleepingknights;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

/**
 * Created by jessganoww on 12/16/17.
 */

public class InventoryDialog extends Dialog {

    RecyclerView rvInventory;

    public InventoryDialog(@NonNull Context context) { super(context); }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory);

        rvInventory = (RecyclerView)findViewById(R.id.rv_invItems);
        


    }
}
