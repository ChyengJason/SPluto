package com.jscheng.spluto.view.span;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;

import com.jscheng.spluto.view.Span;
import com.jscheng.spluto.view.resource.ColorResource;
import com.jscheng.spluto.view.resource.FontResource;

/**
 * Created By Chengjunsen on 2018/11/22
 */
public class UnorderHeadSpan extends Span {
    private TextPaint mTextPaint;
    private StaticLayout mHeadLayout;
    private int mHeadWidth;
    private String mHeadText;

    public UnorderHeadSpan() {
        super(SpanType.HEAD_SPAN);
        this.mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        this.mHeadText = "• ";
        loadUnOrderHead();
    }

    private void loadUnOrderHead() {
        mTextPaint.setTextSize(FontResource.getFontSize(0));
        mTextPaint.setColor(ColorResource.getTextFontColor());
        mHeadWidth = (int)mTextPaint.measureText(mHeadText);
        mHeadLayout = new StaticLayout(mHeadText, mTextPaint, mHeadWidth, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0f, false);
    }

    @Override
    public void measure(int maxWidth, int maxHeight) {
        setWidth(mHeadLayout.getWidth());
        setHeight(maxHeight);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.save();
        canvas.translate(getX(), getY());
        mHeadLayout.draw(canvas);
        canvas.restore();
    }

    @Override
    public void layout(int left, int top, int right, int bottom) {
        setX(left);
        setY(top);
    }
}
