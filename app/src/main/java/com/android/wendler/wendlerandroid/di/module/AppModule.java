package com.android.wendler.wendlerandroid.di.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.android.wendler.wendlerandroid.main.model.User;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by QiFeng on 6/29/17.
 */

@Module
public class AppModule {

    private Context mContext;

    public AppModule(Context context){
        mContext = context;
    }


    @Provides
    @Singleton
    public Context providesContext(){
        return mContext;
    }

    @Provides
    @Singleton
    public SharedPreferences providesSharedPreferences(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    @Singleton
    public User providesUser(SharedPreferences sharedPreferences){
        return User.loadFromSP(sharedPreferences);
    }
}
