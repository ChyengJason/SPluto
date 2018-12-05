package com.jscheng.spluto.markdown.view.panel;
import android.graphics.Canvas;

import com.jscheng.spluto.markdown.core.parser.ListType;
import com.jscheng.spluto.markdown.view.Panel;
import com.jscheng.spluto.markdown.view.part.Part;
import com.jscheng.spluto.markdown.view.Span;
import com.jscheng.spluto.markdown.view.span.OrderHeadSpan;
import com.jscheng.spluto.markdown.view.span.PictureSpan;
import com.jscheng.spluto.markdown.view.span.QuoteHeadSpan;
import com.jscheng.spluto.markdown.view.span.SpanType;
import com.jscheng.spluto.markdown.view.span.TextSpan;
import com.jscheng.spluto.markdown.view.resource.ColorResource;
import com.jscheng.spluto.markdown.view.resource.PaddingResouce;
import com.jscheng.spluto.markdown.view.part.PartType;
import com.jscheng.spluto.markdown.view.span.TodoHeadSpan;
import com.jscheng.spluto.markdown.view.span.UnorderHeadSpan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengjunsen on 2018/11/15.
 */
public class ListPanel extends Panel {
    private ListType mListType;
    private List<Part> mParts;
    private List<Span> mSpans;
    private Span mHeadSpan;
    private int mBackgroudColor;
    private int mFontColor;
    private boolean isStrike;

    public ListPanel(ListType mListType, int orderNum) {
        this.mListType = mListType;
        this.mSpans = new ArrayList<>();
        this.mHeadSpan = createHeadSpan(orderNum);
        initProperity();
    }

    private void initProperity() {
        this.mFontColor = mListType == ListType.QUOTE ? ColorResource.getQuoteFontColor() : ColorResource.getTextFontColor();
        this.mBackgroudColor = mListType == ListType.QUOTE ? ColorResource.getQuoteBackgroudColor() : ColorResource.getDefaultBackgroundColor();
        this.isStrike = mListType == ListType.TODO_LIST;
    }

    private Span createHeadSpan(int orderNum) {
        switch (mListType) {
            case UNTODO_LIST:
                return new TodoHeadSpan(false);
            case TODO_LIST:
                return new TodoHeadSpan(true);
            case ORDERED_LIST:
                return new OrderHeadSpan(orderNum);
            case QUOTE:
                return new QuoteHeadSpan();
            case UNORDERED_LIST:
                return new UnorderHeadSpan();
            default:
                return new UnorderHeadSpan();
        }
    }

    @Override
    public void setParts(List<Part> parts) {
        this.mParts = parts;
        for (Part part : parts) {
            if (part.getPartType() != PartType.PART_IMAGE) {
                if (mSpans.isEmpty() || mSpans.get(mSpans.size() - 1).getSpanType() != SpanType.TEXT_SPAN) {
                    mSpans.add(new TextSpan());
                }
                TextSpan textSpan = (TextSpan) mSpans.get(mSpans.size() - 1);
                textSpan.setBackGroundColor(mBackgroudColor);
                textSpan.setFontColor(mFontColor);
                textSpan.setStrike(isStrike);
                textSpan.addPart(part);
            } else {
                PictureSpan picSpan = new PictureSpan();
                picSpan.setPart(part);
                mSpans.add(picSpan);
            }
        }
    }

    @Override
    public List<Part> getParts() {
        return mParts;
    }

    @Override
    public void measure(int defaultWidth, int defaultHeight) {
        int width =  defaultWidth - PaddingResouce.getListMiddleSpacingPx();
        mHeadSpan.measure(defaultWidth, 0);
        width -= mHeadSpan.getWidth();
        int height = 0;
        for (int i = 0; i < mSpans.size(); i++) {
            Span span = mSpans.get(i);
            span.measure(width, defaultHeight);
            height += span.getHeight();
        }
        height += PaddingResouce.getPannelSpacingPx() * 2;
        if (mListType == ListType.QUOTE) {
            mHeadSpan.measure(mHeadSpan.getWidth(), height);
        }
        setWidth(defaultWidth);
        setHeight(height);
    }

    @Override
    public void layout(int left, int top, int right, int bottom) {
        int y = top + PaddingResouce.getPannelSpacingPx();
        if (mListType == ListType.QUOTE) {
            mHeadSpan.layout(left, top, right, bottom);
        } else {
            mHeadSpan.layout(left, y, right, bottom);
        }
        int x = left + mHeadSpan.getWidth() + PaddingResouce.getListMiddleSpacingPx();
        for (int i = 0; i < mSpans.size(); i++) {
            Span span = mSpans.get(i);
            span.layout(x, y, right, bottom);
            y += span.getHeight();
        }
        setX(left);
        setY(top);
    }

    @Override
    public Part getPart(float x, float y) {
        for (Span span : mSpans) {
            boolean includeX = x >= span.getX() && x <= span.getX() + span.getWidth();
            boolean includeY = y >= span.getY() && y <= span.getY() + span.getHeight();
            if (!includeX || !includeY) {
                return null;
            }
            if (span.getSpanType() == SpanType.TEXT_SPAN) {
                return ((TextSpan)span).getPart((int)x, (int)y);
            } else {
                return ((PictureSpan)span).getPart((int)x, (int)y);
            }
        }
        return null;
    }

    @Override
    public void draw(Canvas canvas) {
        mHeadSpan.draw(canvas);
        for (int i = 0; i < mSpans.size(); i++) {
            Span span = mSpans.get(i);
            span.draw(canvas);
        }
    }
}
