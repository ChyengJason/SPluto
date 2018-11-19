package com.jscheng.spluto.view.panel;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.jscheng.spluto.view.resource.ColorResource;
import com.jscheng.spluto.view.resource.FontResouce;
import com.jscheng.spluto.view.resource.PaddingResouce;

/**
 * Created by chengjunsen on 2018/11/15.
 */
public class QuotePanel extends ListPanel {
    private Paint mHeadPaint;
    private int mHeadWidth;

    public QuotePanel() {
        this.mHeadPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.backgroudColor = ColorResource.getQuoteBackgroudColor();
        loadQuoteHead();
    }

    private void loadQuoteHead() {
        mHeadPaint.setColor(ColorResource.getQuoteHeadColor());
        mHeadWidth = PaddingResouce.getQuoteListHeadWidth();
    }

    @Override
    protected void drawHead(Canvas canvas) {
        canvas.save();
        canvas.translate(getX(), getY());
        Rect rect = new Rect(0, 0, mHeadWidth, getHeight());
        canvas.drawRect(rect, mHeadPaint);
        canvas.restore();

    }

    @Override
    protected int getHeadWidth() {
        return mHeadWidth;
    }
}
