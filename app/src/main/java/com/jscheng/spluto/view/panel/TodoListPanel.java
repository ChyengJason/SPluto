package com.jscheng.spluto.view.panel;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Size;

import com.jscheng.spluto.view.panel.LineInnerPanel.LineInnerPanel;
import com.jscheng.spluto.view.resource.BitmapResource;
import com.jscheng.spluto.view.resource.PaddingResouce;

/**
 * Created by chengjunsen on 2018/11/15.
 */
public class TodoListPanel extends ListPanel {
    private Paint mHeadPaint;
    private int checkboxHeight;
    private int checkboxWidth;
    private Bitmap checkboxBitmap;
    private boolean isChecked;

    public TodoListPanel(boolean isChecked) {
        this.mHeadPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.isChecked = isChecked;
        loadCheckBoxBitmap();
    }

    private void loadCheckBoxBitmap() {
        Size size = BitmapResource.loadDefaultCheckBoxSize();
        checkboxHeight = size.getHeight();
        checkboxWidth = size.getWidth();
        checkboxBitmap = isChecked ? BitmapResource.loadCheckBoxBitmap() : BitmapResource.loadUnCheckBoxBitmap();
    }

    @Override
    public void measure(int defaultWidth, int defaultHeight) {
        int width = (int)(defaultWidth - checkboxWidth - PaddingResouce.getListMiddleSpacingPx());
        int height = 0;
        for (int i = 0; i < mInnerPanels.size(); i++) {
            LineInnerPanel innerPanel = mInnerPanels.get(i);
            innerPanel.measure(width, defaultHeight);
            height += innerPanel.getHeight();
        }
        setWidth(defaultWidth);
        setHeight(height);
    }

    @Override
    public void layout(int left, int top, int right, int bottom) {
        int x = (int)(left + checkboxWidth + PaddingResouce.getListMiddleSpacingPx());
        int y = top;
        for (int i = 0; i < mInnerPanels.size(); i++) {
            LineInnerPanel innerPanel = mInnerPanels.get(i);
            innerPanel.layout(x, y, right, bottom);
            y += innerPanel.getHeight();
        }
        setX(left);
        setY(top);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.save();
        canvas.translate(getX(), getY());
        if (checkboxBitmap != null) {
            int destTop = (int)PaddingResouce.getCheckBoxTopPaddingPx();
            int destBottom = destTop + checkboxHeight;
            Rect resRect = new Rect(0, 0, checkboxBitmap.getWidth(), checkboxBitmap.getHeight());
            Rect destrect = new Rect(0, destTop, checkboxWidth, destBottom);
            canvas.drawBitmap(checkboxBitmap, resRect, destrect, mHeadPaint);
        }
        canvas.restore();
        for (int i = 0; i < mInnerPanels.size(); i++) {
            LineInnerPanel innerPanel = mInnerPanels.get(i);
            innerPanel.draw(canvas);
        }
    }
}
