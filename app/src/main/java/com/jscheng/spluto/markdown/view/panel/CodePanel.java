package com.jscheng.spluto.markdown.view.panel;


import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.jscheng.spluto.markdown.view.Panel;
import com.jscheng.spluto.markdown.view.part.Part;
import com.jscheng.spluto.markdown.view.resource.ColorResource;
import com.jscheng.spluto.markdown.view.resource.FontResource;
import com.jscheng.spluto.markdown.view.resource.PaddingResouce;
import com.jscheng.spluto.markdown.view.span.TextSpan;

import java.util.Arrays;
import java.util.List;

/**
 * Created by chengjunsen on 2018/11/15.
 */
public class CodePanel extends Panel {
    private static final String TAG = "CJS";
    private Part mPart;
    private Paint mBackgroundPaint;
    private TextSpan mSpan;

    public CodePanel() {
        this.mSpan = new TextSpan();
        mBackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBackgroundPaint.setColor(ColorResource.getCodePanelBackgroundColor());
    }

    @Override
    public void setParts(List<Part> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        this.mPart = list.get(0);
        this.mSpan.setFontColor(ColorResource.getCodePannelFontColor());
        this.mSpan.setFontSize(FontResource.getCodePanelFontSize());
        this.mSpan.addPart(mPart);
    }

    @Override
    public List<Part> getParts() {
        return Arrays.asList(mPart);
    }

    @Override
    public void measure(int defaultWidth, int defaultHeight) {
        int width = defaultWidth - 2 * PaddingResouce.getCodePanelLeftRightPadding();
        mSpan.measure(width, defaultHeight);
        int height = mSpan.getHeight() + 2 * PaddingResouce.getPannelSpacingPx() +  PaddingResouce.getCodePanelTopBottomPadding();
        Log.e(TAG, "code measure:  " +  mSpan.getHeight());
        setWidth(defaultWidth);
        setHeight(height);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.save();
        canvas.translate(getX(), getY() + PaddingResouce.getPannelSpacingPx());
        Rect backgroundRect = new Rect(0, 0, getWidth(), mSpan.getHeight() + PaddingResouce.getCodePanelTopBottomPadding());
        canvas.drawRect(backgroundRect, mBackgroundPaint);
        canvas.restore();
        mSpan.draw(canvas);
    }

    @Override
    public void layout(int left, int top, int right, int bottom) {
        int x = left + PaddingResouce.getCodePanelLeftRightPadding();
        int y = top + PaddingResouce.getPannelSpacingPx() + PaddingResouce.getCodePanelTopBottomPadding();
        mSpan.layout(x, y, right, bottom);
        setX(left);
        setY(top);
    }

    @Override
    public Part getPart(float x, float y) {
        if (x >= getX() && x <= getX() + getWidth() && y >= getY() && y <= getY() + getHeight()) {
            return mPart;
        }
        return null;
    }
}
