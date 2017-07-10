package com.android.wendler.wendlerandroid.main.view.activity.set.rv;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.TextView;

import com.android.wendler.wendlerandroid.R;
import com.android.wendler.wendlerandroid.main.model.Lift;
import com.android.wendler.wendlerandroid.utils.LiftPercents;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by QiFeng on 7/10/17.
 */

public class SetViewHolder extends ViewHolder {

    @BindView(R.id.percent)
    TextView vPercent;

    @BindView(R.id.value)
    TextView vValue;

    @BindView(R.id.reps)
    TextView vReps;

    public SetViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }


    public void bindView(Lift lift, int position){
        double percent = LiftPercents.PERCENT_MAP[lift.getWeek()][position];
        int reps = LiftPercents.REP_MAP[lift.getWeek()][position];

        Resources res = itemView.getResources();

        vPercent.setText(res.getString(R.string.x_percent, (int) (percent * 100)));
        vReps.setText(res.getQuantityString(R.plurals.rep, reps, reps));
        vValue.setText(String.valueOf(percent * lift.getMax()));
    }

}
