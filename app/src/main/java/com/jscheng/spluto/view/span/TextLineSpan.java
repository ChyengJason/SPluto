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

import com.jscheng.spluto.view.Part;
import com.jscheng.spluto.view.resource.ColorResource;
import com.jscheng.spluto.view.resource.FontResouce;
import com.jscheng.spluto.view.resource.PaddingResouce;
import com.jscheng.spluto.view.part.PartType;

public class TextLineSpan extends LineSpan {
    private StaticLayout mStaticLayout;
    private SpannableStringBuilder mSpanBuilder;
    private TextPaint mTextPaint;
    private int mBackGroundColor;
    private int mFontColor;
    private boolean isBold;
    private boolean isItalic;
    private boolean isStrike;
    private boolean isUnderLine;

    public TextLineSpan() {
        super(LineInnerPanelType.TEXT_INNER_PANEL);
        mSpanBuilder = new SpannableStringBuilder();
        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mBackGroundColor = ColorResource.getDefaultBackgroundColor();
        mFontColor = ColorResource.getTextFontColor();
        isBold = false;
        isItalic = false;
        isStrike = false;
        isUnderLine = false;
    }

    public void addTextPart(Part part) {
        int begin = mSpanBuilder.length();
        int end = mSpanBuilder.length() + part.getText().length();
        mSpanBuilder.append(part.getText());
        int fontSize = FontResouce.getFontSize(part.getFontLevel());
        setProperity(new AbsoluteSizeSpan(fontSize), begin, end);
        setProperity(new BackgroundColorSpan(mBackGroundColor), begin, end);

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

    public void setBackGroundColor(int mBackGroundColor) {
        this.mBackGroundColor = mBackGroundColor;
    }

    public void setFontColor(int mFontColor) {
        this.mFontColor = mFontColor;
    }

    public void setBold(boolean bold) {
        isBold = bold;
    }

    public void setItalic(boolean italic) {
        isItalic = italic;
    }

    public void setStrike(boolean strike) {
        isStrike = strike;
    }

    public void setUnderLine(boolean underLine) {
        isUnderLine = underLine;
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
