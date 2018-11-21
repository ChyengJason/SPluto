package com.jscheng.spluto.view;

import com.jscheng.spluto.view.span.SpanType;

public abstract class Span extends Panel{

    private SpanType mPanelType;

    public Span(SpanType mPanelType) {
        this.mPanelType = mPanelType;
    }

    public SpanType getmPanelType() {
        return mPanelType;
    }

}
