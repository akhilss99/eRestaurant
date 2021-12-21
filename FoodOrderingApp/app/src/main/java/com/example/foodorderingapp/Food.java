package com.example.foodorderingapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Food implements Parcelable {

    public String name, details, type;
    public int imgid, price;

    public Food(String name, String details, int imgid, int price, String type){
        this.name = name;
        this.details = details;
        this.imgid = imgid;
        this.price = price;
        this.type = type;
    }
    public Food(String name, String details, int imgid, int price){
        this.name = name;
        this.details = details;
        this.imgid = imgid;
        this.price = price;
    }

    protected Food(Parcel in) {
        name = in.readString();
        details = in.readString();
        imgid = in.readInt();
        price = in.readInt();
    }

    public static final Creator<Food> CREATOR = new Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel in) {
            return new Food(in);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(details);
        parcel.writeInt(imgid);
        parcel.writeInt(price);
    }
}
