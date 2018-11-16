package com.jscheng.spluto.view;

import com.jscheng.spluto.core.parser.Analyzer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengjunsen on 2018/11/16.
 */
public class PanelGroup {
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
        width = windowWidth;
        height = 0;
        for (Panel panel : mPanels) {
            panel.measure(width, 0);
            height += panel.getHeight();
        }
        System.out.println("PanelGroup measure width: " + width + " height: " + height);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
