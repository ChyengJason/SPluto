package com.jscheng.spluto.view.span;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Log;

import com.jscheng.spluto.util.FontUtil;
import com.jscheng.spluto.view.Span;
import com.jscheng.spluto.view.resource.FontResouce;

/**
 * Created by chengjunsen on 2018/11/15.
 */
public class TextSpan extends Span {
    private static final String TAG = "CJS";
    private String value;
    private TextPaint paint;

    public TextSpan(String value) {
        this.value = value;
        this.paint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    private void initPaint() {
        int fontSize = FontResouce.getFontSize(getFontLevel());
        Log.d(TAG, "textSpan: fontLevel: " + getFontLevel() + " fontsize: " + fontSize);
        paint.setTextSize(fontSize);
        paint.setLetterSpacing(0.0f);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        if (isBold() || getFontLevel() > 0) {
            paint.setFakeBoldText(true);
        }
        if (isItalic()) {
            paint.setTextSkewX(-0.3f);
        }
        if (isStrike()) {
            paint.setStrikeThruText(true);
        }
    }

    @Override
    public String getText() {
        return value;
    }

    @Override
    public void measure(int defaultWidth, int defaultHeight) {
        initPaint();
        double width = FontUtil.getFontWidth(paint, getValue());
        double height = FontUtil.getFontHeight(paint);
        this.setWidth((int)Math.ceil(width));
        this.setHeight((int)Math.ceil(height));
    }

    @Override
    public void draw(Canvas canvas) {
        draw(canvas, 0, 0, 0, value.length());
    }

    @Override
    public void draw(Canvas canvas, int x, int y, int start, int end) {
        String subValue = value.substring(start, end);
        canvas.save();
        canvas.translate(x, y);
        StaticLayout staticLayout = new StaticLayout(subValue, paint, canvas.getWidth(), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        staticLayout.draw(canvas);
        canvas.restore();
    }
}
