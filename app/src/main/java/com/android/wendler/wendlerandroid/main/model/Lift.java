package com.android.wendler.wendlerandroid.main.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by QiFeng on 6/30/17.
 */

public class Lift implements Parcelable{

    @Expose
    @SerializedName("_id")
    String id;

    @Expose
    int max;

    @Expose
    int week;

    @Expose
    String name;

    public Lift(String name, int max, int week){
        this.max = max;
        this.week = week;
        this.name = name;
    }

    protected Lift(Parcel in) {
        max = in.readInt();
        week = in.readInt();
        name = in.readString();

    }

    public static final Creator<Lift> CREATOR = new Creator<Lift>() {
        @Override
        public Lift createFromParcel(Parcel in) {
            return new Lift(in);
        }

        @Override
        public Lift[] newArray(int size) {
            return new Lift[size];
        }
    };

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeInt(max);
        parcel.writeInt(week);
        parcel.writeString(name);
    }
}
