package com.example.kevinyue.wineselector.Element;


import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by kevinyue on 11.04.16.
 */
public class Wine extends ArrayList<Wine> implements Parcelable {
    private int id;
    private String type;
    private String name;
    private String country;
    private int dice;
    private String description;
    private String price;

    public Wine() {

    }

    // Constructor
    public Wine(String type, String name, String country, int dice, String description, String price) {
        super();
        this.type = type;
        this.name = name;
        this.country = country;
        this.dice = dice;
        this.description = description;
        this.price = price;
        Log.d("Wine", this.toString());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Storing the Wine data to Parcel object
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        /*
        dest.writeStringArray(new String[] {
                this.type,
                this.name,
                this.country,
                String.valueOf(this.dice),
                this.description,
                this.price
        });
        */
        dest.writeString(type);
        dest.writeString(name);
        dest.writeString(country);
        dest.writeInt(dice);
        dest.writeString(description);
        dest.writeString(price);
    }

    /**
     * Retrieving wine data from parcel object
     * This constructor is invoked by the method createFromParcel of the object creator
     * @param in
     */
    private Wine(Parcel in) {
        type = in.readString();
        name = in.readString();
        country = in.readString();
        dice = in.readInt();
        description = in.readString();
        price = in.readString();
    }

    // Parceable creator
    public static final Parcelable.Creator<Wine> CREATOR = new Creator<Wine>() {
        @Override
        public Wine createFromParcel(Parcel source) {
            /*Wine mWine = new Wine();
            mWine.type = source.readString();
            mWine.name = source.readString();
            mWine.country = source.readString();
            mWine.dice = source.readInt();
            mWine.description = source.readString();
            mWine.price = source.readString();
            return mWine;*/
            return new Wine(source);
        }

        @Override
        public Wine[] newArray(int size) {
            return new Wine[size];
        }
    };

    // Getter and setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getDice() {
        return dice;
    }

    public void setDice(int dice) {
        this.dice = dice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "Wine {" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", dice=" + dice +
                ", description='" + description + '\'' +
                ", price='" + price +
                '}';
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        Wine other = (Wine) obj;
        if(name != other.name)
            return false;
        return true;
    }


}
