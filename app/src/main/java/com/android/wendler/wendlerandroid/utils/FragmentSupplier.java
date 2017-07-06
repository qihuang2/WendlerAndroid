package com.android.wendler.wendlerandroid.utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by QiFeng on 7/5/17.
 */

public class FragmentSupplier {

    private static final String KEY_BUNDLE = "fragment_supplier_key";
    private static final String KEY_LAST_QUERIED = "last_queried";


    private int mLastQueriedPosition;
    private Fragment[] mFragments;
    private FragmentAdapter mFragmentAdapter;

    public FragmentSupplier(FragmentAdapter adapter){
        mFragmentAdapter = adapter;
        mFragments = new Fragment[mFragmentAdapter.getCount()];
        mLastQueriedPosition = -1;
    }

    public Fragment getFragmentAt(int pos){
        if (pos < 0 || pos >= mFragmentAdapter.getCount()) return null;

        if (mFragments[pos] == null){
            mFragments[pos] = mFragmentAdapter.createFragment(pos);
        }

        mLastQueriedPosition = pos;

        return mFragments[pos];
    }

    public void setFragmentAtPos(int pos, Fragment fragment){
        if (pos < -1 || pos > mFragmentAdapter.getCount()) return;

        mFragments[pos] = fragment;
    }

    public int getLastQueriedPosition() {
        return mLastQueriedPosition;
    }

    public void setLastQueriedPosition(int lastQueriedPosition) {
        mLastQueriedPosition = lastQueriedPosition;
    }

    public void saveInstance(Bundle bundle){
        Bundle b = new Bundle();
        b.putInt(KEY_LAST_QUERIED, mLastQueriedPosition);
        bundle.putParcelable(KEY_BUNDLE, b);
    }

    public void restoreInstance(Bundle bundle){
        Bundle b = bundle.getBundle(KEY_BUNDLE);
        if (b != null){
            mLastQueriedPosition = b.getInt(KEY_LAST_QUERIED);
        }
    }
}
