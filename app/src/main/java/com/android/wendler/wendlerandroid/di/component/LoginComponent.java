package com.android.wendler.wendlerandroid.di.component;

import com.android.wendler.wendlerandroid.di.module.LoginModule;
import com.android.wendler.wendlerandroid.di.scope.ActivityScope;
import com.android.wendler.wendlerandroid.main.view.login.LoginActivity;

import dagger.Subcomponent;

/**
 * Created by QiFeng on 6/30/17.
 */

@ActivityScope
@Subcomponent(modules = {LoginModule.class})
public interface LoginComponent {

    void inject(LoginActivity activity);
}
