package com.example.tinklabs.tinklabsdemo.adapter.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tinklabs.tinklabsdemo.R;


/**
 * Created by ryzeliu on 2018/5/5.
 */
class ImageDescriptionViewHolder extends RecyclerView.ViewHolder {

    private ImageView mImageView;
    private TextView mTitle;
    private TextView mDescription;

    ImageDescriptionViewHolder(View itemView) {
        super(itemView);
        mImageView = itemView.findViewById(R.id.image);
        mTitle = itemView.findViewById(R.id.title);
        mDescription = itemView.findViewById(R.id.text);
    }

    ImageView getImageView() {
        return mImageView;
    }

    public TextView getTitle() {
        return mTitle;
    }

    public TextView getDescription() {
        return mDescription;
    }
}
