package com.android.wendler.wendlerandroid.main.interactor;

import com.android.wendler.wendlerandroid.api.LoginApi;
import com.android.wendler.wendlerandroid.main.contract.LoginContract;
import com.android.wendler.wendlerandroid.main.model.LoginBody;
import com.android.wendler.wendlerandroid.main.model.User;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * Created by QiFeng on 6/30/17.
 */

public class LoginInteractor implements LoginContract.Interactor {

    private LoginApi mLoginApi;

    public LoginInteractor(Retrofit retrofit){
        mLoginApi = retrofit.create(LoginApi.class);
    }


    @Override
    public Observable<User> attemptLogin(String token) {
        LoginBody body = new LoginBody(token);
        return mLoginApi.attemptLogin(body);
    }
}
