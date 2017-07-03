package com.android.wendler.wendlerandroid.di.component;

import com.android.wendler.wendlerandroid.di.module.AppModule;
import com.android.wendler.wendlerandroid.di.module.LoginModule;
import com.android.wendler.wendlerandroid.di.module.NetModule;
import com.android.wendler.wendlerandroid.main.view.launch.LaunchActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by QiFeng on 6/30/17.
 */

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface AppComponent {

    LoginComponent plus(LoginModule loginModule);

    void inject(LaunchActivity launchActivity);

}

