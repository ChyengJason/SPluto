package com.jscheng.spluto.view.panel;

import android.graphics.Canvas;

import com.jscheng.spluto.view.Panel;
import com.jscheng.spluto.view.Part;
import com.jscheng.spluto.view.span.LineSpan;
import com.jscheng.spluto.view.span.PictureLineSpan;
import com.jscheng.spluto.view.span.TextLineSpan;
import com.jscheng.spluto.view.part.ImagePart;
import com.jscheng.spluto.view.part.PartType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengjunsen on 2018/11/15.
 */
public class TextPanel extends Panel {
    private static final String TAG = "CJS";
    private List<Part> mParts;
    private List<LineSpan> mInnerPanels;

    public TextPanel() {
        mParts = new ArrayList<>();
        mInnerPanels = new ArrayList<>();
    }

    public void addPart(Part part) {
        mParts.add(part);
        if (part.getPartType() != PartType.PART_IMAGE) {
            if (mInnerPanels.isEmpty() || mInnerPanels.get(mInnerPanels.size() - 1).getmPanelType() != LineSpan.LineInnerPanelType.TEXT_INNER_PANEL) {
                mInnerPanels.add(new TextLineSpan());
            }
            TextLineSpan textInnerPanel = (TextLineSpan) mInnerPanels.get(mInnerPanels.size() - 1);
            textInnerPanel.addTextPart(part);
        } else {
            ImagePart imagePart = (ImagePart) part;
            PictureLineSpan pictureInnerPanel = new PictureLineSpan(imagePart.getUrl(), imagePart.getDescripe());
            mInnerPanels.add(pictureInnerPanel);
        }
    }

    @Override
    public void measure(int defaultWidth, int defaultHeight) {
        int width = defaultWidth;
        int height = 0;
        for (int i = 0; i < mInnerPanels.size(); i++) {
            LineSpan innerPanel = mInnerPanels.get(i);
            innerPanel.measure(defaultWidth, defaultHeight);
            height += innerPanel.getHeight();
        }
        setWidth(width);
        setHeight(height);
    }

    @Override
    public void layout(int left, int top, int right, int bottom) {
        int x = left;
        int y = top;
        for (int i = 0; i < mInnerPanels.size(); i++) {
            LineSpan innerPanel = mInnerPanels.get(i);
            innerPanel.layout(x, y, right, bottom);
            y += innerPanel.getHeight();
        }
        setX(left);
        setY(top);
    }

    @Override
    public void draw(Canvas canvas) {
        for (int i = 0; i < mInnerPanels.size(); i++) {
            LineSpan innerPanel = mInnerPanels.get(i);
            innerPanel.draw(canvas);
        }
    }
}
