package com.jscheng.spluto.view.panel;


import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;

import com.jscheng.spluto.view.Panel;
import com.jscheng.spluto.view.Span;
import com.jscheng.spluto.view.part.Part;
import com.jscheng.spluto.view.resource.ColorResource;
import com.jscheng.spluto.view.resource.FontResouce;
import com.jscheng.spluto.view.resource.PaddingResouce;

import java.util.List;

/**
 * Created by chengjunsen on 2018/11/15.
 */
public class CodePanel extends Panel {
    private List<Part> mParts;
    private StaticLayout mStaticLayout;
    private TextPaint mTextPaint;
    private Paint mBackgroundPaint;
    private String mCodeText;

    public CodePanel() {
        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mBackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(ColorResource.getCodePannelFontColor());
        mTextPaint.setTextSize(FontResouce.getCodePanelFontSize());
        mBackgroundPaint.setColor(ColorResource.getCodePanelBackgroundColor());
    }

    @Override
    public void setParts(List<Part> list) {
        this.mParts = list;
        this.mCodeText = !mParts.isEmpty() ? mParts.get(0).getValue() : "";
    }

    @Override
    public void measure(int defaultWidth, int defaultHeight) {
        int width = defaultWidth - 2 * PaddingResouce.getCodePanelLeftRightPadding();
        mStaticLayout = new StaticLayout(mCodeText, mTextPaint, width, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0, false);
        int height = mStaticLayout.getHeight() + 2 * PaddingResouce.getPannelSpacingPx();
        setWidth(defaultWidth);
        setHeight(height);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.save();
        canvas.translate(getX(), getY() + PaddingResouce.getPannelSpacingPx());
        Rect backgroundRect = new Rect(0, 0, getWidth(), getHeight() - PaddingResouce.getPannelSpacingPx());
        canvas.drawRect(backgroundRect, mBackgroundPaint);

        canvas.translate(PaddingResouce.getCodePanelLeftRightPadding(), PaddingResouce.getCodePanelTopBottomPadding());
        mStaticLayout.draw(canvas);
        canvas.restore();
    }

    @Override
    public void layout(int left, int top, int right, int bottom) {
        setX(left);
        setY(top);
    }
}
