package com.android.wendler.wendlerandroid.api;

import com.android.wendler.wendlerandroid.main.model.LoginBody;
import com.android.wendler.wendlerandroid.main.model.User;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by QiFeng on 6/30/17.
 */

public interface LoginApi {

    @POST("/login")
    @FormUrlEncoded
    Observable<User> attemptLogin(@Body LoginBody body);

}
