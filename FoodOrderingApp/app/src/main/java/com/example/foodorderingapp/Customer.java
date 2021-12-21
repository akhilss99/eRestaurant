package com.example.foodorderingapp;

public class Customer {
    public String name, contact, address, date, dish;

    public Customer(String name, String contact, String address,String dish, String date){
        this.address = address;
        this.contact = contact;
        this.name = name;
        this.date = date;
        this.dish = dish;
    }

    public Customer(String name, String dish, String date){
        this.name = name;
        this.dish = dish;
        this.date = date;
    }

}
