package com.jscheng.spluto.view.span;

import com.jscheng.spluto.view.Panel;

public abstract class LineSpan extends Panel{
    public enum LineInnerPanelType {
        // 图片
        PICTURE_INNER_PANEL,
        // 文本
        TEXT_INNER_PANEL
    }
    private LineInnerPanelType mPanelType;

    public LineSpan(LineInnerPanelType mPanelType) {
        this.mPanelType = mPanelType;
    }

    public LineInnerPanelType getmPanelType() {
        return mPanelType;
    }

}
