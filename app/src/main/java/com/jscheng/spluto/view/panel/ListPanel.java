package com.jscheng.spluto.view.panel;
import com.jscheng.spluto.view.Panel;
import com.jscheng.spluto.view.Span;
import com.jscheng.spluto.view.panel.LineInnerPanel.LineInnerPanel;
import com.jscheng.spluto.view.panel.LineInnerPanel.PictureLineInnerPanel;
import com.jscheng.spluto.view.panel.LineInnerPanel.TextLineInnerPanel;
import com.jscheng.spluto.view.span.ImageSpan;
import com.jscheng.spluto.view.span.SpanType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengjunsen on 2018/11/15.
 */
public abstract class ListPanel extends Panel {
    protected List<Span> mSpans;
    protected List<LineInnerPanel> mInnerPanels;

    public ListPanel() {
        this.mSpans = new ArrayList<>();
        mInnerPanels = new ArrayList<>();
    }

    public void addSpan(Span span) {
        this.mSpans.add(span);
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
}
