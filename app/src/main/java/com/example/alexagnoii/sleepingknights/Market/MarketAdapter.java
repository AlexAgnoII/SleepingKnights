package com.example.alexagnoii.sleepingknights.Market;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alexagnoii.sleepingknights.CursorRecyclerViewAdapter;
import com.example.alexagnoii.sleepingknights.DatabaseHelper;
import com.example.alexagnoii.sleepingknights.Knight.Item;
import com.example.alexagnoii.sleepingknights.Knight.SkinGiver;
import com.example.alexagnoii.sleepingknights.R;

import java.util.ArrayList;

/**
 * Created by Claude on 2017-12-16.
 */

public class MarketAdapter extends CursorRecyclerViewAdapter<MarketAdapter.ViewHolder> {


    private OnItemClickListener onItemClickListener;
    public Context context;

    public MarketAdapter(Context c, Cursor cursor) {
        super(c, cursor);
        context = c;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Cursor cursor) {
        // cursor is already pointed at the current position
        long id = cursor.getLong(cursor.getColumnIndex(Item.COLUMN_ID));
        Log.i("LOG|ONBINDVIEWHOLDER", id+"");
        DatabaseHelper dbh = new DatabaseHelper(context);


        Item item = dbh.getItem(id);
        viewHolder.tv_item.setText(item.getName());
        viewHolder.tv_price.setText(item.getCost()+"");
        viewHolder.img_marketItem.setImageResource(SkinGiver.give(id));


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
    public MarketAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items_layout, parent, false);
        return new ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_marketItem;
        TextView tv_item, tv_price;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_item = (TextView) itemView.findViewById(R.id.tv_item);
            tv_price = (TextView) itemView.findViewById(R.id.tv_price);
            img_marketItem = (ImageView) itemView.findViewById(R.id.id_item);
        }
    }

    // interface to be implemented to know if an item has been clicked or not
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        public void onItemClick(long id);
    }
}