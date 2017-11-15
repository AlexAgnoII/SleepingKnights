package com.example.alexagnoii.sleepingknights.Help;

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
 * Created by Claude on 2017-11-14.
 */

public class HelpAdapter extends RecyclerView.Adapter<HelpAdapter.HelpViewHolder>{

    ArrayList<HelpItems> helpItems;
    Context c;

    public HelpAdapter(Context c, ArrayList<HelpItems> helpItems){this.c = c; this.helpItems = helpItems;}

    @Override
    public HelpAdapter.HelpViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_help, parent, false);
        return new HelpViewHolder(v);
    }

    @Override
    public void onBindViewHolder(HelpViewHolder holder, int position) {
        final HelpItems currentHelpItem = helpItems.get(position);
        holder.tvHelpTopic.setText(currentHelpItem.getHelpTopic());
        holder.tvHelpDescription.setText(currentHelpItem.getHelpDescription());
        holder.ivIcon.setImageResource(currentHelpItem.getIcon());

    }

    @Override
    public int getItemCount() {return helpItems.size();}

    public class HelpViewHolder extends RecyclerView.ViewHolder{

        TextView tvHelpTopic, tvHelpDescription;
        ImageView ivIcon;

        public HelpViewHolder(View itemView) {
            super(itemView);
            tvHelpTopic = itemView.findViewById(R.id.tv_topic);
            tvHelpDescription = itemView.findViewById(R.id.tv_description);
            ivIcon = itemView.findViewById(R.id.iv_help_icon);
        }
    }

}
