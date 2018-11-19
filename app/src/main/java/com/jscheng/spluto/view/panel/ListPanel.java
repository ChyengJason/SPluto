package com.jscheng.spluto.view.panel;
import android.graphics.Canvas;
import android.graphics.Color;

import com.jscheng.spluto.view.Panel;
import com.jscheng.spluto.view.Span;
import com.jscheng.spluto.view.panel.LineInnerPanel.LineInnerPanel;
import com.jscheng.spluto.view.panel.LineInnerPanel.PictureLineInnerPanel;
import com.jscheng.spluto.view.panel.LineInnerPanel.TextLineInnerPanel;
import com.jscheng.spluto.view.resource.ColorResource;
import com.jscheng.spluto.view.resource.PaddingResouce;
import com.jscheng.spluto.view.span.ImageSpan;
import com.jscheng.spluto.view.span.SpanType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengjunsen on 2018/11/15.
 */
public abstract class ListPanel extends Panel {
    protected List<Span> mSpans;
    protected List<LineInnerPanel> mInnerPanels;
    protected int backgroudColor;

    public ListPanel() {
        this.mSpans = new ArrayList<>();
        this.mInnerPanels = new ArrayList<>();
        this.backgroudColor = ColorResource.getDefaultBackgroundColor();
    }

    public void addSpan(Span span) {
        this.mSpans.add(span);
        mSpans.add(span);
        if (span.getSpanType() != SpanType.SPAN_IMAGE) {
            if (mInnerPanels.isEmpty() || mInnerPanels.get(mInnerPanels.size() - 1).getmPanelType() != LineInnerPanel.LineInnerPanelType.TEXT_INNER_PANEL) {
                mInnerPanels.add(new TextLineInnerPanel());
            }
            TextLineInnerPanel textInnerPanel = (TextLineInnerPanel) mInnerPanels.get(mInnerPanels.size() - 1);
            textInnerPanel.setBackGroundColor(backgroudColor);
            textInnerPanel.setLevel(getLevel());
            textInnerPanel.addTextSpan(span);
        } else {
            ImageSpan imageSpan = (ImageSpan) span;
            PictureLineInnerPanel pictureInnerPanel = new PictureLineInnerPanel(imageSpan.getUrl(), imageSpan.getDescripe());
            pictureInnerPanel.setBackGroundColor(backgroudColor);
            pictureInnerPanel.setLevel(getLevel());
            mInnerPanels.add(pictureInnerPanel);
        }
    }

    @Override
    public void measure(int defaultWidth, int defaultHeight) {
        int width = (int)(defaultWidth - getHeadWidth() - PaddingResouce.getListMiddleSpacingPx());
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
        int x = (int)(left + getHeadWidth() + PaddingResouce.getListMiddleSpacingPx());
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
        drawHead(canvas);
        for (int i = 0; i < mInnerPanels.size(); i++) {
            LineInnerPanel innerPanel = mInnerPanels.get(i);
            innerPanel.draw(canvas);
        }
    }

    protected abstract void drawHead(Canvas canvas);

    protected abstract int getHeadWidth();
}
