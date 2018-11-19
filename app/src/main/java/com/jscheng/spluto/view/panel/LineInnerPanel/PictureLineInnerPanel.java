package com.jscheng.spluto.view.panel.LineInnerPanel;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Size;

import com.jscheng.spluto.view.Span;
import com.jscheng.spluto.view.resource.BitmapResource;
import com.jscheng.spluto.view.resource.FontResouce;
import com.jscheng.spluto.view.resource.PaddingResouce;

public class PictureLineInnerPanel extends LineInnerPanel {
    private StaticLayout mStaticLayout;
    private SpannableStringBuilder mSpanBuilder;
    private TextPaint mTextPaint;
    private Paint mImagePaint;
    private String url;
    private String descripe;
    private int imageHeight;
    private int imageWidth;
    private int imageSamperSize;

    public PictureLineInnerPanel(String url, String descripe) {
        super(LineInnerPanelType.PICTURE_INNER_PANEL);
        this.url = url;
        this.descripe = descripe;
        this.mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        this.mImagePaint = new Paint();
        this.imageHeight = 0;
        this.imageWidth = 0;

        this.mSpanBuilder = new SpannableStringBuilder();
        mSpanBuilder.append(descripe);
        mSpanBuilder.setSpan(new ForegroundColorSpan(Color.BLACK), 0, descripe.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        mSpanBuilder.setSpan(new AbsoluteSizeSpan(FontResouce.getImageFontSize()), 0, descripe.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
    }

    @Override
    public void measure(int maxWidth, int maxHeight) {
        mStaticLayout = new StaticLayout(mSpanBuilder, mTextPaint, maxWidth, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
        loadBitmapSize(maxWidth, maxHeight);
        int width = mStaticLayout.getWidth();
        int height = (int)(imageHeight + mStaticLayout.getHeight() + 2 * PaddingResouce.getPannelSpacingPx());
        setWidth(width);
        setHeight(height);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.save();
        canvas.translate(getX(), getY() + PaddingResouce.getPannelSpacingPx());
        int left = (getWidth() - imageWidth)/2;
        int right = left + imageWidth;
        Rect resRect = new Rect(0, 0, imageWidth, imageHeight);
        Rect destrect = new Rect(left, 0, right, imageHeight);
        canvas.drawBitmap(loadBitmap(), resRect, destrect, mImagePaint);
        canvas.translate(0, imageHeight);
        mStaticLayout.draw(canvas);
        canvas.restore();
    }

    @Override
    public void layout(int left, int top, int right, int bottom) {
        setX(left);
        setY(top);
    }

    private Bitmap loadBitmap() {
        return BitmapResource.loadDefaultBitmap(imageSamperSize);
    }

    private void loadBitmapSize(int maxWidth, int maxHeight) {
        Size size = BitmapResource.loadDefaultBitmapSize();
        if (size.getWidth() > maxWidth) {
            imageSamperSize = (int)((float)imageWidth / (float)maxWidth);
            imageWidth = maxWidth;
            imageHeight = (int)((float)size.getHeight() / (float)imageSamperSize);
        } else {
            imageSamperSize = 1;
            imageWidth = size.getWidth();
            imageHeight = size.getHeight();
        }
    }
}