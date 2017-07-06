package com.android.wendler.wendlerandroid.main.view.fragment;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.TextView;

import com.android.wendler.wendlerandroid.R;
import com.android.wendler.wendlerandroid.main.model.Lift;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by QiFeng on 7/5/17.
 */

class LiftViewHolder extends ViewHolder {

    private Lift mLift;

    @BindView(R.id.name)
    TextView vName;

    @BindView(R.id.max)
    TextView vMax;

    @BindView(R.id.week)
    TextView vWeek;


    public LiftViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }



    public void bind(Lift lift){
        mLift = lift;

        Resources res = itemView.getResources();

        vName.setText("test");
        vMax.setText(res.getString(R.string.max_format, lift.getMax()));
        vWeek.setText(res.getString(R.string.week_format, lift.getWeek()));
    }

}
