package com.jscheng.spluto.view.panel;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;

import com.jscheng.spluto.view.Span;
import com.jscheng.spluto.view.panel.LineInnerPanel.LineInnerPanel;
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
        mTextPaint.setColor(FontResouce.getTextFontColor());
        mHeadWidth = (int)mTextPaint.measureText(mHeadText);
        mHeadLayout = new StaticLayout(mHeadText, mTextPaint, mHeadWidth, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0f, false);
    }

    @Override
    public void measure(int defaultWidth, int defaultHeight) {
        int width = (int)(defaultWidth - mHeadWidth - PaddingResouce.getListMiddleSpacingPx());
        int height = 0;
        for (int i = 0; i < mInnerPanels.size(); i++) {
            LineInnerPanel innerPanel = mInnerPanels.get(i);
            innerPanel.measure(defaultWidth, width);
            height += innerPanel.getHeight();
        }
        setWidth(defaultWidth);
        setHeight(height);
    }

    @Override
    public void layout(int left, int top, int right, int bottom) {
        int x = (int)(left + mHeadWidth + PaddingResouce.getListMiddleSpacingPx());
        int y = top;
        for (int i = 0; i < mInnerPanels.size(); i++) {
            LineInnerPanel innerPanel = mInnerPanels.get(i);
            innerPanel.layout(x, y, right, bottom);
            y += innerPanel.getHeight();
        }
        setX(left);
        setY(top);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.save();
        canvas.translate(getX(), getY() + (int)PaddingResouce.getPannelSpacingPx());
        mHeadLayout.draw(canvas);
        canvas.restore();

        for (int i = 0; i < mInnerPanels.size(); i++) {
            LineInnerPanel innerPanel = mInnerPanels.get(i);
            innerPanel.draw(canvas);
        }
    }
}
