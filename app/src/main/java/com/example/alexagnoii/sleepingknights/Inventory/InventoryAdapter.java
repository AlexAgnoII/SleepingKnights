package com.example.alexagnoii.sleepingknights.Inventory;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alexagnoii.sleepingknights.CursorRecyclerViewAdapter;
import com.example.alexagnoii.sleepingknights.DatabaseHelper;
import com.example.alexagnoii.sleepingknights.Knight.Item;
import com.example.alexagnoii.sleepingknights.R;

import java.util.ArrayList;

/**
 * Created by Claude on 2017-12-16.
 */

public class InventoryAdapter extends CursorRecyclerViewAdapter<InventoryAdapter.ViewHolder> {


    private OnItemClickListener onItemClickListener;
    public Context context;
    public InventoryAdapter(Context c, Cursor cursor) {
        super(c, cursor);
        context = c;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Cursor cursor) {
        // cursor is already pointed at the current position
        long id = cursor.getLong(cursor.getColumnIndex("item_id"));
        DatabaseHelper dbh = new DatabaseHelper(context);

        Item item = dbh.getItem(id);
        viewHolder.tv_invItemName.setText(item.getName() + "|" + item.getSkinId());

        // set the database id to the viewholder's itemView (the "whole row" view)
        viewHolder.itemView.setTag(id);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass id to caller
                onItemClickListener.onItemClick((Long) v.getTag());
            }
        });

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_items, parent, false);
        return new ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img_invItem;
        TextView tv_invItemName;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_invItemName = (TextView) itemView.findViewById(R.id.tv_inventoryItemName);
            img_invItem = (ImageView) itemView.findViewById(R.id.iv_image);
        }
    }

    // interface to be implemented to know if an item has been clicked or not
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        public void onItemClick(long id);
    }

}
