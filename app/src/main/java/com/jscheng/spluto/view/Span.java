package com.jscheng.spluto.view;

import android.graphics.Canvas;

import com.jscheng.spluto.view.part.Part;
import com.jscheng.spluto.view.resource.ColorResource;
import com.jscheng.spluto.view.resource.FontResource;
import com.jscheng.spluto.view.span.SpanType;

public abstract class Span {
    protected int width;
    protected int height;
    protected int x, y;
    protected SpanType mSpanType;
    protected int mBackGroundColor;
    protected int mFontColor;
    protected int mFontSize;
    protected boolean isSetFontSize;
    protected boolean isBold;
    protected boolean isItalic;
    protected boolean isStrike;
    protected boolean isUnderLine;

    public Span(SpanType spanType) {
        this.mSpanType = spanType;
        this.mBackGroundColor = ColorResource.getDefaultBackgroundColor();
        this.mFontColor = ColorResource.getTextFontColor();
        this.mFontSize = FontResource.getFontSize(0);
        this.isBold = false;
        this.isItalic = false;
        this.isStrike = false;
        this.isUnderLine = false;
        this.isSetFontSize = false;
    }

    public SpanType getSpanType() {
        return mSpanType;
    }

    public void setFontColor(int mFontColor){
            this.mFontColor = mFontColor;
    }

    public void setBold(boolean bold) {
        isBold = bold;
    }

    public void setItalic(boolean italic) {
        isItalic = italic;
    }

    public void setStrike(boolean strike) {
        isStrike = strike;
    }

    public void setUnderLine(boolean underLine) {
        isUnderLine = underLine;
    }

    public void setBackGroundColor(int mBackGroundColor) {
        this.mBackGroundColor = mBackGroundColor;
    }

    public void setFontSize(int mFontSize) {
        this.mFontSize = mFontSize;
        this.isSetFontSize = true;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public abstract void measure(int maxWidth, int maxHeight);

    public abstract void draw(Canvas canvas);

    public abstract void layout(int left, int top, int right, int bottom);
}
