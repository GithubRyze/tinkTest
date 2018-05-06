package com.example.tinklabs.tinklabsdemo.app;

import android.app.Application;

import com.example.tinklabs.tinklabsdemo.http.DataManager;

import java.lang.ref.WeakReference;


/**
 * Created by ryzeliu on 2018/5/5.
 */

public class TinkLabsApplication extends Application {
    private static DataManager mDataManager = new DataManager();
    @Override
    public void onCreate() {
        super.onCreate();
    }
    public static DataManager getDataManager(){

        return mDataManager;
    }
}
