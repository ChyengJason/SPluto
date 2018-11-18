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
        Log.e(TAG, "PanelGroup measure width: " + width + " height: " + height);
    }

    public int getHeight() {
        Log.e(TAG, "PanelGroup measure width: " + width + " height: " + height);
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void draw(Canvas canvas) {
        for (Panel panel : mPanels) {
           panel.draw(canvas);
        }
    }

    public void layout(int left, int top, int right, int bottom) {
        int yBegin = top + PaddingResouce.getTopPaddingPx();
        int yEnd = bottom - PaddingResouce.getBottomPaddingPx();
        int xBegin = left + PaddingResouce.getLeftPaddingPx();
        int xEnd = right - PaddingResouce.getRightPaddingPx();

        int lastYEnd = yBegin;
        for (int i = 0; i < mPanels.size(); i++) {
            Panel panel = mPanels.get(i);
            panel.layout(xBegin, lastYEnd, xEnd, lastYEnd + panel.getHeight());
            lastYEnd += panel.getHeight();
        }
    }
}
