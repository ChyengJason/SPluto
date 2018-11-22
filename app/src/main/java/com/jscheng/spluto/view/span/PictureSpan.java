package com.jscheng.spluto.view.span;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.util.Size;
import com.jscheng.spluto.view.Span;
import com.jscheng.spluto.view.part.Part;
import com.jscheng.spluto.view.resource.BitmapResource;
import com.jscheng.spluto.view.resource.IconResource;
import com.jscheng.spluto.view.resource.ColorResource;
import com.jscheng.spluto.view.resource.FontResource;
import com.jscheng.spluto.view.resource.PaddingResouce;

public class PictureSpan extends Span {
    private static final String TAG = "CJS";
    private StaticLayout mStaticLayout;
    private SpannableStringBuilder mSpanBuilder;
    private TextPaint mTextPaint;
    private Paint mImagePaint;
    private String url;
    private String descripe;
    private int imageHeight;
    private int imageWidth;
    private boolean isDefualtImage;

    public PictureSpan() {
        super(SpanType.PICTURE_SPAN);
        this.mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        this.mImagePaint = new Paint();
        this.imageHeight = 0;
        this.imageWidth = 0;
        this.mSpanBuilder = new SpannableStringBuilder();
        this.isDefualtImage = false;
    }

    public void setPart(Part part) {
        this.url = part.getUrl();
        this.descripe = part.getDescripe();
        initSpanBuilder();
    }

    @Override
    public void measure(int maxWidth, int maxHeight) {
        mStaticLayout = new StaticLayout(mSpanBuilder, mTextPaint, maxWidth, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
        loadBitmapSize(maxWidth);
        int width = mStaticLayout.getWidth();
        int height = imageHeight + mStaticLayout.getHeight() + PaddingResouce.getPannelSpacingPx();
        setWidth(width);
        setHeight(height);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.save();
        canvas.translate(getX(), getY());
        int left = (getWidth() - imageWidth)/2;
        int right = left + imageWidth;
        Bitmap bitmap = loadBitmap(getWidth(), getHeight());
        if (bitmap != null) {
            Rect resRect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
            Rect destrect = new Rect(left, 0, right, imageHeight);
            canvas.drawBitmap(bitmap, resRect, destrect, mImagePaint);
        }
        canvas.translate(0, PaddingResouce.getPannelSpacingPx() + imageHeight);
        mStaticLayout.draw(canvas);
        canvas.restore();
    }

    @Override
    public void layout(int left, int top, int right, int bottom) {
        setX(left);
        setY(top);
    }

    private void initSpanBuilder() {
        mSpanBuilder.clearSpans();
        mSpanBuilder.append(descripe);
        setProperity(new ForegroundColorSpan(ColorResource.getImageFontColor()), 0, descripe.length());
        setProperity(new AbsoluteSizeSpan(FontResource.getImageFontSize()), 0, descripe.length());
        setProperity(new BackgroundColorSpan(ColorResource.getImageFontBackgroudColor()), 0, descripe.length());
    }

    private void setProperity(Object properitySpan, int begin, int end) {
        mSpanBuilder.setSpan(properitySpan, begin, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
    }

    private Bitmap loadBitmap(int maxWidth, int maxHeight) {
        Bitmap bitmap = isDefualtImage ? IconResource.loadDefualtLoadingBitmap() : BitmapResource.getBitmap(url, maxWidth);
        return bitmap;
    }

    private void loadBitmapSize(int maxWidth) {
        isDefualtImage = false;
        Size size = BitmapResource.getBitmapSize(url, maxWidth);
        if (size.getWidth() <= 0 || size.getHeight() <= 0 ){
            isDefualtImage = true;
            size = IconResource.loadDefaultBitmapSize();
        }
        if (size.getWidth() > maxWidth) {
            imageWidth = maxWidth;
            imageHeight = size.getHeight() * imageWidth / size.getWidth();
        } else {
            imageWidth = size.getWidth();
            imageHeight = size.getHeight();
        }
        Log.d(TAG, "loadBitmapSize: " + imageWidth + "x" + imageHeight);
    }
}