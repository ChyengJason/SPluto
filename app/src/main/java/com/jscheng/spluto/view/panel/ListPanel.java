package com.jscheng.spluto.view.panel;
import android.graphics.Canvas;

import com.jscheng.spluto.view.Panel;
import com.jscheng.spluto.view.part.Part;
import com.jscheng.spluto.view.Span;
import com.jscheng.spluto.view.span.PictureSpan;
import com.jscheng.spluto.view.span.SpanType;
import com.jscheng.spluto.view.span.TextSpan;
import com.jscheng.spluto.view.resource.ColorResource;
import com.jscheng.spluto.view.resource.PaddingResouce;
import com.jscheng.spluto.view.part.PartType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengjunsen on 2018/11/15.
 */
public abstract class ListPanel extends Panel {
    protected List<Part> mParts;
    protected List<Span> mInnerPanels;
    protected int mBackgroudColor;
    protected int mFontColor;

    public ListPanel() {
        this.mParts = new ArrayList<>();
        this.mInnerPanels = new ArrayList<>();
        this.mBackgroudColor = ColorResource.getDefaultBackgroundColor();
        this.mFontColor = ColorResource.getTextFontColor();
    }

    public void addPart(Part part) {
        this.mParts.add(part);
        mParts.add(part);
        if (part.getPartType() != PartType.PART_IMAGE) {
            if (mInnerPanels.isEmpty() || mInnerPanels.get(mInnerPanels.size() - 1).getmPanelType() != SpanType.TEXT_INNER_PANEL) {
                mInnerPanels.add(new TextSpan());
            }
            TextSpan textInnerPanel = (TextSpan) mInnerPanels.get(mInnerPanels.size() - 1);
            textInnerPanel.setBackGroundColor(mBackgroudColor);
            textInnerPanel.setFontColor(mFontColor);
            textInnerPanel.addTextPart(part);
        } else {
            PictureSpan pictureInnerPanel = new PictureSpan(part.getUrl(), part.getDescripe());
            mInnerPanels.add(pictureInnerPanel);
        }
    }

    @Override
    public void measure(int defaultWidth, int defaultHeight) {
        int width = defaultWidth - getHeadWidth() - PaddingResouce.getListMiddleSpacingPx();
        int height = 0;
        for (int i = 0; i < mInnerPanels.size(); i++) {
            Span innerPanel = mInnerPanels.get(i);
            innerPanel.measure(width, defaultHeight);
            height += innerPanel.getHeight();
        }
        setWidth(defaultWidth);
        setHeight(height);
    }

    @Override
    public void layout(int left, int top, int right, int bottom) {
        int x = left + getHeadWidth() + PaddingResouce.getListMiddleSpacingPx();
        int y = top;
        for (int i = 0; i < mInnerPanels.size(); i++) {
            Span innerPanel = mInnerPanels.get(i);
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
            Span innerPanel = mInnerPanels.get(i);
            innerPanel.draw(canvas);
        }
    }

    protected abstract void drawHead(Canvas canvas);

    protected abstract int getHeadWidth();
}
