package com.jscheng.spluto.view.span;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;

import com.jscheng.spluto.view.Span;

/**
 * Created by chengjunsen on 2018/11/15.
 */
public class CodeSpan extends Span {
    private String value;

    public CodeSpan(String value) {
        this.value = value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String getText() {
        return value;
    }

    @Override
    public void setSpannable(SpannableStringBuilder spanBuilder) {

    }
}
