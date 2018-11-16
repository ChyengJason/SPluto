package com.jscheng.spluto.view.span;

import com.jscheng.spluto.view.Span;

/**
 * Created by chengjunsen on 2018/11/15.
 */
public class LinkSpan extends Span {
    private String url;
    private String descripe;

    public LinkSpan(String url, String descripe) {
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
    public void measure(int defaultWidth, int defaultHeight) {

    }
}
