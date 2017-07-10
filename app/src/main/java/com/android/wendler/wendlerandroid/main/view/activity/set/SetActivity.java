package com.android.wendler.wendlerandroid.main.view.activity.set;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.wendler.wendlerandroid.R;
import com.android.wendler.wendlerandroid.WendlerApplication;
import com.android.wendler.wendlerandroid.di.module.SetModule;
import com.android.wendler.wendlerandroid.main.contract.SetContract;
import com.android.wendler.wendlerandroid.main.model.Lift;
import com.android.wendler.wendlerandroid.main.model.User;
import com.android.wendler.wendlerandroid.main.view.activity.set.rv.SetRvAdapter;
import com.google.gson.Gson;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by QiFeng on 7/6/17.
 */

public class SetActivity extends AppCompatActivity implements View.OnClickListener, SetContract.View {

    private static final String ARG_LIFT = "arg_lift";

    private Lift mLift;

    private SetRvAdapter mSetRvAdapter;

    @BindView(R.id.toolbar)
    Toolbar vToolbar;

    @BindView(R.id.recycler_view)
    RecyclerView vRecyclerView;

    @Inject
    SetContract.Presenter mPresenter;

    @Inject
    SharedPreferences mSharedPreferences;

    @Inject
    Gson mGson;

    @Inject
    User mUser;


    public static Intent getIntent(Activity origin, int index) {
        Intent i = new Intent(origin, SetActivity.class);
        i.putExtra(ARG_LIFT, index);
        return i;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);

        WendlerApplication.getAppComponent(getApplication())
                .plus(new SetModule())
                .inject(this);

        ButterKnife.bind(this);
        mPresenter.bindView(this);

        mLift = mUser.getLifts().get(getIntent().getIntExtra(ARG_LIFT, 0));
        vToolbar.setTitle(mLift.getName());
        vToolbar.setNavigationOnClickListener(this);

        mSetRvAdapter = new SetRvAdapter(mLift);

        vRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        vRecyclerView.setAdapter(mSetRvAdapter);
    }


    @OnClick(R.id.advance)
    protected void onAdvanceClick() {
        mPresenter.advanceWeek(mUser, mLift);
    }

    @Override
    public void onClick(View view) {
        onBackPressed();
    }

    @Override
    public void advanceWeek() {
        User.saveToSP(mSharedPreferences, mUser, mGson);
        Toast.makeText(this, "Updated week", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void showConnectionError() {
        Toast.makeText(this, R.string.bad_connection, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unbind();
    }
}
