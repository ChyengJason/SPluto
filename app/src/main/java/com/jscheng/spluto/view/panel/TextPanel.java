package com.jscheng.spluto.view.panel;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Log;

import com.jscheng.spluto.view.Panel;
import com.jscheng.spluto.view.Span;
import com.jscheng.spluto.view.panel.LineInnerPanel.LineInnerPanel;
import com.jscheng.spluto.view.panel.LineInnerPanel.PictureLineInnerPanel;
import com.jscheng.spluto.view.panel.LineInnerPanel.TextLineInnerPanel;
import com.jscheng.spluto.view.resource.FontResouce;
import com.jscheng.spluto.view.resource.PaddingResouce;
import com.jscheng.spluto.view.span.ImageSpan;
import com.jscheng.spluto.view.span.SpanType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengjunsen on 2018/11/15.
 */
public class TextPanel extends Panel {
    private static final String TAG = "CJS";
    private List<Span> mSpans;
    private List<LineInnerPanel> mInnerPanels;

    public TextPanel() {
        mSpans = new ArrayList<>();
        mInnerPanels = new ArrayList<>();
    }

    public void addSpan(Span span) {
        mSpans.add(span);
        if (span.getSpanType() != SpanType.SPAN_IMAGE) {
            if (mInnerPanels.isEmpty() || mInnerPanels.get(mInnerPanels.size() - 1).getmPanelType() != LineInnerPanel.LineInnerPanelType.TEXT_INNER_PANEL) {
                mInnerPanels.add(new TextLineInnerPanel());
            }
            TextLineInnerPanel textInnerPanel = (TextLineInnerPanel) mInnerPanels.get(mInnerPanels.size() - 1);
            textInnerPanel.setLevel(getLevel());
            textInnerPanel.addTextSpan(span);
        } else {
            ImageSpan imageSpan = (ImageSpan) span;
            PictureLineInnerPanel pictureInnerPanel = new PictureLineInnerPanel(imageSpan.getUrl(), imageSpan.getDescripe());
            pictureInnerPanel.setLevel(getLevel());
            mInnerPanels.add(pictureInnerPanel);
        }
    }

    @Override
    public void measure(int defaultWidth, int defaultHeight) {
        int width = defaultWidth;
        int height = 0;
        for (int i = 0; i < mInnerPanels.size(); i++) {
            LineInnerPanel innerPanel = mInnerPanels.get(i);
            innerPanel.measure(defaultWidth, defaultHeight);
            height += innerPanel.getHeight();
        }
        setWidth(width);
        setHeight(height);
    }

    @Override
    public void layout(int left, int top, int right, int bottom) {
        int x = left;
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
        for (int i = 0; i < mInnerPanels.size(); i++) {
            LineInnerPanel innerPanel = mInnerPanels.get(i);
            innerPanel.draw(canvas);
        }
    }
}
