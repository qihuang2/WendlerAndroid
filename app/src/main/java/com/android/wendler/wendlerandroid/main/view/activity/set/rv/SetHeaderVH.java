package com.android.wendler.wendlerandroid.main.view.activity.set.rv;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.wendler.wendlerandroid.R;
import com.android.wendler.wendlerandroid.main.model.Lift;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by QiFeng on 7/10/17.
 */

public class SetHeaderVH extends RecyclerView.ViewHolder {

    @BindView(R.id.title)
    TextView vTextView;

    public SetHeaderVH(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }

    public void bundView(Lift lift){
        vTextView.setText(lift.getName());
    }
}
