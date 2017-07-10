package com.android.wendler.wendlerandroid.main.view.activity.main;

import android.support.v4.app.Fragment;

import com.android.wendler.wendlerandroid.main.view.fragment.lift.LiftFragment;
import com.android.wendler.wendlerandroid.utils.FragmentAdapter;

/**
 * Created by QiFeng on 7/5/17.
 */

public class MainFragmentAdapter implements FragmentAdapter {


    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Fragment createFragment(int position) {
        //// TODO: 7/5/17

        if (position == 0) return LiftFragment.newInstance();
        return new Fragment();
    }
}
