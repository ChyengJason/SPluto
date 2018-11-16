package com.jscheng.spluto.view.panel;

import com.jscheng.spluto.view.Panel;
import com.jscheng.spluto.view.Span;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengjunsen on 2018/11/15.
 */
public class TextPanel extends Panel {
    private List<Span> mSpans;

    public TextPanel() {
        mSpans = new ArrayList<>();
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
        int lineNum = measureLineNum(defaultWidth, width);
        height = height * lineNum;
        width = defaultWidth;
        setWidth(width);
        setHeight(height);
    }

    private int measureLineNum(int maxWidth, int width) {
        int num = maxWidth / width;
        if (maxWidth % width != 0) {
            num += 1;
        }
        return num;
    }
}
