package com.jscheng.spluto.view.span;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.util.Log;

import com.jscheng.spluto.view.Span;
import com.jscheng.spluto.view.resource.FontResouce;

/**
 * Created by chengjunsen on 2018/11/15.
 */
public class LinkSpan extends Span {
    private static final String TAG = "CJS";
    private String url;
    private String descripe;

    public LinkSpan(String url, String descripe) {
        super(SpanType.SPAN_LINK);
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
