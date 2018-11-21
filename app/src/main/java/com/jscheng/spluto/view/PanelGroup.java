package com.jscheng.spluto.view;

import android.graphics.Canvas;
import android.util.Log;

import com.jscheng.spluto.view.resource.FontResouce;
import com.jscheng.spluto.view.resource.PaddingResouce;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengjunsen on 2018/11/16.
 */
public class PanelGroup {
    private static final String TAG = "CJS";
    private List<Panel> mPanels;
    private int height;
    private int width;

    public PanelGroup() {
        this.mPanels = new ArrayList<>();
    }

    public void setPanels(List<Panel> panels) {
        this.mPanels.clear();
        this.mPanels.addAll(panels);
    }

    public void measure(int windowWidth, int windowHeight) {
        width = windowWidth - PaddingResouce.getLeftPaddingPx() - PaddingResouce.getRightPaddingPx();
        height = PaddingResouce.getTopPaddingPx();
        for (int i = 0; i < mPanels.size(); i++) {
            Panel panel = mPanels.get(i);
            panel.measure(width, 0);
            height += panel.getHeight();
        }
        height += PaddingResouce.getBottomPaddingPx();
        Log.e(TAG, "PanelGroup measure width: " + width + " height: " + height + " window width: " + windowWidth);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void draw(Canvas canvas, int x1, int y1, int x2, int y2) {
        for (Panel panel : mPanels) {
            if (needToDraw(panel, x1, y1, x2, y2)) {
                panel.draw(canvas);
            }
        }
    }

    public void layout() {
        int yBegin = PaddingResouce.getTopPaddingPx();
        int yEnd = getHeight() - PaddingResouce.getBottomPaddingPx();
        int xBegin = PaddingResouce.getLeftPaddingPx();
        int xEnd = getWidth() - PaddingResouce.getRightPaddingPx();

        int lastYEnd = yBegin;
        for (int i = 0; i < mPanels.size(); i++) {
            Panel panel = mPanels.get(i);
            panel.layout(xBegin, lastYEnd, xEnd, lastYEnd + panel.getHeight());
            lastYEnd += panel.getHeight();
        }
        Log.e(TAG, "layout: "+ lastYEnd );
    }

    /**
     * 最多绘制三个可见区域的面积
     * @param panel
     * @param x1 可见区域 x1
     * @param y1 可见区域 x2
     * @param x2 可见区域 y1
     * @param y2 可见区域 y2
     * @return
     */
    private boolean needToDraw(Panel panel, int x1, int y1, int x2, int y2) {
        int visableHeight = y2 - y1;
        int left = x1;
        int right = x2;
        int top = y1 - visableHeight > 0 ? y1 - visableHeight : 0;
        int bottom = y2 + visableHeight < getHeight() ? y2 + visableHeight : getHeight();
        int panelLeft = panel.getX();
        int panelRight = panel.getX() + panel.getWidth();
        int panelTop = panel.getY();
        int panelBottom = panel.getY() + panel.getHeight();
        boolean includeX = (left >= panelRight || right >= panelLeft);
        boolean includeY = (top >= panelBottom || bottom >= panelTop);
        return includeX && includeY;
    }
}
