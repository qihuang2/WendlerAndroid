package com.android.wendler.wendlerandroid.main.presenter;

import android.util.Log;

import com.android.wendler.wendlerandroid.main.contract.LoginContract;
import com.android.wendler.wendlerandroid.main.model.User;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by QiFeng on 6/30/17.
 */

public class LoginPresenter implements LoginContract.Presenter {

    LoginContract.View mView;
    LoginContract.Interactor mInteractor;

    Disposable mDisposable;

    public LoginPresenter(LoginContract.Interactor interactor){
        mInteractor = interactor;
    }

    @Override
    public void bind(LoginContract.View view) {
        mView = view;
    }

    @Override
    public void unbind() {
        if (mDisposable != null){
            mDisposable.dispose();
            mDisposable = null;
        }

        mView = null;
    }

    @Override
    public void activityResult(GoogleSignInResult result) {
        boolean failed = false;
        if (result.isSuccess()){
            GoogleSignInAccount account = result.getSignInAccount();

            if (account == null){
                failed = true;
            }else {
                attemptLogin(account.getIdToken());
            }

        }else failed = true;

        if (failed){
            mView.showGoogleSignInError();
        }
    }

    void attemptLogin(String token){
        if (token == null) return;

        mDisposable = mInteractor.attemptLogin(token)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<User>() {
                    @Override
                    protected void onStart() {
                        super.onStart();
                        mView.showProgress(true);
                    }

                    @Override
                    public void onNext(@NonNull User user) {
                        mView.login(user);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();

                        mView.showBadConnectionToast();
                        mView.showProgress(false);
                    }

                    @Override
                    public void onComplete() {
                        mView.showProgress(false);
                    }
                });
    }
}
