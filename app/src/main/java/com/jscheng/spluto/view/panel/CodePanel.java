package com.jscheng.spluto.view.panel;


import com.jscheng.spluto.view.Panel;

/**
 * Created by chengjunsen on 2018/11/15.
 */
public class CodePanel extends Panel {
    private String value;

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public void measure(int defaultWidth, int defaultHeight) {

    }
}
