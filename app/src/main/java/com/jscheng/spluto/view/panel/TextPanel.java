package com.jscheng.spluto.view.panel;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Log;

import com.jscheng.spluto.view.Panel;
import com.jscheng.spluto.view.Span;
import com.jscheng.spluto.view.resource.FontResouce;
import com.jscheng.spluto.view.resource.PaddingResouce;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengjunsen on 2018/11/15.
 */
public class TextPanel extends Panel {
    private static final String TAG = "CJS";
    private List<Span> mSpans;
    private StaticLayout mStaticLayout;
    private SpannableStringBuilder mSpanBuilder;
    private TextPaint mTextPaint;

    public TextPanel() {
        mSpans = new ArrayList<>();
        mSpanBuilder = new SpannableStringBuilder();
        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
    }

    public void setSpans(List<Span> spans) {
        mSpans.clear();
        for (Span span: spans) {
            span.setSpannable(mSpanBuilder);
            mSpans.add(span);
        }
    }

    @Override
    public void measure(int defaultWidth, int defaultHeight) {
        mStaticLayout = new StaticLayout(mSpanBuilder, mTextPaint, defaultWidth, Layout.Alignment.ALIGN_NORMAL, 1.0f, PaddingResouce.getLineSpacingPx(), false);
        setWidth(defaultWidth);
        setHeight(mStaticLayout.getHeight());
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.save();
        canvas.translate(getX(), getY());
        mStaticLayout.draw(canvas);
        canvas.restore();
    }

    @Override
    public void layout(int left, int top, int right, int bottom) {
        setX(left);
        setY(top);
    }

}
