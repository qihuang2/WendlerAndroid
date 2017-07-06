package com.android.wendler.wendlerandroid.main.presenter;

import com.android.wendler.wendlerandroid.main.contract.SetContract;

/**
 * Created by QiFeng on 7/6/17.
 */

public class SetPresenter implements SetContract.Presenter {

    SetContract.Interactor mInteractor;
    SetContract.View mView;


    public SetPresenter(SetContract.Interactor interactor){
        mInteractor = interactor;
    }

    @Override
    public void advanceWeek() {

    }

    @Override
    public void bindView(SetContract.View view) {
        mView = view;
    }

    @Override
    public void unbind() {
        mView = null;
    }

}
