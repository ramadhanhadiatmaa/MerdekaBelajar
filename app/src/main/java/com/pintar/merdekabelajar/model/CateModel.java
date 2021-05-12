package com.pintar.merdekabelajar.model;

public class CateModel {

    private String imageurl;
    private String tag;

    public CateModel(String imageurl, String tag) {
        this.imageurl = imageurl;
        this.tag = tag;
    }

    public CateModel() {
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
