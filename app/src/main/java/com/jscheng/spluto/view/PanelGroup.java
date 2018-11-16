package com.jscheng.spluto.view;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengjunsen on 2018/11/16.
 */
public class PanelGroup {
    private List<Panel> mPanels;
    private int windowHeight;
    private int windowWidth;
    private int height;
    private int width;

    public PanelGroup() {
        this.mPanels = new ArrayList<>();
    }

    public void setPanels(List<Panel> panels) {
        this.mPanels.clear();
        this.mPanels.addAll(panels);
    }

    public void setWindowSize(int windowWidth, int windowHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
    }

    public void measure() {
        width = windowWidth;
        height = 0;
        for (Panel panel : mPanels) {
            panel.measure(width, 0);
            height += panel.getHeight();
        }
        System.out.println("width: " + width + " height: " + height);
    }
}
