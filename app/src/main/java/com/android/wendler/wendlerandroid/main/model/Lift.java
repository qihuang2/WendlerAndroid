package com.android.wendler.wendlerandroid.main.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

/**
 * Created by QiFeng on 6/30/17.
 */

public class Lift implements Parcelable{

    @Expose
    int max;

    @Expose
    int week;

    public Lift(int max, int week){
        this.max = max;
        this.week = week;
    }

    protected Lift(Parcel in) {
        max = in.readInt();
        week = in.readInt();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeInt(max);
        parcel.writeInt(week);
    }
}
