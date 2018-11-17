package com.jscheng.spluto.view.span;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
    private TextPaint paint;

    public LinkSpan(String url, String descripe) {
        this.url = url;
        this.descripe = descripe;
        this.paint = new TextPaint();
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
    public void measure(int defaultWidth, int defaultHeight) {
        loadPaint();
    }


    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public void draw(Canvas canvas, int x, int y, int start, int end) {

    }

    private void loadPaint() {
        int fontSize = FontResouce.getFontSize(getFontLevel());
        Log.d(TAG, "LinkSpan: fontLevel: " + getFontLevel() + " fontsize: " + fontSize);
        paint.setTextSize(fontSize);
        paint.setLetterSpacing(0.0f);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        if (isBold() || getFontLevel() > 0) {
            paint.setFakeBoldText(true);
        }
        if (isItalic()) {
            paint.setTextSkewX(-1);
        }
        if (isStrike()) {
            paint.setStrikeThruText(true);
        }
    }
}
