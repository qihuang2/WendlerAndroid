package com.android.wendler.wendlerandroid.main.view.activity.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.android.wendler.wendlerandroid.R;
import com.android.wendler.wendlerandroid.WendlerApplication;
import com.android.wendler.wendlerandroid.di.module.MainActivityModule;
import com.android.wendler.wendlerandroid.utils.FragmentSupplier;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener,
        BottomNavigationView.OnNavigationItemReselectedListener{

    @Inject
    FragmentSupplier mFragmentSupplier;

    @BindView(R.id.bottom_nav)
    BottomNavigationView vBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WendlerApplication.getAppComponent(getApplication())
                .plus(new MainActivityModule())
                .inject(this);

        ButterKnife.bind(this);

        handleSavedInstance(savedInstanceState);

        vBottomNavigationView.setOnNavigationItemSelectedListener(this);
        vBottomNavigationView.setOnNavigationItemReselectedListener(this);
    }

    private void handleSavedInstance(Bundle savedInstanceState){
        if (savedInstanceState != null){
            mFragmentSupplier.restoreInstance(savedInstanceState);
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_holder);
            mFragmentSupplier.setFragmentAtPos(mFragmentSupplier.getLastQueriedPosition(), fragment);
        }else {
            mFragmentSupplier.setLastQueriedPosition(0);
            replaceFragment(0, "TEMP");
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mFragmentSupplier.saveInstance(outState);
    }

    protected void replaceFragment(int pos, String tag){
        Fragment fragment = mFragmentSupplier.getFragmentAt(pos);
        if (fragment == null) return;

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.fragment_holder, fragment, tag)
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_lifts:
                replaceFragment(0, "TEMP");
                break;
            case R.id.menu_history:
                replaceFragment(1, "TEMP");
                break;
        }
        return true;
    }

    @Override
    public void onNavigationItemReselected(@NonNull MenuItem item) {

    }
}
