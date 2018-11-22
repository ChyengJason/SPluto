package com.jscheng.spluto.view.span;


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
import android.util.Log;

import com.jscheng.spluto.view.Span;
import com.jscheng.spluto.view.part.Part;
import com.jscheng.spluto.view.resource.ColorResource;
import com.jscheng.spluto.view.resource.FontResource;
import com.jscheng.spluto.view.part.PartType;
import com.jscheng.spluto.view.resource.PaddingResouce;

public class TextSpan extends Span {
    private StaticLayout mStaticLayout;
    private SpannableStringBuilder mSpanBuilder;
    private TextPaint mTextPaint;

    public TextSpan() {
        super(SpanType.TEXT_SPAN);
        this.mSpanBuilder = new SpannableStringBuilder();
        this.mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
    }

    public void addPart(Part part) {
        Log.e("CJS", "addPart: " + part.getText());
        int begin = mSpanBuilder.length();
        int end = mSpanBuilder.length() + part.getText().length();
        mSpanBuilder.append(part.getText());

        setFontSize(part, begin, end);
        setBackgroundColor(part, begin, end);
        setFontColor(part, begin, end);
        setStyle(part, begin, end);
    }

    private void setFontSize(Part part, int begin, int end) {
        if (isSetFontSize) {
            setProperity(new AbsoluteSizeSpan(mFontSize), begin, end);
        } else {
            int fontSize = FontResource.getFontSize(part.getFontLevel());
            setProperity(new AbsoluteSizeSpan(fontSize), begin, end);
        }
    }

    private void setFontColor(Part part, int begin, int end) {
        if (part.getPartType() == PartType.PART_TEXT) {
            setProperity(new ForegroundColorSpan(mFontColor), begin, end);
        } else if (part.getPartType() == PartType.PART_CODE) {
            setProperity(new ForegroundColorSpan(ColorResource.getCodeFontColor()), begin, end);
        } else if (part.getPartType() == PartType.PART_IMAGE) {
            setProperity(new ForegroundColorSpan(ColorResource.getImageFontColor()), begin, end);
        } else if (part.getPartType() == PartType.PART_LINK) {
            setProperity(new ForegroundColorSpan(ColorResource.getLinkFontColor()), begin, end);
            setProperity(new UnderlineSpan(), begin, end);
        }
    }

    private void setBackgroundColor(Part part, int begin, int end) {
        setProperity(new BackgroundColorSpan(mBackGroundColor), begin, end);
    }

    private void setStyle(Part part, int begin, int end) {
        if (isBold || part.isBold() || part.getFontLevel() > 0) {
            setProperity(new StyleSpan(Typeface.BOLD), begin, end);
        }
        if (isItalic || part.isItalic()) {
            setProperity(new StyleSpan(Typeface.ITALIC), begin, end);
        }
        if (isStrike || part.isStrike()) {
            setProperity(new StrikethroughSpan(), begin, end);
        }
        if (isUnderLine || part.isUnderline() ) {
            setProperity(new UnderlineSpan(), begin, end);
        }
    }

    private void setProperity(Object properitySpan, int begin, int end) {
        mSpanBuilder.setSpan(properitySpan, begin, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
    }

    @Override
    public void measure(int maxWidth, int maxHeight) {
        mStaticLayout = new StaticLayout(mSpanBuilder, mTextPaint, maxWidth, Layout.Alignment.ALIGN_NORMAL, 1.0f, PaddingResouce.getLineSpacingPx(), false);
        int width = mStaticLayout.getWidth();
        int height = mStaticLayout.getHeight();
        setWidth(width);
        setHeight(height);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.save();
        canvas.translate(getX(), getY());
        mStaticLayout.draw(canvas);
        canvas.restore();
    }

    @Override
    public void layout(int left, int top, int right, int bottom) {
        setX(left);
        setY(top);
    }

}
