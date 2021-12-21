package com.example.foodorderingapp;

import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Replace the implementation with code for your data type.
 */
public class MyVegRecyclerViewAdapter extends RecyclerView.Adapter<MyVegRecyclerViewAdapter.ViewHolder> {


    Activity mContext;
    private final ArrayList<Food> foodlist;
    int position;

    public MyVegRecyclerViewAdapter(Activity context, ArrayList<Food> list) {
        this.foodlist = list;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = mContext.getLayoutInflater();
        View listitem = inflater.inflate(R.layout.fragment_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listitem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Food food = foodlist.get(position);
        String name = food.name;
        int imgid = food.imgid;
        String details = food.details;
        int price = food.price;
        holder.foodname.setText(name);
        holder.imagedisp.setImageResource(imgid);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, FoodDetail.class);
                intent.putExtra("food", food);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView foodname;
        ImageView imagedisp;
        LinearLayout layout;
        public ViewHolder(View itemview) {
            super(itemview);
            this.imagedisp = (ImageView) itemview.findViewById(R.id.icon);
            this.foodname = (TextView) itemview.findViewById(R.id.name);
            this.layout = (LinearLayout) itemview.findViewById(R.id.linearlayout);
        }

    }

}