package com.android.wendler.wendlerandroid;

import android.app.Application;

import com.android.wendler.wendlerandroid.api.UrlHelper;
import com.android.wendler.wendlerandroid.di.component.AppComponent;
import com.android.wendler.wendlerandroid.di.component.DaggerAppComponent;
import com.android.wendler.wendlerandroid.di.module.AppModule;
import com.android.wendler.wendlerandroid.di.module.NetModule;

/**
 * Created by QiFeng on 6/30/17.
 */

public class WendlerApplication extends Application{

    private AppComponent mAppComponent;

    public static AppComponent getAppComponent(Application application){
        WendlerApplication app = (WendlerApplication) application;
        return app.getAppComponent();
    }


    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(UrlHelper.getBaseUrl()))
                .build();

    }

    public AppComponent getAppComponent(){
        return mAppComponent;
    }
}
