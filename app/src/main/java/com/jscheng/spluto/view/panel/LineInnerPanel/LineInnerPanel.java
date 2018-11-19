package com.jscheng.spluto.view.panel.LineInnerPanel;

import android.graphics.Canvas;

import com.jscheng.spluto.view.Panel;
import com.jscheng.spluto.view.resource.ColorResource;

public abstract class LineInnerPanel extends Panel{
    public enum LineInnerPanelType {
        // 图片
        PICTURE_INNER_PANEL,
        // 文本
        TEXT_INNER_PANEL
    }
    private LineInnerPanelType mPanelType;
    private int mBackGroundColor;

    public LineInnerPanel(LineInnerPanelType mPanelType) {
        this.mPanelType = mPanelType;
        mBackGroundColor = ColorResource.getDefaultBackgroundColor();
    }

    public LineInnerPanelType getmPanelType() {
        return mPanelType;
    }

    public int getBackGroundColor() {
        return mBackGroundColor;
    }

    public void setBackGroundColor(int mBackGroundColor) {
        this.mBackGroundColor = mBackGroundColor;
    }
}
