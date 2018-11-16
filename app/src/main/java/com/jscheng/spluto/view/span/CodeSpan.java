package com.jscheng.spluto.view.span;

import android.graphics.Canvas;

import com.jscheng.spluto.view.Span;

/**
 * Created by chengjunsen on 2018/11/15.
 */
public class CodeSpan extends Span {
    private String value;

    public CodeSpan(String value) {
        this.value = value;
    }

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
    public void draw(Canvas canvas, int x, int y, int start, int end) {

    }
}
