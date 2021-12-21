package com.example.foodorderingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;

public class Form extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        Intent intent = getIntent();
        FragmentManager manager = getSupportFragmentManager();
        Boolean isDash = intent.getBooleanExtra("isDash", false);
        if (isDash) {
            OrderSuccess dash = new OrderSuccess();
            manager.beginTransaction().replace(R.id.fragmentcontainerforcustomer, dash).commit();
        }
        else {
            String dish = intent.getStringExtra("dish");
            int price = intent.getIntExtra("price", 0);
            CustomerInfo customerInfo = new CustomerInfo();
            Bundle bundle = new Bundle();
            bundle.putString("dish", dish);
            bundle.putInt("price", price);
            customerInfo.setArguments(bundle);
            manager.beginTransaction().replace(R.id.fragmentcontainerforcustomer, customerInfo).commit();
        }
    }
}