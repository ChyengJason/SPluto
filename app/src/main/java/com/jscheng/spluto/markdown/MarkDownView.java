package com.jscheng.spluto.markdown;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Scroller;

import com.jscheng.spluto.ImageActivity;
import com.jscheng.spluto.markdown.view.Panel;
import com.jscheng.spluto.markdown.view.PanelGroup;
import com.jscheng.spluto.markdown.view.PanelBuilder;
import com.jscheng.spluto.markdown.view.part.Part;
import com.jscheng.spluto.markdown.view.part.PartType;
import com.jscheng.spluto.markdown.view.resource.BitmapResource;
import com.jscheng.spluto.markdown.view.resource.IconResource;
import com.jscheng.spluto.markdown.view.resource.FontResource;
import com.jscheng.spluto.markdown.view.resource.PaddingResouce;
import java.util.List;

/**
 * Created By Chengjunsen on 2018/11/16
 */
public class MarkDownView extends View implements BitmapResource.BitmapResourceListener{
    private static final String TAG = "CJS";
    private PanelGroup mPanelGroup;
    private VelocityTracker mVelocityTracker;
    private Scroller mScroller;
    private ViewConfiguration mViewConfiguration;
    private Rect mVisibleRect;

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
        this.mVelocityTracker = VelocityTracker.obtain();
        this.mScroller = new Scroller(context);
        this.mViewConfiguration = ViewConfiguration.get(context);
        this.mVisibleRect = new Rect();
        this.setClickable(true);
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
        getLocalVisibleRect(mVisibleRect);
        Log.e(TAG, "onLayout: " + mVisibleRect.width() + " " + mVisibleRect.height());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        getLocalVisibleRect(mVisibleRect);
        mPanelGroup.draw(canvas, mVisibleRect.left, mVisibleRect.top, mVisibleRect.right, mVisibleRect.bottom);
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

    private float lastY;
    private float currentY;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mVelocityTracker.addMovement(event);
        currentY = event.getY();
        int deltaY = 0;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                lastY = currentY;
                return true;
            case MotionEvent.ACTION_MOVE:
                deltaY = (int)(lastY - currentY);
                int resultY = getScrollY() + deltaY;
                // 滑动到顶部 mScrollY == 0
                if (resultY < 0) {
                    resultY = 0;
                }
                // 滑动到底部 mScrollY + 可见高度 == 总的height
                else if (resultY > getHeight() - mVisibleRect.height()) {
                    resultY = getHeight() - mVisibleRect.height();
                }
                scrollTo(getScrollX(), resultY);
                lastY = currentY;
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                mVelocityTracker.computeCurrentVelocity(1000);
                int yVelocity = (int) mVelocityTracker.getYVelocity();
                deltaY = (int)(lastY - currentY);
                if (Math.abs(yVelocity) > mViewConfiguration.getScaledMinimumFlingVelocity()) {
                    mScroller.fling(0, getScrollY(), 0, -yVelocity, 0, 0, 0, getHeight() - mVisibleRect.height());
                    invalidate();
                } else if ( Math.abs(deltaY) < mViewConfiguration.getScaledTouchSlop()) {
                    tapUp(event);
                }
                lastY = currentY;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    private void tapUp(MotionEvent e) {
        float x = e.getX() + getScrollX();
        float y = e.getY() + getScrollY();
        Log.e(TAG, "tapUp: x: " + x + " y: " + y);
        Part part = mPanelGroup.getPart(x, y);
        if (part != null) {
            onClick(part);
        }
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }

    /**
     * 外部统一处理点击事件，可以抛出到Activity处理
     * @param part
     */
    private void onClick(Part part) {
        Log.e(TAG, "onSingleTapUp: " + part.getPartType() + " -> " + part.getText());
        PartType type = part.getPartType();
        switch (part.getPartType()) {
            case PART_IMAGE:
                checkJumpPicture(part.getDescripe(), part.getUrl());
                break;
            case PART_LINK:
                checkJumpUrl(part.getDescripe(), part.getUrl());
                break;
            default:
                break;
        }
    }

    private void checkJumpPicture(String descripe, String url) {
        String path = BitmapResource.getBitmapPath(url);
        Intent intent = new Intent(getContext(), ImageActivity.class);
        intent.putExtra("path", path);
        getContext().startActivity(intent);
    }

    private void checkJumpUrl(String descripe, final String url) {
        new AlertDialog.Builder(getContext()).setTitle("提示")
                .setMessage("确定打开" + url + "?")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String mUrl = url;
                        if (!mUrl.startsWith("http://") && !mUrl.startsWith("https://")) {
                            mUrl = ("http://") + mUrl;
                        }
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mUrl));
                        getContext().startActivity(intent);
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                }).show();
    }
}
