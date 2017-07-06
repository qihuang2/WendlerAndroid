package com.android.wendler.wendlerandroid.main.model;

import android.content.SharedPreferences;
import android.os.Parcel;
import android.os.Parcelable;

import com.android.wendler.wendlerandroid.utils.SharedPrefUtils;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

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
    List<Lift> lifts;


    public static User loadFromSP(SharedPreferences sharedPreferences, Gson gson) {
        User user = gson.fromJson(sharedPreferences.getString(SharedPrefUtils.KEY_USER, "{}"), User.class);
        user.setToken(sharedPreferences.getString(SharedPrefUtils.KEY_TOKEN, null));

        return user;
    }

    public static void saveToSP(SharedPreferences preferences, User user, Gson gson) {

        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(SharedPrefUtils.KEY_USER,  gson.toJson(user));
        editor.putString(SharedPrefUtils.KEY_TOKEN, user.getToken());

        editor.apply();
    }

    public User(String id,
                String firstName,
                String lastName,
                String email,
                ArrayList<Lift> lifts) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.lifts = lifts;
    }


    protected User(Parcel in) {
        id = in.readString();
        firstName = in.readString();
        lastName = in.readString();
        email = in.readString();
        token = in.readString();
        lifts = new ArrayList<>();
        in.readList(lifts, Lift.class.getClassLoader());
    }


    public void update(User user) {
        id = user.getId();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        email = user.getEmail();
        lifts = user.getLifts();
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

    public List<Lift> getLifts() {
        return lifts;
    }

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
        parcel.writeList(lifts);
    }
}
