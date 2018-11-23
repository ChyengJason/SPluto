package com.jscheng.spluto.view.panel;


import android.graphics.Canvas;

import com.jscheng.spluto.view.Panel;
import com.jscheng.spluto.view.part.Part;

import java.util.List;

/**
 * Created by chengjunsen on 2018/11/15.
 */
public class TablePanel extends Panel {
    private List<Part> mParts;

    @Override
    public void setParts(List<Part> list) {
        mParts = list;
    }

    @Override
    public List<Part> getParts() {
        return mParts;
    }

    @Override
    public void measure(int defaultWidth, int defaultHeight) {

    }

    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public void layout(int left, int top, int right, int bottom) {

    }

    @Override
    public Part getPart(float x, float y) {
        return null;
    }
}
