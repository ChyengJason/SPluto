package com.jscheng.spluto;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ScrollView;

/**
 * Created By Chengjunsen on 2018/11/21
 */
public class MarkdownScrollView extends ScrollView{
    private MarkDownView mContentView;

    public MarkdownScrollView(Context context) {
        super(context);
        init(context);
    }

    public MarkdownScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MarkdownScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mContentView = new MarkDownView(context);
        this.addView(mContentView, layoutParams);
    }

    public void setMarkDownSource(String source) {
        mContentView.setMarkDownSource(source);
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
//        mContentView.requestLayout();
        mContentView.invalidate();
    }
}
