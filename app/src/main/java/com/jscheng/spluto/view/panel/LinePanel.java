package com.jscheng.spluto.view.panel;

import android.graphics.Bitmap;
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
import com.jscheng.spluto.view.span.ImageSpan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengjunsen on 2018/11/15.
 */
public class LinePanel extends Panel {
    private static final String TAG = "CJS";
    private List<Span> mSpans;
    private StaticLayout mStaticLayout;
    private SpannableStringBuilder mSpanBuilder;
    private TextPaint mTextPaint;

    public LinePanel() {
        mSpans = new ArrayList<>();
        mSpanBuilder = new SpannableStringBuilder();
        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
    }

    public void addSpan(Span span) {
        mSpans.add(span);
        span.setSpannable(mSpanBuilder);
    }

    @Override
    public void measure(int defaultWidth, int defaultHeight) {
        mStaticLayout = new StaticLayout(mSpanBuilder, mTextPaint, defaultWidth, Layout.Alignment.ALIGN_NORMAL, 1.0f, PaddingResouce.getLineSpacingPx(), false);
        int width = defaultWidth;
        int height = (int)(mStaticLayout.getHeight() + 2 * PaddingResouce.getPannelSpacingPx());
        setWidth(width);
        setHeight(height);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.save();
        canvas.translate(getX(), getY() + PaddingResouce.getPannelSpacingPx());
        mStaticLayout.draw(canvas);
        canvas.restore();
    }

    @Override
    public void layout(int left, int top, int right, int bottom) {
        setX(left);
        setY(top);
    }
}
