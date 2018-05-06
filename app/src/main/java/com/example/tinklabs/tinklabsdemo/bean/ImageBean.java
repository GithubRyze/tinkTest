package com.example.tinklabs.tinklabsdemo.bean;

import java.util.UUID;

/**
 * Created by ryzeliu on 2018/5/5.
 */

public class ImageBean {

    private String imageUrl;
    private final String mUUID;

    public ImageBean(String imageUrl) {
        this.imageUrl = imageUrl;
        this.mUUID = UUID.randomUUID().toString();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUUID(){ return mUUID; }
}
