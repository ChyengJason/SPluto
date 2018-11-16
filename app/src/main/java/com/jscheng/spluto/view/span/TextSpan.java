package com.jscheng.spluto.view.span;

import android.graphics.Paint;

import com.jscheng.spluto.util.FontUtil;
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
    public void measure(int defaultWidth, int defaultHeight) {
        int fontSize = FontUtil.getFontSize(getFontLevel());
        Paint paint = new Paint();
        //paint.setLetterSpacing();
        paint.setTextSize(fontSize);
        double width = FontUtil.getFontWidth(paint, getValue());
        double height = FontUtil.getFontHeight(paint);
        setWidth((int)Math.ceil(width));
        setHeight((int)Math.ceil(height));
    }
}
