package com.jscheng.spluto.view.panel.LineInnerPanel;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;

import com.jscheng.spluto.view.Span;
import com.jscheng.spluto.view.resource.FontResouce;
import com.jscheng.spluto.view.resource.PaddingResouce;
import com.jscheng.spluto.view.span.SpanType;

public class TextLineInnerPanel extends LineInnerPanel {
    private StaticLayout mStaticLayout;
    private SpannableStringBuilder mSpanBuilder;
    private TextPaint mTextPaint;

    public TextLineInnerPanel() {
        super(LineInnerPanelType.TEXT_INNER_PANEL);
        mSpanBuilder = new SpannableStringBuilder();
        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
    }

    public void addTextSpan(Span span) {
        int begin = mSpanBuilder.length();
        int end = mSpanBuilder.length() + span.getText().length();
        mSpanBuilder.append(span.getText());
        int fontSize = FontResouce.getFontSize(span.getFontLevel());
        mSpanBuilder.setSpan(new AbsoluteSizeSpan(fontSize), begin, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        if (span.getSpanType() == SpanType.SPAN_TEXT) {
            mSpanBuilder.setSpan(new ForegroundColorSpan(FontResouce.getTextFontColor()), begin, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        } else if (span.getSpanType() == SpanType.SPAN_CODE) {
            mSpanBuilder.setSpan(new ForegroundColorSpan(FontResouce.getCodeFontColor()), begin, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            mSpanBuilder.setSpan(new BackgroundColorSpan(FontResouce.getCodeBackgroudColor()), begin, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        } else if (span.getSpanType() == SpanType.SPAN_IMAGE) {
            mSpanBuilder.setSpan(new ForegroundColorSpan(FontResouce.getImageFontColor()), begin, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        } else if (span.getSpanType() == SpanType.SPAN_LINK) {
            mSpanBuilder.setSpan(new ForegroundColorSpan(FontResouce.getLinkFontColor()), begin, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            mSpanBuilder.setSpan(new UnderlineSpan(), begin, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        }

        if (span.isBold() || span.getFontLevel() > 0) {
            mSpanBuilder.setSpan(new StyleSpan(Typeface.BOLD), begin, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        }
        if (span.isItalic()) {
            mSpanBuilder.setSpan(new StyleSpan(Typeface.ITALIC), begin, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        }
        if (span.isStrike()) {
            mSpanBuilder.setSpan(new StrikethroughSpan(), begin, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        }
    }

    @Override
    public void measure(int maxWidth, int maxHeight) {
        mStaticLayout = new StaticLayout(mSpanBuilder, mTextPaint, maxWidth, Layout.Alignment.ALIGN_NORMAL, 1.0f, PaddingResouce.getLineSpacingPx(), false);
        int width = mStaticLayout.getWidth();
        int height = (int)(mStaticLayout.getHeight() + 2 * PaddingResouce.getPannelSpacingPx());
        setWidth(width);
        setHeight(height);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.save();
        canvas.translate(getX(), getY() + (int)PaddingResouce.getPannelSpacingPx());
        mStaticLayout.draw(canvas);
        canvas.restore();
    }

    @Override
    public void layout(int left, int top, int right, int bottom) {
        setX(left);
        setY(top);
    }
}
