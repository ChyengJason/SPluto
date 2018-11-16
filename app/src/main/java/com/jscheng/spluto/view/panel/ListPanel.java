package com.jscheng.spluto.view.panel;
import com.jscheng.spluto.view.Panel;
import com.jscheng.spluto.view.Span;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengjunsen on 2018/11/15.
 */
public abstract class ListPanel extends Panel {
    private List<Span> spans;

    public ListPanel() {
        this.spans = new ArrayList<>();
    }

    public void setSpans(List<Span> spans) {
        this.spans.clear();
        this.spans.addAll(spans);
    }
}
