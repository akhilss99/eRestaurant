package com.example.foodorderingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class FoodDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        Intent intent = getIntent();
        Food food = intent.getParcelableExtra("food");
        String foodname = food.name;
        String fooddetails = food.details;
        int foodprice = food.price;
        int imgid = food.imgid;

        ImageView picdisp = (ImageView) findViewById(R.id.foodpic);
        TextView namedisp = (TextView) findViewById(R.id.foodname);
        TextView detailsdisp = (TextView) findViewById(R.id.fooddetails);
        TextView pricedisp = (TextView) findViewById(R.id.foodprice);
        Button order = (Button) findViewById(R.id.placeorder);

        picdisp.setImageResource(imgid);
        namedisp.setText(foodname);
        detailsdisp.setText(fooddetails);
        pricedisp.setText("₹ "+String.valueOf(foodprice));

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Form.class);
                intent.putExtra("price", foodprice);
                intent.putExtra("dish", foodname);
                startActivity(intent);
            }
        });
    }
}