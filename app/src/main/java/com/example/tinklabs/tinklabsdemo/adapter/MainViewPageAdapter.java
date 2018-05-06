package com.example.tinklabs.tinklabsdemo.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.example.tinklabs.tinklabsdemo.R;
import com.example.tinklabs.tinklabsdemo.fragments.MainFragment;
import com.example.tinklabs.tinklabsdemo.http.DataLoader;
import com.example.tinklabs.tinklabsdemo.utils.DataType;

/**
 * Created by ryzeliu on 2018/5/5.
 */

public class MainViewPageAdapter extends FragmentPagerAdapter{

    private static final int GUIDE = 0;
    private static final int EAT = 2;
    private static final int SHOP = 1;
    private static final int PAGE_COUNT = 3;
    private Context mContext;

    public MainViewPageAdapter(FragmentManager manager, Context context) {
        super(manager);
        mContext = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        MainFragment mainFragment = (MainFragment) super.instantiateItem(container, position);
        if (position == GUIDE) {
            mainFragment.setDataLoader(new DataLoader(mContext, DataType.DATA_TYPE_GUIDE));
        } else if (position == EAT) {
            mainFragment.setDataLoader(new DataLoader(mContext, DataType.DATA_TYPE_EAT));
        } else if (position == SHOP) {
            mainFragment.setDataLoader(new DataLoader(mContext, DataType.DATA_TYPE_SHOP));
        } else {
            throw new UnsupportedOperationException();
        }
        mainFragment.initData();
        return mainFragment;
    }

    @Override
    public Fragment getItem(int position) {
        MainFragment mainFragment = new MainFragment();
        if (position == GUIDE) {
            return mainFragment;
        } else if (position == EAT) {
            return mainFragment;
        } else if (position == SHOP) {
            return mainFragment;
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == GUIDE) {
            return mContext.getString(R.string.tab_guide);
        } else if (position == EAT) {
            return mContext.getString(R.string.tab_eat);
        } else if (position == SHOP) {
            return mContext.getString(R.string.tab_shop);
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}

