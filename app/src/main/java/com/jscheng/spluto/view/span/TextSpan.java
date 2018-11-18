package com.jscheng.spluto.view.span;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
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
    StaticLayout mStaticLayout;

    public TextSpan(String value) {
        this.value = value;
        this.mStaticLayout = null;
    }

    @Override
    public void setSpannable(SpannableStringBuilder spanBuilder) {
        int begin = spanBuilder.length();
        int end = spanBuilder.length() + value.length();
        setBegin(begin);
        setEnd(end);
        spanBuilder.append(value);
        int fontSize = FontResouce.getFontSize(getFontLevel());
        spanBuilder.setSpan(new ForegroundColorSpan(Color.BLACK), begin, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spanBuilder.setSpan(new AbsoluteSizeSpan(fontSize), begin, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        if (isBold() || getFontLevel() > 0) {
            spanBuilder.setSpan(new StyleSpan(Typeface.BOLD), begin, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        }
        if (isItalic()) {
            spanBuilder.setSpan(new StyleSpan(Typeface.ITALIC), begin, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        }
        if (isStrike()) {
            spanBuilder.setSpan(new StrikethroughSpan(), begin, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        }
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

}
