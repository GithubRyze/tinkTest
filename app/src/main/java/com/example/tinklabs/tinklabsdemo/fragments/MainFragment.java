package com.example.tinklabs.tinklabsdemo.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tinklabs.tinklabsdemo.R;
import com.example.tinklabs.tinklabsdemo.adapter.recycler.RecyclerAdapter;
import com.example.tinklabs.tinklabsdemo.bean.ImageBean;
import com.example.tinklabs.tinklabsdemo.http.DataLoader;
import com.example.tinklabs.tinklabsdemo.http.LoadingCallBack;

import java.util.List;

/**
 * Created by ryzeliu on 2018/5/5.
 */

public class MainFragment extends Fragment implements LoadingCallBack {
    private static final String TAG = "BaseFragment";
    private static final boolean DEBUG = true;

    private DataLoader mDataLoader;

    private RecyclerView mRecyclerView;
    private RecyclerAdapter mRecyclerAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    private boolean mLoading = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setDataLoader(@NonNull DataLoader dataLoader) {
        mDataLoader = dataLoader;
        mDataLoader.registerCallBack(this);
    }

    public void loadMoreData() {
        if (mDataLoader == null) {
            throw new RuntimeException("Cannot load data without data loader");
        }
        mDataLoader.load();
    }

    public void initData() {
        if (mDataLoader == null) {
            throw new RuntimeException("Cannot load data without data loader");
        }
        List<ImageBean> items = mDataLoader.getData();
        if (items.isEmpty()) {
            loadMoreData();
        } else {
            if (mRecyclerAdapter != null) {
                mRecyclerAdapter.updateData(items);
            }
        }
    }

    @Override
    public void onLoadingStart() {
        if (DEBUG) {
            Log.v(TAG, "onLoadingStart");
        }
        if (mRecyclerView != null && mRecyclerAdapter != null) {
            mRecyclerView.post(new Runnable() {
                @Override
                public void run() {
                    mRecyclerAdapter.startLoading();
                }
            });
        }
    }

    @Override
    public void onLoadingFinished() {
        if (DEBUG) {
            Log.v(TAG, "onLoadingFinished");
        }
        // Let's run a tiny delay for better UI
        updateLoadResult();
    }

    public void updateLoadResult() {
        if (mRecyclerAdapter != null) {
            mRecyclerAdapter.updateData(mDataLoader.getData());
        }
        mLoading = false;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.fragment_main, null);

        mRecyclerAdapter = new RecyclerAdapter(getContext());
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    if (!mLoading) {
                        if ((mLinearLayoutManager.getChildCount() + mLinearLayoutManager.findFirstVisibleItemPosition()) >= mLinearLayoutManager.getItemCount()) {
                            mLoading = true;
                            loadMoreData();
                        }
                    }
                }
            }
        });

        if (mDataLoader != null) {
            mRecyclerAdapter.updateData(mDataLoader.getData());
        }

        return view;
    }
}
