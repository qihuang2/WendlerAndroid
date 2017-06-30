package com.android.wendler.wendlerandroid.main.view.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.android.wendler.wendlerandroid.R;
import com.android.wendler.wendlerandroid.WendlerApplication;
import com.android.wendler.wendlerandroid.di.module.LoginModule;
import com.android.wendler.wendlerandroid.main.contract.LoginContract;
import com.android.wendler.wendlerandroid.main.model.User;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by QiFeng on 6/30/17.
 */

public class LoginActivity extends AppCompatActivity implements LoginContract.View,
        GoogleApiClient.OnConnectionFailedListener{

    private static final int SIGN_IN_REQUEST = 15;

    @Inject LoginContract.Presenter mPresenter;
    @Inject SharedPreferences mSharedPreferences;
    @Inject GoogleApiClient mGoogleApiClient;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WendlerApplication.getAppComponent(getApplication())
                .plus(new LoginModule(this, getString(R.string.default_web_client_id)))
                .inject(this);

        mPresenter.bind(this);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.google_sign_in)
    protected void onSignInClicked(){
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, SIGN_IN_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SIGN_IN_REQUEST){
            mPresenter.activityResult(Auth.GoogleSignInApi.getSignInResultFromIntent(data));
        }
    }

    @Override
    public void showProgress(boolean show) {

    }

    @Override
    public void showBadConnectionToast() {

    }

    @Override
    public void showServerErrorToast() {

    }

    @Override
    public void showGoogleSignInError() {

    }

    @Override
    public void login(User user) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unbind();
    }
}
