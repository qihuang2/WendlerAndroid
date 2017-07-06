package com.android.wendler.wendlerandroid.di.component;

import com.android.wendler.wendlerandroid.di.module.AppModule;
import com.android.wendler.wendlerandroid.di.module.LiftModule;
import com.android.wendler.wendlerandroid.di.module.LoginModule;
import com.android.wendler.wendlerandroid.di.module.MainActivityModule;
import com.android.wendler.wendlerandroid.di.module.NetModule;
import com.android.wendler.wendlerandroid.main.view.activity.launch.LaunchActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by QiFeng on 6/30/17.
 */

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface AppComponent {

    LoginComponent plus(LoginModule loginModule);
    MainActivityComponent plus(MainActivityModule mainActivityModule);
    LiftComponent plus(LiftModule module);

    void inject(LaunchActivity launchActivity);
}

