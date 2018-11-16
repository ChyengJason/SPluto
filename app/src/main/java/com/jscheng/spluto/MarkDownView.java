package com.jscheng.spluto;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.jscheng.spluto.view.Panel;
import com.jscheng.spluto.view.PanelGroup;
import com.jscheng.spluto.view.PanelParser;

import java.util.List;

/**
 * Created By Chengjunsen on 2018/11/16
 */
public class MarkDownView extends View {
    private static final String TAG = "CJS";
    private PanelGroup mPanelGroup;
    public MarkDownView(Context context) {
        super(context);
        init();
    }

    public MarkDownView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MarkDownView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        this.mPanelGroup = new PanelGroup();
    }

    public void setMarkDownSource(String content) {
        List<Panel> panels = PanelParser.parser(content);
        mPanelGroup.setPanels(panels);
        invalidate();
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
        height = Math.max(height, mPanelGroup.getHeight());
        Log.d(TAG, "MarkDownView onMeasure: " + width + "x" + height);
        setMeasuredDimension(width, height);
    }
}
