package com.jscheng.spluto.view;

import android.graphics.Canvas;
import android.text.SpannableStringBuilder;

import com.jscheng.spluto.view.span.SpanType;

/**
 * Created by chengjunsen on 2018/11/15.
 */
public abstract class Span {
    private int begin, end;
    private boolean bold;
    private boolean italic;
    private boolean strike;
    private int fontLevel;
    private SpanType spanType;

    public Span(SpanType spanType) {
        this.spanType = spanType;
    }

    public void setBold(boolean bold) {
        this.bold = bold;
    }

    public void setItalic(boolean italic) {
        this.italic = italic;
    }

    public void setStrike(boolean strike) {
        this.strike = strike;
    }

    public void setFontLevel(int fontLevel) {
        this.fontLevel = fontLevel;
    }

    public boolean isBold() {
        return bold;
    }

    public boolean isItalic() {
        return italic;
    }

    public boolean isStrike() {
        return strike;
    }

    public int getFontLevel() {
        return fontLevel;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public abstract String getText();

    public SpanType getSpanType() {
        return spanType;
    }
}
