package com.jscheng.spluto.view.panel;

import com.jscheng.spluto.view.Panel;
import com.jscheng.spluto.view.Span;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengjunsen on 2018/11/15.
 */
public class TextPanel extends Panel {
    private List<Span> mSpans;

    public TextPanel() {
        mSpans = new ArrayList<>();
    }

    public void setSpans(List<Span> spans) {
        mSpans.clear();
        mSpans.addAll(spans);
    }

    @Override
    public void measure(int maxWidth, int maxHeight) {

    }
}
