package com.example.tinklabs.tinklabsdemo.adapter.recycler;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.tinklabs.tinklabsdemo.R;
import com.example.tinklabs.tinklabsdemo.bean.ImageBean;
import com.example.tinklabs.tinklabsdemo.bean.ImageDescriptionBean;
import com.example.tinklabs.tinklabsdemo.utils.ImageItemDiff;
import com.example.tinklabs.tinklabsdemo.utils.LogUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ryzeliu on 2018/5/5.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final static String TAG = "RecyclerAdapter";
    public static final int IMAGE_TYPE = 0x11;
    public static final int IMAGE_DESCRIPTION_TYPE = 0x12;

    private final List<ImageBean> imageBeanList = new ArrayList<>();
    private final WeakReference<Context> mContext;

    private final int mLargeImageWidth;
    private final int mLargeImageHeight;

    public RecyclerAdapter(Context context) {
        mContext = new WeakReference<>(context);
        mLargeImageWidth = context.getResources().getDisplayMetrics().widthPixels;
        mLargeImageHeight = context.getResources().getDimensionPixelSize(R.dimen.holder_view_image_height);
    }

    public void updateData(List<ImageBean> newData) {
        LogUtils.e(TAG,newData.toString());
        final ImageItemDiff itemDiff = new ImageItemDiff(imageBeanList, newData);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(itemDiff);
        imageBeanList.clear();
        imageBeanList.addAll(newData);
        diffResult.dispatchUpdatesTo(this);
    }

    public void startLoading() {
        notifyItemInserted(getItemCount());
    }

    @SuppressLint("InflateParams")
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final Context context = mContext.get();
        if (context == null) return null;
        if (IMAGE_TYPE == viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.holder_view_image, null);
            return new ImageViewHolder(view);
        } else if (IMAGE_DESCRIPTION_TYPE == viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.holder_view_image_description, null);
            return new ImageDescriptionViewHolder(view);
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Context context = mContext.get();
        if (context == null) return;
        final ImageBean imageBean = imageBeanList.get(position);;
        final int viewType = getItemViewType(position);
        if (IMAGE_TYPE == viewType) {
            final ImageViewHolder viewHolder = (ImageViewHolder) holder;
            viewHolder.getLoadingView().setVisibility(View.VISIBLE);
            Glide.with(context)
                    .load(Uri.parse(imageBean.getImageUrl()))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .override(mLargeImageWidth, mLargeImageHeight)
                    .centerCrop()
                    .listener(new RequestListener<Uri, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, Uri model, Target<GlideDrawable> target, boolean isFirstResource) {
                            viewHolder.getLoadingView().setVisibility(View.INVISIBLE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, Uri model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            viewHolder.getLoadingView().setVisibility(View.INVISIBLE);
                            return false;
                        }
                    })
                    .into(viewHolder.getImageView());
        } else if (IMAGE_DESCRIPTION_TYPE == viewType) {
            ImageDescriptionBean imageDescriptionBean = (ImageDescriptionBean) imageBean;
            ImageDescriptionViewHolder imageDescriptionViewHolder = (ImageDescriptionViewHolder) holder;
            Glide.with(context)
                    .load(Uri.parse(imageDescriptionBean.getImageUrl()))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .into(imageDescriptionViewHolder.getImageView());
            imageDescriptionViewHolder.getDescription().setText(imageDescriptionBean.getImageDescription());
            imageDescriptionViewHolder.getTitle().setText(imageDescriptionBean.getImageTitle());
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public int getItemViewType(int position) {
       return position % 2 == 0 ? IMAGE_DESCRIPTION_TYPE : IMAGE_TYPE;
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);
        if (holder instanceof ImageViewHolder) {
            //Glide.clear(((ImageTypeViewHolder) holder).getImageView());
        } else if (holder instanceof ImageDescriptionViewHolder) {
            //Glide.clear(((TextTypeViewHolder) holder).getImageView());
        }
    }

    @Override
    public int getItemCount() {
        return imageBeanList.size();
    }
}
