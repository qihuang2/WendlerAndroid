package com.android.wendler.wendlerandroid.main.view.activity.launch;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.android.wendler.wendlerandroid.R;
import com.android.wendler.wendlerandroid.main.view.activity.main.MainActivity;
import com.android.wendler.wendlerandroid.WendlerApplication;
import com.android.wendler.wendlerandroid.main.view.activity.login.LoginActivity;
import com.android.wendler.wendlerandroid.utils.SharedPrefUtils;

import javax.inject.Inject;

/**
 * Created by QiFeng on 7/3/17.
 */

public class LaunchActivity extends AppCompatActivity {

    @Inject
    SharedPreferences mSharedPreferences;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WendlerApplication.getAppComponent(getApplication())
                .inject(this);

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        boolean showLogin = mSharedPreferences.getBoolean(SharedPrefUtils.KEY_SHOW_LOGIN, true);

        startActivity(showLogin ? LoginActivity.class : MainActivity.class);
    }

    private void startActivity(Class<? extends Activity> activity){
        Intent intent = new Intent(this, activity);
        startActivity(intent);
        finish();
    }
}
