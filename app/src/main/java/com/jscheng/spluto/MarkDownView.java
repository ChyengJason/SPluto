package com.jscheng.spluto;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.jscheng.spluto.view.Panel;
import com.jscheng.spluto.view.PanelGroup;
import com.jscheng.spluto.view.PanelParser;
import com.jscheng.spluto.view.resource.BitmapResource;
import com.jscheng.spluto.view.resource.IconResource;
import com.jscheng.spluto.view.resource.FontResouce;
import com.jscheng.spluto.view.resource.PaddingResouce;
import java.util.List;

/**
 * Created By Chengjunsen on 2018/11/16
 */
public class MarkDownView extends View implements BitmapResource.BitmapResourceListener {
    private static final String TAG = "CJS";
    private PanelGroup mPanelGroup;

    public MarkDownView(Context context) {
        super(context);
        init(context);
    }

    public MarkDownView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MarkDownView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mPanelGroup = new PanelGroup();
        FontResouce.register(context);
        PaddingResouce.register(context);
        IconResource.register(context);
        BitmapResource.register(context);
        BitmapResource.setTaskListener(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        FontResouce.unRegister();
        PaddingResouce.unRegister();
        IconResource.unRegister();
        BitmapResource.unRegister();
    }

    public void setMarkDownSource(String content) {
        List<Panel> panels = PanelParser.parser(content);
        mPanelGroup.setPanels(panels);
        postInvalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        mPanelGroup.measure(width, height);
        width = Math.max(width, mPanelGroup.getWidth());
        height = mPanelGroup.getHeight();
        Log.d(TAG, "MarkDownView onMeasure: " + width + "x" + height);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mPanelGroup.layout();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect visibleRect = new Rect();
        getLocalVisibleRect(visibleRect);
        int x1 = visibleRect.left;
        int y1 = visibleRect.top;
        int x2 = visibleRect.right;
        int y2 = visibleRect.bottom;
        mPanelGroup.draw(canvas, x1, y1, x2, y2);
    }

    @Override
    public void taskBitmapFinish(String url) {
        Log.e(TAG, "taskBitmapFinish: " + url);
        requestLayout();
    }

    @Override
    public void taksBitmapFailed(String error, String url) {
        Log.e(TAG, "taksBitmapFailed: error: " + error + " url: " + url );
    }
}
