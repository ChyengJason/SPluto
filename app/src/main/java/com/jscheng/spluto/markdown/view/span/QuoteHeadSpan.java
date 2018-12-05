package com.jscheng.spluto.markdown.view.span;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.jscheng.spluto.markdown.view.Span;
import com.jscheng.spluto.markdown.view.resource.ColorResource;
import com.jscheng.spluto.markdown.view.resource.PaddingResouce;

/**
 * Created By Chengjunsen on 2018/11/22
 */
public class QuoteHeadSpan extends Span {
    private Paint mHeadPaint;

    public QuoteHeadSpan() {
        super(SpanType.HEAD_SPAN);
        this.mHeadPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.mFontColor = ColorResource.getQuoteFontColor();
        this.mHeadPaint.setColor(ColorResource.getQuoteHeadColor());
    }

    @Override
    public void measure(int maxWidth, int maxHeight) {
        setWidth(PaddingResouce.getQuoteListHeadWidth());
        setHeight(maxHeight);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.save();
        canvas.translate(getX(), getY());
        Rect rect = new Rect(0, 0, getWidth(), getHeight());
        canvas.drawRect(rect, mHeadPaint);
        canvas.restore();
    }

    @Override
    public void layout(int left, int top, int right, int bottom) {
        setX(left);
        setY(top);
    }
}
