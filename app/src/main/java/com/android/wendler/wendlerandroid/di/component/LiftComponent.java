package com.android.wendler.wendlerandroid.di.component;

import com.android.wendler.wendlerandroid.di.module.LiftModule;
import com.android.wendler.wendlerandroid.di.scope.FragmentScope;
import com.android.wendler.wendlerandroid.main.view.fragment.LiftFragment;

import dagger.Subcomponent;

/**
 * Created by QiFeng on 7/5/17.
 */

@FragmentScope
@Subcomponent(modules = LiftModule.class)
public interface LiftComponent {

    void inject(LiftFragment fragment);
}
