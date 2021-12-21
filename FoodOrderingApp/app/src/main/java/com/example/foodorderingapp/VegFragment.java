package com.example.foodorderingapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;



public class VegFragment extends Fragment {

    String foodtype;
    RecyclerView list;

    dbmanager database;
    public VegFragment(){
        Log.i("akhil", "Constructor fo fragment");
    }

    public static VegFragment newInstance(String foodtype) {
        VegFragment fragment = new VegFragment();
        Bundle args = new Bundle();
        args.putString("foodtype", foodtype);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        database = new dbmanager(getActivity());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("akhil", "oncreate for fragment");
        if (getArguments() != null) {
            this.foodtype = getArguments().getString("foodtype");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("akhil", "1");
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
        this.list = recyclerView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        Log.i("akhil", "2");
        if(foodtype.equals("veg")){
            MyVegRecyclerViewAdapter adapter = new MyVegRecyclerViewAdapter(getActivity(), vegdatabase());
            recyclerView.setAdapter(adapter);
        }
        else{
            MyVegRecyclerViewAdapter adapter = new MyVegRecyclerViewAdapter(getActivity(), nonvegdatabase());
            recyclerView.setAdapter(adapter);
        }
        Log.i("akhil", "3");
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public ArrayList<Food> vegdatabase(){
        return database.getVeg();
    }

    public ArrayList<Food> nonvegdatabase(){
        return database.getNonVeg();
    }
}