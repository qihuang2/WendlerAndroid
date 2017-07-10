package com.android.wendler.wendlerandroid.di.component;

import com.android.wendler.wendlerandroid.di.module.SetModule;
import com.android.wendler.wendlerandroid.di.scope.ActivityScope;
import com.android.wendler.wendlerandroid.main.view.activity.set.SetActivity;

import dagger.Module;
import dagger.Subcomponent;

/**
 * Created by QiFeng on 7/10/17.
 */

@ActivityScope
@Subcomponent(modules = SetModule.class)
public interface SetComponent {

    public void inject(SetActivity activity);
}
