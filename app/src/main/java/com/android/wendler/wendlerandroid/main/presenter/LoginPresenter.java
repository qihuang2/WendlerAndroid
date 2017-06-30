package com.android.wendler.wendlerandroid.main.presenter;

import com.android.wendler.wendlerandroid.main.contract.LoginContract;

/**
 * Created by QiFeng on 6/30/17.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mView;

    @Override
    public void bind(LoginContract.View view) {
        mView = view;
    }

    @Override
    public void unbind() {
        mView = null;
    }
}
