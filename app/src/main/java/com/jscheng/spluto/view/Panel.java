package com.jscheng.spluto.view;

import android.graphics.Canvas;

import com.jscheng.spluto.view.part.Part;

import java.util.List;

/**
 * Created by chengjunsen on 2018/11/15.
 */
public abstract class Panel {
    private int width;
    private int height;
    private int x, y;

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

    public abstract void setParts(List<Part> list);

    public abstract List<Part> getParts();

    public abstract void measure(int maxWidth, int maxHeight);

    public abstract void draw(Canvas canvas);

    public abstract void layout(int left, int top, int right, int bottom);

    public abstract Part getPart(float x, float y);
}
