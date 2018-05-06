package com.example.tinklabs.tinklabsdemo.bean;

/**
 * Created by ryzeliu on 2018/5/5.
 */

public class ImageDescriptionBean extends ImageBean{

    private String imageTitle;
    private String imageDescription;


    public ImageDescriptionBean(String imageUrl, String imageTitle, String imageDescription) {
        super(imageUrl);
        this.imageTitle = imageTitle;
        this.imageDescription = imageDescription;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }

    public String getImageDescription() {
        return imageDescription;
    }

    public void setImageDescription(String imageDescription) {
        this.imageDescription = imageDescription;

    }


}
