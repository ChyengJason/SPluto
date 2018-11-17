package com.jscheng.spluto.view.panel;

import android.graphics.Canvas;
import android.util.Log;

import com.jscheng.spluto.view.Panel;
import com.jscheng.spluto.view.Span;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengjunsen on 2018/11/15.
 */
public class TextPanel extends Panel {
    private static final String TAG = "CJS";
    private List<Span> mSpans;
    private int mLineNum;

    public TextPanel() {
        mSpans = new ArrayList<>();
        mLineNum = 0;
    }

    public void setSpans(List<Span> spans) {
        mSpans.clear();
        mSpans.addAll(spans);
    }

    @Override
    public void measure(int defaultWidth, int defaultHeight) {
        int width = 0;
        int height = 0;
        for (Span span : mSpans) {
            span.measure(defaultWidth, defaultHeight);
            width += span.getWidth();
            height = Math.max(height, span.getHeight());
        }
        mLineNum = measureLineNum(defaultWidth, width);
        height = height * mLineNum;
        width = defaultWidth;
        this.setWidth(width);
        this.setHeight(height);
        Log.e(TAG, "measure: " + width + "x" + height + " linenum: " + mLineNum);
    }

    @Override
    public void draw(Canvas canvas) {
        for (Span span: mSpans) {
            span.draw(canvas, getX(), getY(), 0, span.getText().length());
        }
    }

    @Override
    public void layout(int left, int top, int right, int bottom) {
        int x = left;
        int y = top;

        this.setX(x);
        this.setY(y);
    }

    private int measureLineNum(int lineWidth, int width) {
        int num = width / lineWidth;
        if (lineWidth % width != 0) {
            num += 1;
        }
        return num;
    }
}
