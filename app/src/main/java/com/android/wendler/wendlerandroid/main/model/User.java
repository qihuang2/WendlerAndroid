package com.android.wendler.wendlerandroid.main.model;

import android.content.SharedPreferences;
import android.os.Parcel;
import android.os.Parcelable;

import com.android.wendler.wendlerandroid.utils.SharedPrefUtils;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by QiFeng on 6/30/17.
 */

public class User implements Parcelable {

    @Expose
    @SerializedName("_id")
    String id;

    @Expose
    String firstName;

    @Expose
    String lastName;

    @Expose
    String email;

    @Expose(serialize = false)
    String token;

    @Expose
    Lift deadlift;

    @Expose
    Lift squat;

    @Expose
    Lift overhead;

    @Expose
    Lift bench;


    public static User loadFromSP(SharedPreferences sharedPreferences) {
        User user = new User(
                sharedPreferences.getString(SharedPrefUtils.KEY_ID, null),
                sharedPreferences.getString(SharedPrefUtils.KEY_FIRST_NAME, null),
                sharedPreferences.getString(SharedPrefUtils.KEY_LAST_NAME, null),
                sharedPreferences.getString(SharedPrefUtils.KEY_EMAIL_NAME, null),
                new Lift(sharedPreferences.getInt(SharedPrefUtils.KEY_DL_MAX, 0), sharedPreferences.getInt(SharedPrefUtils.KEY_DL_WEEK, 0)),
                new Lift(sharedPreferences.getInt(SharedPrefUtils.KEY_SQ_MAX, 0), sharedPreferences.getInt(SharedPrefUtils.KEY_SQ_WEEK, 0)),
                new Lift(sharedPreferences.getInt(SharedPrefUtils.KEY_BE_MAX, 0), sharedPreferences.getInt(SharedPrefUtils.KEY_BE_WEEK, 0)),
                new Lift(sharedPreferences.getInt(SharedPrefUtils.KEY_OH_MAX, 0), sharedPreferences.getInt(SharedPrefUtils.KEY_OH_WEEK, 0))
        );

        user.setToken(sharedPreferences.getString(SharedPrefUtils.KEY_TOKEN, null));

        return user;
    }

    public static void saveToSP(SharedPreferences preferences, User user) {

        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(SharedPrefUtils.KEY_ID, user.getId());
        editor.putString(SharedPrefUtils.KEY_FIRST_NAME, user.getFirstName());
        editor.putString(SharedPrefUtils.KEY_LAST_NAME, user.getLastName());
        editor.putString(SharedPrefUtils.KEY_EMAIL_NAME, user.getEmail());
        editor.putInt(SharedPrefUtils.KEY_DL_MAX, user.getDeadlift().getMax());
        editor.putInt(SharedPrefUtils.KEY_DL_WEEK, user.getDeadlift().getWeek());
        editor.putInt(SharedPrefUtils.KEY_SQ_MAX, user.getSquat().getMax());
        editor.putInt(SharedPrefUtils.KEY_SQ_WEEK, user.getSquat().getWeek());
        editor.putInt(SharedPrefUtils.KEY_OH_MAX, user.getOverhead().getMax());
        editor.putInt(SharedPrefUtils.KEY_OH_WEEK, user.getOverhead().getWeek());
        editor.putInt(SharedPrefUtils.KEY_BE_MAX, user.getBench().getMax());
        editor.putInt(SharedPrefUtils.KEY_BE_WEEK, user.getBench().getWeek());

        editor.putString(SharedPrefUtils.KEY_TOKEN, user.getToken());

        editor.apply();
    }

    public User(String id,
                String firstName,
                String lastName,
                String email,
                Lift deadLift,
                Lift squat,
                Lift bench,
                Lift overhead) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.deadlift = deadLift;
        this.squat = squat;
        this.bench = bench;
        this.overhead = overhead;
    }


    protected User(Parcel in) {
        id = in.readString();
        firstName = in.readString();
        lastName = in.readString();
        email = in.readString();
        deadlift = in.readParcelable(Lift.class.getClassLoader());
        squat = in.readParcelable(Lift.class.getClassLoader());
        overhead = in.readParcelable(Lift.class.getClassLoader());
        bench = in.readParcelable(Lift.class.getClassLoader());
    }


    public void update(User user) {
        id = user.getId();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        email = user.getEmail();
        deadlift = user.getDeadlift();
        overhead = user.getOverhead();
        squat = user.getSquat();
        bench = user.getBench();
        token = user.getToken();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Lift getDeadlift() {
        return deadlift;
    }

    public void setDeadlift(Lift deadlift) {
        this.deadlift = deadlift;
    }

    public Lift getSquat() {
        return squat;
    }

    public void setSquat(Lift squat) {
        this.squat = squat;
    }

    public Lift getOverhead() {
        return overhead;
    }

    public void setOverhead(Lift overhead) {
        this.overhead = overhead;
    }

    public Lift getBench() {
        return bench;
    }

    public void setBench(Lift bench) {
        this.bench = bench;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(firstName);
        parcel.writeString(lastName);
        parcel.writeString(email);
        parcel.writeString(token);
        parcel.writeParcelable(deadlift, i);
        parcel.writeParcelable(squat, i);
        parcel.writeParcelable(bench, i);
        parcel.writeParcelable(overhead, i);
    }
}
