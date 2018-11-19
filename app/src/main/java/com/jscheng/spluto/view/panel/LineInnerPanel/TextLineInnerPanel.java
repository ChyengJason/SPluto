package com.jscheng.spluto.view.panel.LineInnerPanel;


import android.graphics.Canvas;
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
import com.jscheng.spluto.view.resource.ColorResource;
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
        setProperity(new AbsoluteSizeSpan(fontSize), begin, end);
        setProperity(new BackgroundColorSpan(getBackGroundColor()), begin, end);

        if (span.getSpanType() == SpanType.SPAN_TEXT) {
            setProperity(new ForegroundColorSpan(ColorResource.getTextFontColor()), begin, end);
        } else if (span.getSpanType() == SpanType.SPAN_CODE) {
            setProperity(new ForegroundColorSpan(ColorResource.getCodeFontColor()), begin, end);
        } else if (span.getSpanType() == SpanType.SPAN_IMAGE) {
            setProperity(new ForegroundColorSpan(ColorResource.getImageFontColor()), begin, end);
        } else if (span.getSpanType() == SpanType.SPAN_LINK) {
            setProperity(new ForegroundColorSpan(ColorResource.getLinkFontColor()), begin, end);
            setProperity(new UnderlineSpan(), begin, end);
        }

        if (span.isBold() || span.getFontLevel() > 0) {
            setProperity(new StyleSpan(Typeface.BOLD), begin, end);
        }
        if (span.isItalic()) {
            setProperity(new StyleSpan(Typeface.ITALIC), begin, end);
        }
        if (span.isStrike()) {
            setProperity(new StrikethroughSpan(), begin, end);
        }
    }

    private void setProperity(Object properitySpan, int begin, int end) {
        mSpanBuilder.setSpan(properitySpan, begin, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
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
