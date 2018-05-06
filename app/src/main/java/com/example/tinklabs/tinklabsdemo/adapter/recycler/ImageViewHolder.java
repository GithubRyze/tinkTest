package com.example.tinklabs.tinklabsdemo.adapter.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.tinklabs.tinklabsdemo.R;

/**
 * Created by ryzeliu on 2018/5/5.
 */

class ImageViewHolder extends RecyclerView.ViewHolder {
    private ImageView mImageView;
    private ProgressBar mLoadingView;

    ImageViewHolder(View itemView) {
        super(itemView);
        mImageView = itemView.findViewById(R.id.image);
        mLoadingView = itemView.findViewById(R.id.loading);
    }

    ImageView getImageView() {
        return mImageView;
    }

    ProgressBar getLoadingView() {
        return mLoadingView;
    }
}
