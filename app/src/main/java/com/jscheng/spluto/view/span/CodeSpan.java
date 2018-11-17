package com.jscheng.spluto.view.span;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;

import com.jscheng.spluto.view.Span;

/**
 * Created by chengjunsen on 2018/11/15.
 */
public class CodeSpan extends Span {
    private String value;
    private TextPaint paint;

    public CodeSpan(String value) {
        this.value = value;
        this.paint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String getText() {
        return value;
    }

    @Override
    public void measure(int defaultWidth, int defaultHeight) {
        loadPaint();
    }

    private void loadPaint() {

    }

    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public void draw(Canvas canvas, int x, int y, int start, int end) {

    }
}
