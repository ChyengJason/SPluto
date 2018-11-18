package com.jscheng.spluto.view.span;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.text.SpannableStringBuilder;

import com.jscheng.spluto.view.Span;

/**
 * Created by chengjunsen on 2018/11/15.
 */
public class ImageSpan extends Span {
    private String url;
    private String descripe;

    public ImageSpan(String url, String descripe) {
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

    @Override
    public void setSpannable(SpannableStringBuilder spanBuilder) {

    }
}
