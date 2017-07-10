package com.android.wendler.wendlerandroid.main.view.fragment.lift;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.wendler.wendlerandroid.R;
import com.android.wendler.wendlerandroid.WendlerApplication;
import com.android.wendler.wendlerandroid.di.component.LiftComponent;
import com.android.wendler.wendlerandroid.di.module.LiftModule;
import com.android.wendler.wendlerandroid.main.view.activity.set.SetActivity;
import com.android.wendler.wendlerandroid.main.view.fragment.lift.rv.LiftRvAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by QiFeng on 7/5/17.
 */

public class LiftFragment extends Fragment implements LiftRvAdapter.OnLiftClick {

    @BindView(R.id.recycler_view)
    RecyclerView vRecyclerView;

    @BindView(R.id.toolbar)
    Toolbar vToolbar;

    @Inject
    LiftRvAdapter mLiftRvAdapter;

    private static final int REQUEST_SET = 12;

    private LiftComponent mLiftComponent;
    private Unbinder mUnbinder;


    public static LiftFragment newInstance() {
        return new LiftFragment();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (mLiftComponent == null) {
            mLiftComponent = WendlerApplication.getAppComponent(getActivity().getApplication())
                    .plus(new LiftModule());

            mLiftComponent.inject(this);
        }


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_lift, container, false);
        mUnbinder = ButterKnife.bind(this, root);

        mLiftRvAdapter.setOnLiftClick(this);
        vRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        vRecyclerView.setAdapter(mLiftRvAdapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void liftClicked(int index) {
        Intent intent = SetActivity.getIntent(getActivity(), index);
        startActivityForResult(intent, REQUEST_SET);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SET && mLiftRvAdapter != null) {
            mLiftRvAdapter.notifyDataSetChanged();
        }
    }
}
