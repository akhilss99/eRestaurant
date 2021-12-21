package com.example.foodorderingapp;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.viewHolder> {
    @NonNull
    @NotNull
    private ArrayList<Customer> customers;
    Activity mContext;
    public UserAdapter(Activity context, ArrayList<Customer> customers){
        this.mContext = context;
        this.customers = customers;
    }
    @Override
    public viewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = mContext.getLayoutInflater();
        View listitem = inflater.inflate(R.layout.userview, parent, false);
        viewHolder v = new viewHolder(listitem);
        return v;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull viewHolder holder, int position) {
        Customer customer = customers.get(position);
        holder.customername.setText(customer.name);
        holder.customerdish.setText(customer.dish);
        holder.customerdate.setText(customer.date);
    }

    @Override
    public int getItemCount() {
        return customers.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        TextView customername, customerdish, customerdate;
        public viewHolder(View itemview){
            super(itemview);
            this.customername = itemview.findViewById(R.id.username);
            this.customerdish = itemview.findViewById(R.id.userdish);
            this.customerdate = itemview.findViewById(R.id.userdate);
        }
    }
}
