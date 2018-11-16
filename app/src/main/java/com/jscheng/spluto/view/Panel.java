package com.jscheng.spluto.view;

/**
 * Created by chengjunsen on 2018/11/15.
 */
public abstract class Panel {
    private int level;
    private int width;
    private int height;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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

    public abstract void measure(int maxWidth, int maxHeight);
}
