package com.example.foodorderingapp;

import android.app.AlertDialog;
import java.util.regex.*;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import org.jetbrains.annotations.NotNull;

public class CustomerInfo extends Fragment {

    String dish;
    int price;
    Customer customer;
    dbmanager database;
    Context mContext;
    String firebasebill;
    public CustomerInfo() {
        // Required empty public constructor
    }


    public static CustomerInfo newInstance(String name, String address, String phone) {
        CustomerInfo fragment = new CustomerInfo();
        Bundle args = new Bundle();
        args.putString("name", name);
        args.putString("address", address);
        args.putString("phone", phone);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.dish = getArguments().getString("dish");
            this.price = getArguments().getInt("price");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_customer_info, container, false);
        mContext = view.getContext();
        this.database = new dbmanager(mContext);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText nameview = (EditText) view.findViewById(R.id.name);
        EditText contactview = (EditText) view.findViewById(R.id.contact);
        EditText addressview = (EditText) view.findViewById(R.id.address);
        Button submitbtn = (Button) view.findViewById(R.id.submit);

        NotificationChannel channel = new NotificationChannel("deliverynotification", "deliverynotification", NotificationManager.IMPORTANCE_HIGH);
        NotificationManager manager = view.getContext().getSystemService(NotificationManager.class);
        manager.createNotificationChannel(channel);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference("Bill");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                firebasebill = snapshot.getValue(String.class);
                Log.d("firebase", "Value is: " + firebasebill);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String customername = nameview.getText().toString().trim();
                String customercontact = contactview.getText().toString().trim();
                String customeraddress = addressview.getText().toString().trim();
                if(customername.equals("Name")||!(customername.length()>0)||customername.equals(" ")){
                    nameview.setError("Enter your valid name!");
                }
                else if(customercontact.length()<10||customercontact.contains(" ")||!checkPhone(customercontact)){
                    contactview.setError("Enter your valid contact!");
                }
                else if(customeraddress.length()==0){
                    addressview.setError("Enter a valid address!");
                }
                else{
                    NotificationCompat.Builder notbuilder = new NotificationCompat.Builder(view.getContext(), "deliverynotification");
                    notbuilder.setContentTitle("Delivery Update");
                    notbuilder.setContentText("Hi, "+customername+". Your order has been placed, please be patient while our delivery guys contact you.");
                    notbuilder.setSmallIcon(R.drawable.icon);
                    notbuilder.setAutoCancel(true);

                    NotificationManagerCompat compat = NotificationManagerCompat.from(view.getContext());
                    compat.notify(1, notbuilder.build());

                    DateFormat df = new SimpleDateFormat("EEE, MMM d, yyyy");
                    String date = df.format(Calendar.getInstance().getTime());
                    Log.e("date", date);
                    Log.e("akhil", customername);

                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setMessage("Congrats, your order has been placed.\n"+"Keep ₹"+String.valueOf(price)+" with you.");
                    builder.setIcon(R.drawable.icon);
                    builder.setTitle("Order Placed!");
                    builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                            // TODO Auto-generated method stub
                        }
                    });
                    builder.show();




                    int compute = Integer.parseInt(firebasebill)+price;
                    myRef.setValue(String.valueOf(compute));

                    customer = new Customer(customername, customercontact, customeraddress, dish, date);
                    Log.e("akhil", customer.name+" "+customer.date+" "+customer.dish);
                    database.addCustomer(customer);

                    OrderSuccess orderSuccess = new OrderSuccess();
                    getParentFragmentManager().beginTransaction().replace(R.id.fragmentcontainerforcustomer, orderSuccess).commit();
                }
            }
        });
    }

    public Boolean checkPhone(String s){

        Pattern p = Pattern.compile("(0|91)?[7-9][0-9]{9}");
        Matcher m = p.matcher(s);
        return (m.find() && m.group().equals(s));
    }
}