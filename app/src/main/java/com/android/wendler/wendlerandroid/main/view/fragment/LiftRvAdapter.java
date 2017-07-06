package com.android.wendler.wendlerandroid.main.view.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.wendler.wendlerandroid.R;
import com.android.wendler.wendlerandroid.main.model.Lift;
import com.android.wendler.wendlerandroid.main.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by QiFeng on 7/5/17.
 */

public class LiftRvAdapter extends RecyclerView.Adapter<LiftViewHolder> {

    private List<Lift> mLifts;

    public LiftRvAdapter(User user){
        mLifts = new ArrayList<>();
        mLifts.add(user.getDeadlift());
        mLifts.add(user.getBench());
        mLifts.add(user.getOverhead());
        mLifts.add(user.getSquat());
    }


    @Override
    public LiftViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LiftViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_lift, parent, false));
    }

    @Override
    public void onBindViewHolder(LiftViewHolder holder, int position) {
        holder.bind(mLifts.get(position));
    }

    @Override
    public int getItemCount() {
        return mLifts.size();
    }
}
