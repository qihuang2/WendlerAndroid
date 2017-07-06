package com.android.wendler.wendlerandroid.di.component;

import com.android.wendler.wendlerandroid.di.module.MainActivityModule;
import com.android.wendler.wendlerandroid.di.scope.ActivityScope;
import com.android.wendler.wendlerandroid.main.view.activity.main.MainActivity;

import dagger.Subcomponent;

/**
 * Created by QiFeng on 7/5/17.
 */

@ActivityScope
@Subcomponent(modules = MainActivityModule.class)
public interface MainActivityComponent {

    void inject(MainActivity activity);
}
