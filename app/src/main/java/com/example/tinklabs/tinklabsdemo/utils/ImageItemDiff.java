package com.example.tinklabs.tinklabsdemo.utils;

import android.support.v7.util.DiffUtil;

import com.example.tinklabs.tinklabsdemo.bean.ImageBean;

import java.util.List;

/**
 * Created by ryzeliu on 2018/5/6.
 */

public class ImageItemDiff extends DiffUtil.Callback{

    private List<? extends ImageBean> mOldList;
    private List<? extends ImageBean> mNewList;

    public ImageItemDiff(List<? extends ImageBean> oldList, List<? extends ImageBean> newList) {
        mOldList = oldList;
        mNewList = newList;
    }

    @Override
    public int getOldListSize() {
        return mOldList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldList.get(oldItemPosition).getUUID().equals(mNewList.get(newItemPosition).getUUID());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return true;
    }
}
