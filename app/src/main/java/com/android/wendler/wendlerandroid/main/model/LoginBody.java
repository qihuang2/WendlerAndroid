package com.android.wendler.wendlerandroid.main.model;

import com.google.gson.annotations.Expose;

/**
 * Created by QiFeng on 6/30/17.
 */

public class LoginBody {

    @Expose
    private String token;

    public LoginBody(String token){
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
