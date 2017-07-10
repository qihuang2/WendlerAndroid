package com.android.wendler.wendlerandroid.main.view.fragment.lift.rv;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.wendler.wendlerandroid.R;
import com.android.wendler.wendlerandroid.main.model.Lift;
import com.android.wendler.wendlerandroid.main.model.User;

import java.util.List;

/**
 * Created by QiFeng on 7/5/17.
 */

public class LiftRvAdapter extends RecyclerView.Adapter<LiftViewHolder> {

    private List<Lift> mLifts;
    private OnLiftClick mOnLiftClick;

    public LiftRvAdapter(User user){
        mLifts = user.getLifts();
    }

    public void setOnLiftClick(OnLiftClick onLiftClick){
        mOnLiftClick = onLiftClick;
    }

    @Override
    public LiftViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LiftViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_lift, parent, false));
    }

    @Override
    public void onBindViewHolder(LiftViewHolder holder, int position) {
        holder.bind(mLifts.get(position), position, mOnLiftClick);
    }

    @Override
    public int getItemCount() {
        return mLifts.size();
    }


    public interface OnLiftClick{
        void liftClicked(int position);
    }
}
