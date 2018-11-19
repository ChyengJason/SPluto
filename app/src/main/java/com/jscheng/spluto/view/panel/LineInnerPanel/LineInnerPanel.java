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

    public LineInnerPanel(LineInnerPanelType mPanelType) {
        this.mPanelType = mPanelType;
    }

    public LineInnerPanelType getmPanelType() {
        return mPanelType;
    }

}
