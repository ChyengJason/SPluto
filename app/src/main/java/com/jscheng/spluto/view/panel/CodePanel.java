package com.jscheng.spluto.view.panel;


import android.graphics.Canvas;

import com.jscheng.spluto.view.Panel;

/**
 * Created by chengjunsen on 2018/11/15.
 */
public class CodePanel extends Panel {
    private String value;

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public void measure(int defaultWidth, int defaultHeight) {

    }

    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public void layout(int left, int top, int right, int bottom) {

    }
}
