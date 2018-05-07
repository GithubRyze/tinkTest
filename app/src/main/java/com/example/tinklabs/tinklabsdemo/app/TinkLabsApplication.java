package com.example.tinklabs.tinklabsdemo.app;

import android.app.Application;

import com.example.tinklabs.tinklabsdemo.http.DataManager;


/**
 * Created by ryzeliu on 2018/5/5.
 */

public class TinkLabsApplication extends Application {
    private static DataManager mDataManager = new DataManager();
    private static TinkLabsApplication tinkLabsApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        tinkLabsApplication = this;
    }
    public static DataManager getDataManager(){

        return mDataManager;
    }

    public static TinkLabsApplication getInstance(){
        return tinkLabsApplication;
    }
}
