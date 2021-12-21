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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class CustomerList extends Fragment {


    dbmanager database;
    public CustomerList() {
        // Required empty public constructor
    }

    public static CustomerList newInstance(String param1, String param2) {
        CustomerList fragment = new CustomerList();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_customer_list, container, false);
        Context context = view.getContext();
        database = new dbmanager(context);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        RecyclerView recyclerView = view.findViewById(R.id.userList);
        recyclerView.setLayoutManager(linearLayoutManager);
        for(Customer customer:userdatabase()){
            Log.e("users",customer.name+" "+customer.dish);
        }
        Log.e("check", String.valueOf(userdatabase().isEmpty()));
        UserAdapter userAdapter = new UserAdapter(getActivity(), userdatabase());
        recyclerView.setAdapter(userAdapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    public ArrayList<Customer> userdatabase(){
        return this.database.getCustomers();
    }
}