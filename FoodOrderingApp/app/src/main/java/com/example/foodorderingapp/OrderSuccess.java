package com.example.foodorderingapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class OrderSuccess extends Fragment {


    Button getOrders, searchOrders, clearOrders;
    FloatingActionButton email, milestone;
    EditText searchquery;
    RecyclerView list;
    dbmanager database;
    public OrderSuccess() {
        // Required empty public constructor
    }

    public static OrderSuccess newInstance(String param1, String param2) {
        OrderSuccess fragment = new OrderSuccess();
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
        return inflater.inflate(R.layout.fragment_order_success, container, false);

    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        database = new dbmanager(view.getContext());

        searchquery = view.findViewById(R.id.searchquery);

        this.searchOrders = (Button) view.findViewById(R.id.search);
        this.getOrders = (Button) view.findViewById(R.id.getorders);
        this.clearOrders = (Button) view.findViewById(R.id.clear);

        this.email = (FloatingActionButton) view.findViewById(R.id.email);
        this.milestone = (FloatingActionButton) view.findViewById(R.id.milestone);

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"akhilss61@gmail.com","restaurantadmin@gmail.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Contacting via Email.");
                startActivity(intent);
            }
        });

        milestone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MilestoneTracker milestoneTracker = new MilestoneTracker();
                getParentFragmentManager().beginTransaction().replace(R.id.fragmentcontainerforcustomer, milestoneTracker).addToBackStack(null).commit();
            }
        });

        getOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomerList customerList = new CustomerList();
                getParentFragmentManager().beginTransaction().replace(R.id.fragmentcontainerforcustomer, customerList).addToBackStack(null).commit();
            }
        });

        this.searchOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchphrase = searchquery.getText().toString();
                final Dialog dialog = new Dialog(view.getContext());
                dialog.setContentView(R.layout.custom_dialog);
                dialog.setTitle("Title...");
                list = (RecyclerView) dialog.findViewById(R.id.List);
                if(database.searchByUser(searchphrase).size()>0){
                    UserAdapter adapter = new UserAdapter(getActivity(),database.searchByUser(searchphrase));
                    LinearLayoutManager manager = new LinearLayoutManager(view.getContext());
                    list.setLayoutManager(manager);
                    list.setAdapter(adapter);
                    int width = (int)(getResources().getDisplayMetrics().widthPixels*0.95);
                    int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);
                    dialog.getWindow().setLayout(width, height);
                    dialog.show();
                }
                else{
                    Toast.makeText(view.getContext(), "No such name '"+searchphrase+"' ordered before.", Toast.LENGTH_LONG).show();
                }
            }
        });

        this.clearOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int delUsers = database.deleteAllUsers();
                Toast.makeText(view.getContext(), delUsers+" user order(s) cleared!",Toast.LENGTH_SHORT).show();
            }
        });

    }

}