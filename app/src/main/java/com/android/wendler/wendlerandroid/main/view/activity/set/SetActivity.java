package com.android.wendler.wendlerandroid.main.view.activity.set;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.android.wendler.wendlerandroid.R;
import com.android.wendler.wendlerandroid.main.contract.SetContract;
import com.android.wendler.wendlerandroid.main.model.Lift;
import com.android.wendler.wendlerandroid.main.model.User;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by QiFeng on 7/6/17.
 */

public class SetActivity extends AppCompatActivity implements View.OnClickListener, SetContract.View{

    private static final String ARG_LIFT = "arg_lift";

    private Lift mLift;

    @BindView(R.id.toolbar)
    Toolbar vToolbar;

    @BindView(R.id.recycler_view)
    RecyclerView vRecyclerView;

    @Inject
    SetContract.Presenter mPresenter;

    @Inject
    SharedPreferences mSharedPreferences;

    @Inject
    User mUser;


    public static Intent getIntent(Activity origin, Lift lift){
        Intent i = new Intent(origin, SetActivity.class);
        i.putExtra(ARG_LIFT, lift);
        return i;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);

        ButterKnife.bind(this);

        mLift = getIntent().getParcelableExtra(ARG_LIFT);
        vToolbar.setTitle(mLift.getName());
        vToolbar.setNavigationOnClickListener(this);

        mPresenter.bindView(this);

    }


    @OnClick(R.id.advance)
    protected void onAdvanceClick(){
        mPresenter.advanceWeek();
    }

    @Override
    public void onClick(View view) {
        onBackPressed();
    }

    @Override
    public void advanceWeek() {
        User.saveToSP(mSharedPreferences, mUser);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unbind();
    }
}
