package com.jscheng.spluto.view.panel;

import android.graphics.Canvas;

import com.jscheng.spluto.view.Panel;
import com.jscheng.spluto.view.part.Part;
import com.jscheng.spluto.view.Span;
import com.jscheng.spluto.view.resource.PaddingResouce;
import com.jscheng.spluto.view.span.PictureSpan;
import com.jscheng.spluto.view.span.SpanType;
import com.jscheng.spluto.view.span.TextSpan;
import com.jscheng.spluto.view.part.PartType;
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
            if (part.getPartType() != PartType.PART_IMAGE) {
                if (mSpans.isEmpty() || mSpans.get(mSpans.size() - 1).getSpanType() != SpanType.TEXT_SPAN) {
                    mSpans.add(new TextSpan());
                }
                TextSpan textSpan = (TextSpan) mSpans.get(mSpans.size() - 1);
                textSpan.addPart(part);
            } else {
                PictureSpan picSpan = new PictureSpan();
                picSpan.setPart(part);
                mSpans.add(picSpan);
            }
        }
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
