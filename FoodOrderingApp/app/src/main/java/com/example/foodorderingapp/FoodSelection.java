package com.example.foodorderingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class FoodSelection extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_selection);
        Log.i("akhil", "fragment manager");
        Intent intent = getIntent();
        String foodtype = intent.getStringExtra("foodtype");
        TextView foodheader = (TextView) findViewById(R.id.foodheader);
        if(foodtype.equals("veg")){
            foodheader.setText("VEG CUISINES");
        }
        else{
            foodheader.setText("NON-VEG CUISINES");
        }
        VegFragment vegFragment = new VegFragment();
        Bundle bundle = new Bundle();
        bundle.putString("foodtype", foodtype);
        vegFragment.setArguments(bundle);
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragmentcontainer, vegFragment).commit();
    }
}