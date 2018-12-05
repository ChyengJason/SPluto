package com.jscheng.spluto.markdown.view.span;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;

import com.jscheng.spluto.markdown.view.Span;
import com.jscheng.spluto.markdown.view.resource.ColorResource;
import com.jscheng.spluto.markdown.view.resource.FontResource;

/**
 * Created By Chengjunsen on 2018/11/22
 */
public class OrderHeadSpan extends Span {
    private TextPaint mTextPaint;
    private StaticLayout mHeadLayout;
    private int mHeadWidth;
    private String mHeadText;

    public OrderHeadSpan(int orderNum) {
        super(SpanType.HEAD_SPAN);
        this.mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        this.mHeadText = orderNum + ".";
        this.mTextPaint.setTextSize(FontResource.getFontSize(0));
        this.mTextPaint.setColor(ColorResource.getTextFontColor());
        this.mHeadWidth = (int)mTextPaint.measureText(mHeadText);
        this.mHeadLayout = new StaticLayout(mHeadText, mTextPaint, mHeadWidth, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0f, false);
    }

    @Override
    public void measure(int maxWidth, int maxHeight) {
        setWidth(mHeadLayout.getWidth());
        setHeight(mHeadLayout.getHeight());
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
