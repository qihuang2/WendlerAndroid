package com.android.wendler.wendlerandroid.di.module;

import com.android.wendler.wendlerandroid.di.scope.ActivityScope;
import com.android.wendler.wendlerandroid.main.contract.SetContract;
import com.android.wendler.wendlerandroid.main.interactor.SetInteractor;
import com.android.wendler.wendlerandroid.main.presenter.SetPresenter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by QiFeng on 7/10/17.
 */
@Module
public class SetModule {

    @Provides
    @ActivityScope
    public SetContract.Interactor providesSetInteractor(Retrofit retrofit){
        return new SetInteractor(retrofit);
    }

    @Provides
    @ActivityScope
    public SetContract.Presenter providesSetPresenter(SetContract.Interactor interactor){
        return new SetPresenter(interactor);
    }
}
