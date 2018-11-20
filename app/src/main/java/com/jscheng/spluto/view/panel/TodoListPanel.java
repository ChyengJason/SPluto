package com.jscheng.spluto.view.panel;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Size;

import com.jscheng.spluto.view.resource.IconResource;
import com.jscheng.spluto.view.resource.FontResouce;
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
    private int mLineHeight;

    public TodoListPanel(boolean isChecked) {
        this.mHeadPaint = new Paint();
        this.isChecked = isChecked;
        loadCheckBoxBitmap();
    }

    private void loadCheckBoxBitmap() {
        Size size = IconResource.loadDefaultCheckBoxSize();
        mLineHeight = FontResouce.getFontHeight(0);
        checkboxHeight = size.getHeight();
        checkboxWidth = size.getWidth();
        checkboxBitmap = isChecked ? IconResource.loadCheckBoxBitmap() : IconResource.loadUnCheckBoxBitmap();
    }

    @Override
    protected void drawHead(Canvas canvas) {
        canvas.save();
        canvas.translate(getX(), getY() + PaddingResouce.getPannelSpacingPx());
        if (checkboxBitmap != null) {
            int destTop = (mLineHeight - checkboxHeight) / 2;
            int destBottom = destTop + checkboxHeight;
            Rect resRect = new Rect(0, 0, checkboxBitmap.getWidth(), checkboxBitmap.getHeight());
            Rect destrect = new Rect(0, destTop, checkboxWidth, destBottom);
            canvas.drawBitmap(checkboxBitmap, resRect, destrect, mHeadPaint);
        }
        canvas.restore();
    }

    @Override
    protected int getHeadWidth() {
        return checkboxWidth;
    }
}
