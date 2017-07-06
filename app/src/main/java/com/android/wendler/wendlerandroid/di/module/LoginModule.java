package com.android.wendler.wendlerandroid.di.module;

import com.android.wendler.wendlerandroid.di.scope.ActivityScope;
import com.android.wendler.wendlerandroid.main.contract.LoginContract;
import com.android.wendler.wendlerandroid.main.interactor.LoginInteractor;
import com.android.wendler.wendlerandroid.main.presenter.LoginPresenter;
import com.android.wendler.wendlerandroid.main.view.activity.login.LoginActivity;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by QiFeng on 6/30/17.
 */

@Module
public class LoginModule {

    private LoginActivity mLoginActivity;
    private String mClientId;

    public LoginModule(LoginActivity activity, String clientId){
        mLoginActivity = activity;
        mClientId = clientId;
    }

    @Provides
    @ActivityScope
    public LoginContract.View providesView(){
        return mLoginActivity;
    }

    @Provides
    @ActivityScope
    public LoginContract.Interactor providesLoginInteractor(Retrofit retrofit){
        return new LoginInteractor(retrofit);
    }

    @Provides
    @ActivityScope
    public LoginContract.Presenter providesPresenter(LoginContract.Interactor interactor){
        return new LoginPresenter(interactor);
    }

    @Provides
    @ActivityScope
    public GoogleSignInOptions providesGoogleSignInOpts(){
        return new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .requestIdToken(mClientId)
                .build();
    }

    @Provides
    @ActivityScope
    public GoogleApiClient providesGoogleApiClient(GoogleSignInOptions opts){
        return new GoogleApiClient.Builder(mLoginActivity)
                .enableAutoManage(mLoginActivity, mLoginActivity)
                .addApi(Auth.GOOGLE_SIGN_IN_API, opts)
                .build();
    }


}
