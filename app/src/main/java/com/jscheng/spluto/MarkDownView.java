package com.jscheng.spluto;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.jscheng.spluto.view.Panel;
import com.jscheng.spluto.view.PanelGroup;
import com.jscheng.spluto.view.PanelBuilder;
import com.jscheng.spluto.view.part.Part;
import com.jscheng.spluto.view.resource.BitmapResource;
import com.jscheng.spluto.view.resource.IconResource;
import com.jscheng.spluto.view.resource.FontResource;
import com.jscheng.spluto.view.resource.PaddingResouce;
import java.util.List;

/**
 * Created By Chengjunsen on 2018/11/16
 */
public class MarkDownView extends View implements BitmapResource.BitmapResourceListener, GestureDetector.OnGestureListener {
    private static final String TAG = "CJS";
    private PanelGroup mPanelGroup;
    private GestureDetector mGestureDetector;
    private int lastActiveX;
    private int lastActiveY;

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
        this.setClickable(true);
        this.mGestureDetector = new GestureDetector(this.getContext(), this);
        FontResource.register(context);
        PaddingResouce.register(context);
        IconResource.register(context);
        BitmapResource.register(context);
        BitmapResource.setTaskListener(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        FontResource.unRegister();
        PaddingResouce.unRegister();
        IconResource.unRegister();
        BitmapResource.unRegister();
    }

    public void setMarkDownSource(String content) {
        List<Panel> panels = new PanelBuilder().build(content);
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
        mPanelGroup.draw(canvas, visibleRect.left, visibleRect.top,  visibleRect.right, visibleRect.bottom);
    }

    @Override
    public void taskBitmapFinish(String url) {
        Log.e(TAG, "taskBitmapFinish: " + url);
        requestLayout();
    }

    @Override
    public void taksBitmapFailed(String url, String error) {
        Log.e(TAG, "taksBitmapFailed: error: " + error + " url: " + url );
    }

    public void scrollChanged(int x, int y, int oldx, int oldy) {
        Rect visibleRect = new Rect();
        getLocalVisibleRect(visibleRect);
        if (Math.abs(y - lastActiveY) >= visibleRect.height()/2) {
            lastActiveX = x;
            lastActiveY = y;
            invalidate();
            Log.e(TAG, "scrollChanged: invalidate");
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Part part = mPanelGroup.getPart(e.getX() - getX(), e.getY() - getY());
        if (part != null) {
            Log.e(TAG, "onSingleTapUp: " + part.getPartType() + " -> " + part.getText());
        }
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}
