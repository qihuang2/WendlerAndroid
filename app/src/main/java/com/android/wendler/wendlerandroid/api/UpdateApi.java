package com.android.wendler.wendlerandroid.api;

import com.android.wendler.wendlerandroid.main.model.User;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by QiFeng on 7/10/17.
 */

public interface UpdateApi {

    @Headers("Content-Type: application/json")
    @PUT("/user/{id}")
    Observable<User> updateUser(@Header("Authorization") String authorization, @Path("id") String id, @Body User user);

}
