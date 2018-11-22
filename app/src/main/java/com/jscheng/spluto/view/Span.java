package com.jscheng.spluto.view;

import android.graphics.Canvas;

import com.jscheng.spluto.view.span.SpanType;

public abstract class Span {
    private int width;
    private int height;
    private int x, y;

    private SpanType mPanelType;

    public Span(SpanType mPanelType) {
        this.mPanelType = mPanelType;
    }

    public SpanType getmPanelType() {
        return mPanelType;
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

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public abstract void measure(int maxWidth, int maxHeight);

    public abstract void draw(Canvas canvas);

    public abstract void layout(int left, int top, int right, int bottom);
}
