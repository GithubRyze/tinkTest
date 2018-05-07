package com.example.tinklabs.tinklabsdemo.http;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import com.example.tinklabs.tinklabsdemo.app.TinkLabsApplication;
import com.example.tinklabs.tinklabsdemo.bean.ImageBean;
import com.example.tinklabs.tinklabsdemo.utils.Constant;
import com.example.tinklabs.tinklabsdemo.utils.DataType;
import com.example.tinklabs.tinklabsdemo.utils.LogUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by ryzeliu on 2018/5/6.
 */

public class DataLoader implements LoadingTask.CallBack{

    private static final String TAG = "DataLoader";



    private final WeakReference<Context> mContext;
    private DataType dataType;
    private final WeakReference<DataManager> mDataManager;
    private  WeakReference<LoadingCallBack> mLoadingCallBack;

    public void registerCallBack(LoadingCallBack loadingCallBack){
        mLoadingCallBack = new WeakReference<LoadingCallBack>(loadingCallBack);
    }

    public DataLoader(@NonNull Context context, @NonNull DataType dataType) {
        this.mContext = new WeakReference<>(context);
        this.mDataManager = new WeakReference<DataManager>(TinkLabsApplication.getDataManager());
        this.dataType = dataType;
    }

    private void executeLoadDataTask(Context context) {
        if (context == null) {
            return;
        }
        String fileName = getFileName(dataType);
        if(fileName == null){
            return;
        }
        new LoadingTask(context, this).
                executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getFileName(dataType));
        LoadingCallBack callBack = mLoadingCallBack.get();
        if (callBack != null){
            callBack.onLoadingStart();
        }
    }

    public void load() {
        executeLoadDataTask(mContext.get());
    }

    private String getFileName(DataType dataType){
        String fileName = null;
        switch (dataType){
            case DATA_TYPE_GUIDE:
                fileName = Constant.FILE_NAME_GUIDE;
                break;
            case DATA_TYPE_SHOP:
                fileName = Constant.FILE_NAME_SHOP;
                break;
            case DATA_TYPE_EAT:
                fileName = Constant.FILE_NAME_EAT;
                break;
            default:
                throw new UnsupportedOperationException();

        }
        return fileName;
    }

    @Override
    public void onLoadingFinished(List<ImageBean> list) {

        LoadingCallBack callback = mLoadingCallBack.get();
        if (callback == null) {
            LogUtils.e(TAG, "loadingcallback is null");
            return;
        }
        LogUtils.e(TAG,list.size() + dataType.toString());
        DataManager dataManager = mDataManager.get();
        if (dataManager != null) {
            dataManager.addData(dataType, list);
            callback.onLoadingFinished();
        }

    }


    @NonNull
    public List<ImageBean> getData() {
        DataManager dataManager = mDataManager.get();
        if (dataManager != null) {
            List<ImageBean> list = dataManager.getItems(this.dataType);
            return list;
        } else {
            return new ArrayList<>();
        }
    }
}
