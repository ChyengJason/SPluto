package com.jscheng.spluto.markdown.view.span;


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

import com.jscheng.spluto.markdown.view.Span;
import com.jscheng.spluto.markdown.view.part.Part;
import com.jscheng.spluto.markdown.view.resource.ColorResource;
import com.jscheng.spluto.markdown.view.resource.FontResource;
import com.jscheng.spluto.markdown.view.part.PartType;
import com.jscheng.spluto.markdown.view.resource.PaddingResouce;

import java.util.ArrayList;
import java.util.List;

public class TextSpan extends Span {
    private static final String TAG = "CJS";
    private StaticLayout mStaticLayout;
    private SpannableStringBuilder mSpanBuilder;
    private TextPaint mTextPaint;
    private List<Part> mParts;

    public TextSpan() {
        super(SpanType.TEXT_SPAN);
        this.mSpanBuilder = new SpannableStringBuilder();
        this.mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        this.mParts = new ArrayList<>();
    }

    public void addPart(Part part) {
        mParts.add(part);
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
        mSpanBuilder.setSpan(properitySpan, begin, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
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

    public Part getPart(int x, int y) {
        if (mStaticLayout == null) {
            return null;
        }
        int num = getWordNum(x, y);
        if (num >= 0 && num < mSpanBuilder.length()) {
            int wordCount = 0;
            for (Part part : mParts) {
                int begin = wordCount;
                int end = wordCount + part.getText().length() - 1;
                if (num >= begin && num <= end) {
                    Log.e(TAG, "getPart: " + part.getText().toCharArray()[num - begin]);
                    return part;
                }
            }
        }
        return null;
    }

    private int getWordNum(int x, int y) {
        x = x - getX();
        y = y - getY();
        for (int line = 0; line < mStaticLayout.getLineCount(); line++) {
            int topY = mStaticLayout.getLineTop(line);
            int bottomY = mStaticLayout.getLineBottom(line);
            int startNum = mStaticLayout.getLineStart(line);
            int endNum = mStaticLayout.getLineEnd(line);
            if (y < topY || y > bottomY) {
                continue;
            }
            for (int i = startNum; i < endNum; i++) {
                float charLeft = mStaticLayout.getPrimaryHorizontal(i);
                float charRight = i < endNum ? mStaticLayout.getPrimaryHorizontal(i + 1) : mStaticLayout.getLineWidth(i);
                if (x >= charLeft && x <= charRight) {
                    return i;
                }
            }
        }
        return -1;
    }
}
