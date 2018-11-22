package com.jscheng.spluto.view.span;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Size;

import com.jscheng.spluto.view.Span;
import com.jscheng.spluto.view.resource.FontResource;
import com.jscheng.spluto.view.resource.IconResource;

/**
 * Created By Chengjunsen on 2018/11/22
 */
public class TodoHeadSpan extends Span {
    private Paint mHeadPaint;
    private int checkboxHeight;
    private int checkboxWidth;
    private Bitmap checkboxBitmap;
    private boolean isChecked;
    private int mLineHeight;

    public TodoHeadSpan(boolean isChecked) {
        super(SpanType.HEAD_SPAN);
        this.mHeadPaint = new Paint();
        this.isChecked = isChecked;
        loadCheckBoxBitmap();
    }

    private void loadCheckBoxBitmap() {
        Size size = IconResource.loadDefaultCheckBoxSize();
        mLineHeight = FontResource.getFontHeight(0);
        checkboxHeight = size.getHeight();
        checkboxWidth = size.getWidth();
        checkboxBitmap = isChecked ? IconResource.loadCheckBoxBitmap() : IconResource.loadUnCheckBoxBitmap();
    }

    @Override
    public void measure(int maxWidth, int maxHeight) {
        setWidth(checkboxWidth);
        setHeight(checkboxHeight);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.save();
        canvas.translate(getX(), getY());
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
    public void layout(int left, int top, int right, int bottom) {
        setX(left);
        setY(top);
    }
}
