package com.android.wendler.wendlerandroid.main.view.activity.set.rv;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.wendler.wendlerandroid.R;
import com.android.wendler.wendlerandroid.main.model.Lift;

/**
 * Created by QiFeng on 7/10/17.
 */

public class SetRvAdapter extends RecyclerView.Adapter<SetViewHolder> {

    private Lift mLift;

    public SetRvAdapter(Lift lift){
        mLift = lift;
    }


    @Override
    public SetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SetViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_set, parent, false));
    }

    @Override
    public void onBindViewHolder(SetViewHolder holder, int position) {
        holder.bindView(mLift, position);
    }


    @Override
    public int getItemCount() {
        return 3;
    }


}
