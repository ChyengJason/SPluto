package com.jscheng.spluto.view;

import android.graphics.Canvas;

/**
 * Created by chengjunsen on 2018/11/15.
 */
public abstract class Span {
    private boolean bold;
    private boolean italic;
    private boolean strike;
    private int fontLevel;
    private int width;
    private int height;

    public void setBold(boolean bold) {
        this.bold = bold;
    }

    public void setItalic(boolean italic) {
        this.italic = italic;
    }

    public void setStrike(boolean strike) {
        this.strike = strike;
    }

    public void setFontLevel(int fontLevel) {
        this.fontLevel = fontLevel;
    }

    public boolean isBold() {
        return bold;
    }

    public boolean isItalic() {
        return italic;
    }

    public boolean isStrike() {
        return strike;
    }

    public int getFontLevel() {
        return fontLevel;
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

    public abstract void measure(int defaultWidth, int defaultHeight);

    public abstract void draw(Canvas canvas);

    public abstract void draw(Canvas canvas, int x, int y, int start, int end);
}
