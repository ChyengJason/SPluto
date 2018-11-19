package com.jscheng.spluto.view.panel;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;

import com.jscheng.spluto.view.Span;
import com.jscheng.spluto.view.panel.LineInnerPanel.LineInnerPanel;
import com.jscheng.spluto.view.resource.ColorResource;
import com.jscheng.spluto.view.resource.FontResouce;
import com.jscheng.spluto.view.resource.PaddingResouce;
import com.jscheng.spluto.view.span.TextSpan;

/**
 * Created by chengjunsen on 2018/11/15.
 */
public class UnorderListPanel extends ListPanel {
    private TextPaint mTextPaint;
    private StaticLayout mHeadLayout;
    private int mHeadWidth;
    private String mHeadText;

    public UnorderListPanel() {
        this.mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        this.mHeadText = "• ";
        loadUnOrderHead();
    }

    private void loadUnOrderHead() {
        mTextPaint.setTextSize(FontResouce.getFontSize(0));
        mTextPaint.setColor(ColorResource.getTextFontColor());
        mHeadWidth = (int)mTextPaint.measureText(mHeadText);
        mHeadLayout = new StaticLayout(mHeadText, mTextPaint, mHeadWidth, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0f, false);
    }

    @Override
    protected void drawHead(Canvas canvas) {
        canvas.save();
        canvas.translate(getX(), getY() + (int)PaddingResouce.getPannelSpacingPx());
        mHeadLayout.draw(canvas);
        canvas.restore();
    }

    @Override
    protected int getHeadWidth() {
        return mHeadWidth;
    }
}
