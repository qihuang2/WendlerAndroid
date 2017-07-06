package com.android.wendler.wendlerandroid.di.module;

import com.android.wendler.wendlerandroid.di.scope.FragmentScope;
import com.android.wendler.wendlerandroid.main.model.User;
import com.android.wendler.wendlerandroid.main.view.fragment.LiftRvAdapter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by QiFeng on 7/5/17.
 */

@Module
public class LiftModule {

    @Provides
    @FragmentScope
    public LiftRvAdapter providesLiftRvAdapter(User user){
        return new LiftRvAdapter(user);
    }

}
