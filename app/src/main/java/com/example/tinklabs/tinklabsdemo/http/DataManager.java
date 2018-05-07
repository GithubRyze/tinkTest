package com.example.tinklabs.tinklabsdemo.http;

import android.support.annotation.NonNull;

import com.example.tinklabs.tinklabsdemo.bean.ImageBean;
import com.example.tinklabs.tinklabsdemo.utils.DataType;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by ryzeliu on 2018/5/6.
 */

public class DataManager {

    private final ConcurrentHashMap<DataType,List<ImageBean>> concurrentHashMap
            = new ConcurrentHashMap<>();
    /**
     * save data into ConcurrentHashMap
     */
    public void addData(@NonNull DataType dataType, @NonNull List<ImageBean> items) {

            List<ImageBean> data = concurrentHashMap.get(dataType);
            if (data == null){
                List<ImageBean> list = new ArrayList<>();
                list.addAll(items);
                concurrentHashMap.put(dataType,list);
            }else {
                data.addAll(items);
                concurrentHashMap.put(dataType,data);
            }

    }

    /**
     * get data with type from ConcurrentHashMap
     */
    @NonNull
    public List<ImageBean> getItems(DataType dataType) {

        List<ImageBean> list =  concurrentHashMap.get(dataType);
        return list == null ? new ArrayList<ImageBean>() : list;

    }
}
