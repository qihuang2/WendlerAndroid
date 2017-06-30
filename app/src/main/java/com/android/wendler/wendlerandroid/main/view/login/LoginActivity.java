package com.android.wendler.wendlerandroid.main.view.login;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.android.wendler.wendlerandroid.R;
import com.android.wendler.wendlerandroid.WendlerApplication;
import com.android.wendler.wendlerandroid.di.module.LoginModule;
import com.android.wendler.wendlerandroid.main.contract.LoginContract;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import javax.inject.Inject;

/**
 * Created by QiFeng on 6/30/17.
 */

public class LoginActivity extends AppCompatActivity implements LoginContract.View,
        GoogleApiClient.OnConnectionFailedListener{

    @Inject LoginContract.Presenter mPresenter;
    @Inject SharedPreferences mSharedPreferences;
    @Inject GoogleSignInOptions mGoogleSignInOptions;
    @Inject GoogleApiClient mGoogleApiClient;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WendlerApplication.getAppComponent(getApplication())
                .plus(new LoginModule(this, getString(R.string.default_web_client_id)))
                .inject(this);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void showBadConnectionToast() {

    }

    @Override
    public void showServerErrorToast() {

    }

    @Override
    public void login() {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
