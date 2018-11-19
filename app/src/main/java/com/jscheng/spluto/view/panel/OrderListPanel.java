package com.jscheng.spluto.view.panel;

import android.graphics.Canvas;

import com.jscheng.spluto.view.Span;
import com.jscheng.spluto.view.panel.LineInnerPanel.LineInnerPanel;
import com.jscheng.spluto.view.span.TextSpan;

/**
 * Created by chengjunsen on 2018/11/15.
 */
public class OrderListPanel extends ListPanel {
    private int orderNum;

    public OrderListPanel(int orderNum) {
        this.orderNum = orderNum;
        createOrderHead();
    }

    private void createOrderHead() {
        Span span = new TextSpan(orderNum + ". ");
        this.addSpan(span);
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
