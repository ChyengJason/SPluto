package com.jscheng.spluto.view.span;

import com.jscheng.spluto.view.Span;

/**
 * Created by chengjunsen on 2018/11/15.
 */
public class TextSpan extends Span {
    private String value;

    public TextSpan(String value) {
        this.value = value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public void measure(int maxWidth, int maxHeight) {

    }
}
