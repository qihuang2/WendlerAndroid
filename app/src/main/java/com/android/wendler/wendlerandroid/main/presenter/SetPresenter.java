package com.android.wendler.wendlerandroid.main.presenter;

import android.util.Log;

import com.android.wendler.wendlerandroid.main.contract.SetContract;
import com.android.wendler.wendlerandroid.main.model.Lift;
import com.android.wendler.wendlerandroid.main.model.User;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

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
    public void advanceWeek(User user, Lift lift) {
        lift.advanceWeek();
        mView.advanceWeek();

        mInteractor.updateUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<User>() {
                    @Override
                    public void onNext(@NonNull User user) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        if (mView != null) {
                            mView.showConnectionError();
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
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
