package com.jscheng.spluto.markdown.view.panel;

import android.graphics.Canvas;

import com.jscheng.spluto.markdown.view.Panel;
import com.jscheng.spluto.markdown.view.part.Part;
import com.jscheng.spluto.markdown.view.Span;
import com.jscheng.spluto.markdown.view.resource.PaddingResouce;
import com.jscheng.spluto.markdown.view.span.PictureSpan;
import com.jscheng.spluto.markdown.view.span.SpanType;
import com.jscheng.spluto.markdown.view.span.TextSpan;
import com.jscheng.spluto.markdown.view.part.PartType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengjunsen on 2018/11/15.
 */
public class TextPanel extends Panel {
    private static final String TAG = "CJS";
    private List<Part> mParts;
    private List<Span> mSpans;

    public TextPanel() {
        mParts = new ArrayList<>();
        mSpans = new ArrayList<>();
    }

    @Override
    public void setParts(List<Part> list) {
        for (Part part : list) {
            mParts.add(part);
            if (part.getPartType() == PartType.PART_IMAGE) {
                PictureSpan picSpan = new PictureSpan();
                picSpan.setPart(part);
                mSpans.add(picSpan);
            } else {
                if (mSpans.isEmpty() || mSpans.get(mSpans.size() - 1).getSpanType() != SpanType.TEXT_SPAN) {
                    mSpans.add(new TextSpan());
                }
                TextSpan textSpan = (TextSpan) mSpans.get(mSpans.size() - 1);
                textSpan.addPart(part);
            }
        }
    }

    @Override
    public List<Part> getParts() {
        return mParts;
    }

    @Override
    public Part getPart(float x, float y) {
        for (Span span : mSpans) {
            boolean includeX = x >= span.getX() && x <= span.getX() + span.getWidth();
            boolean includeY = y >= span.getY() && y <= span.getY() + span.getHeight();
            if (includeX && includeY) {
                if (span.getSpanType() == SpanType.TEXT_SPAN) {
                    return ((TextSpan)span).getPart((int)x, (int)y);
                } else {
                    return ((PictureSpan)span).getPart((int)x, (int)y);
                }
            }
        }
        return null;
    }

    @Override
    public void measure(int defaultWidth, int defaultHeight) {
        int width = defaultWidth;
        int height = PaddingResouce.getPannelSpacingPx() * 2;
        for (int i = 0; i < mSpans.size(); i++) {
            Span span = mSpans.get(i);
            span.measure(defaultWidth, defaultHeight);
            height += span.getHeight();
        }
        setWidth(width);
        setHeight(height);
    }

    @Override
    public void layout(int left, int top, int right, int bottom) {
        int x = left;
        int y = top + PaddingResouce.getPannelSpacingPx();
        for (int i = 0; i < mSpans.size(); i++) {
            Span span = mSpans.get(i);
            span.layout(x, y, right, bottom);
            y += span.getHeight();
        }
        setX(left);
        setY(top);
    }

    @Override
    public void draw(Canvas canvas) {
        for (int i = 0; i < mSpans.size(); i++) {
            Span span = mSpans.get(i);
            span.draw(canvas);
        }
    }
}
