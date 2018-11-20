package com.jscheng.spluto.view.part;

import com.jscheng.spluto.view.Part;

/**
 * Created by chengjunsen on 2018/11/15.
 */
public class ImagePart extends Part {
    private String url;
    private String descripe;

    public ImagePart(String url, String descripe) {
        super(PartType.PART_IMAGE);
        this.url = url;
        this.descripe = descripe;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setDescripe(String descripe) {
        this.descripe = descripe;
    }

    public String getDescripe() {
        return descripe;
    }

    @Override
    public String getText() {
        return descripe;
    }
}
