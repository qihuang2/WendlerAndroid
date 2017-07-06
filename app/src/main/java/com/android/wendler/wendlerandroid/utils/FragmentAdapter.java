package com.android.wendler.wendlerandroid.utils;

import android.support.v4.app.Fragment;

/**
 * Created by QiFeng on 7/5/17.
 */

public interface FragmentAdapter {

    public int getCount();

    public Fragment createFragment(int position);
}
