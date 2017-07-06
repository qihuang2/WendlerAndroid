package com.android.wendler.wendlerandroid.di.module;

import com.android.wendler.wendlerandroid.di.scope.ActivityScope;
import com.android.wendler.wendlerandroid.main.view.activity.main.MainFragmentAdapter;
import com.android.wendler.wendlerandroid.utils.FragmentSupplier;

import dagger.Module;
import dagger.Provides;

/**
 * Created by QiFeng on 7/5/17.
 */

@Module
public class MainActivityModule {

    @Provides
    @ActivityScope
    public MainFragmentAdapter providesFragmentAdapter(){
        return new MainFragmentAdapter();
    }

    @Provides
    @ActivityScope
    public FragmentSupplier providesFragmentSupplier(MainFragmentAdapter adapter){
        return new FragmentSupplier(adapter);
    }
}
