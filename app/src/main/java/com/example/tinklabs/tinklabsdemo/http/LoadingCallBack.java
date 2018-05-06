package com.example.tinklabs.tinklabsdemo.http;

import com.example.tinklabs.tinklabsdemo.bean.ImageBean;

import java.util.List;

/**
 * Created by ryzeliu on 2018/5/5.
 */

public interface LoadingCallBack {
    void onLoadingStart();
    void onLoadingFinished();
}
