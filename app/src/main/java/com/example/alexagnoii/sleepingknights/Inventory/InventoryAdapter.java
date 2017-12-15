package com.example.alexagnoii.sleepingknights.Inventory;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alexagnoii.sleepingknights.R;

import java.util.ArrayList;

/**
 * Created by Claude on 2017-12-16.
 */

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.InventoryViewHolder> {

    ArrayList<InventoryItem> inventoryItems;
    Context c;

    public InventoryAdapter(Context c, ArrayList<InventoryItem> inventoryItems) {
        this.c = c;
        this.inventoryItems = inventoryItems;
    }

    @Override
    public InventoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_items, parent, false);
        return new InventoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(InventoryAdapter.InventoryViewHolder holder, int position) {
        final InventoryItem currentInventoryItem = inventoryItems.get(position);
        holder.tvItemName.setText(currentInventoryItem.getItemName());
        holder.ivItemIcon.setImageResource(currentInventoryItem.getItemIcon());
    }

    @Override
    public int getItemCount() {
        return inventoryItems.size();
    }

    public class InventoryViewHolder extends RecyclerView.ViewHolder {
        TextView tvItemName;
        ImageView ivItemIcon;

        public InventoryViewHolder(View itemView) {
            super(itemView);
            tvItemName = itemView.findViewById(R.id.tv_inventoryItemName);
            ivItemIcon = itemView.findViewById(R.id.iv_image);
        }
    }
}
