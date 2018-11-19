package com.jscheng.spluto.view.resource;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Size;

import com.jscheng.spluto.R;
import com.jscheng.spluto.util.DipUtil;

import java.lang.ref.WeakReference;

public class BitmapResource {
    public static WeakReference<Context> mContext;
    public static final int DEFAULT_IMAGE_WIDTH_DP = 100;
    public static final int DEFAULT_CHECKBOX_WIDTH_DP = 18;
    public static final int DEFAULT_QUOTE_WIDTH_DP = 5;

    private static int DEFAULT_IMAGE_WIDTH_PX;
    private static int DEFAULT_CHECKBOX_WIDTH_PX;
    private static int DEFAULT_QUOTE_WIDTH_PX;

    public static void register(Context context) {
        mContext = new WeakReference<>(context);
        DEFAULT_IMAGE_WIDTH_PX = DipUtil.dp2px(context, DEFAULT_IMAGE_WIDTH_DP);
        DEFAULT_CHECKBOX_WIDTH_PX = DipUtil.dp2px(context, DEFAULT_CHECKBOX_WIDTH_DP);
        DEFAULT_QUOTE_WIDTH_PX = DipUtil.dp2px(context, DEFAULT_QUOTE_WIDTH_DP);
    }

    public static Size loadDefaultBitmapSize() {
        return new Size(DEFAULT_IMAGE_WIDTH_PX, DEFAULT_IMAGE_WIDTH_PX);
    }

    public static Size loadDefaultCheckBoxSize() {
        return new Size(DEFAULT_CHECKBOX_WIDTH_PX, DEFAULT_CHECKBOX_WIDTH_PX);
    }

    public static int loadDefaultQuoteWidth() {
        return DEFAULT_QUOTE_WIDTH_PX;
    }

    public static Bitmap loadDefaultBitmap(int sampleSize) {
        Bitmap bitmap = null;
        Context context = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inSampleSize = sampleSize;
        if ((context = mContext.get()) != null) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.image_loading, options);
        }
        return bitmap;
    }

    public static Bitmap loadCheckBoxBitmap() {
        Bitmap bitmap = null;
        Context context = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        if ((context = mContext.get()) != null) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.todo_list_check, options);
        }
        return bitmap;
    }

    public static Bitmap loadUnCheckBoxBitmap() {
        Bitmap bitmap = null;
        Context context = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        if ((context = mContext.get()) != null) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.todo_list_uncheck, options);
        }
        return bitmap;
    }
}
