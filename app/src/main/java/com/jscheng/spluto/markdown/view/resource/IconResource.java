package com.jscheng.spluto.markdown.view.resource;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Size;

import com.jscheng.spluto.R;
import com.jscheng.spluto.util.DipUtil;

import java.lang.ref.WeakReference;

public class IconResource {
    private static WeakReference<Context> mContext;
    private static final int DEFAULT_IMAGE_WIDTH_DP = 100;
    private static int DEFAULT_IMAGE_WIDTH_PX;
    private static Bitmap DEFAULT_IMAGE_LOADING_BITMAP;
    private static Bitmap DEFAULT_IMAGE_ERROR_BITMAP;
    private static final int DEFAULT_CHECKBOX_WIDTH_DP = 18;
    private static int DEFAULT_CHECKBOX_WIDTH_PX;
    private static Bitmap DEFAULT_CHECKBOX_BITMAP;
    private static Bitmap DEFAULT_UNCHECKBOX_BITMAP;

    public static void register(Context context) {
        mContext = new WeakReference<>(context);
        DEFAULT_IMAGE_WIDTH_PX = DipUtil.dp2px(context, DEFAULT_IMAGE_WIDTH_DP);
        DEFAULT_CHECKBOX_WIDTH_PX = DipUtil.dp2px(context, DEFAULT_CHECKBOX_WIDTH_DP);
        DEFAULT_IMAGE_LOADING_BITMAP = null;
        DEFAULT_IMAGE_ERROR_BITMAP = null;
        DEFAULT_CHECKBOX_BITMAP = null;
    }

    public static Size loadDefaultBitmapSize() {
        return new Size(DEFAULT_IMAGE_WIDTH_PX, DEFAULT_IMAGE_WIDTH_PX);
    }

    public static Size loadDefaultCheckBoxSize() {
        return new Size(DEFAULT_CHECKBOX_WIDTH_PX, DEFAULT_CHECKBOX_WIDTH_PX);
    }

    public static Bitmap loadDefualtLoadingBitmap() {
        if (DEFAULT_IMAGE_LOADING_BITMAP != null) {
            return DEFAULT_IMAGE_LOADING_BITMAP;
        }
        Context context;
        if ((context = mContext.get()) != null) {
            DEFAULT_IMAGE_LOADING_BITMAP = BitmapFactory.decodeResource(context.getResources(), R.mipmap.image_loading);
        }
        return DEFAULT_IMAGE_LOADING_BITMAP;
    }

    public static void unRegister() {
        if (DEFAULT_IMAGE_LOADING_BITMAP != null) {
            DEFAULT_IMAGE_LOADING_BITMAP.recycle();
            DEFAULT_IMAGE_LOADING_BITMAP = null;
        }
        if (DEFAULT_IMAGE_ERROR_BITMAP != null) {
            DEFAULT_IMAGE_ERROR_BITMAP.recycle();
            DEFAULT_IMAGE_ERROR_BITMAP = null;
        }
        if (DEFAULT_CHECKBOX_BITMAP != null) {
            DEFAULT_CHECKBOX_BITMAP.recycle();
            DEFAULT_CHECKBOX_BITMAP = null;
        }
        if (DEFAULT_UNCHECKBOX_BITMAP != null) {
            DEFAULT_UNCHECKBOX_BITMAP.recycle();
            DEFAULT_UNCHECKBOX_BITMAP = null;
        }
    }

    public static Bitmap loadDefualtErrorBitmap() {
        if (DEFAULT_IMAGE_ERROR_BITMAP != null) {
            return DEFAULT_IMAGE_ERROR_BITMAP;
        }
        Context context;
        if ((context = mContext.get()) != null) {
            DEFAULT_IMAGE_ERROR_BITMAP = BitmapFactory.decodeResource(context.getResources(), R.mipmap.image_load_error);
        }
        return DEFAULT_IMAGE_ERROR_BITMAP;
    }

    public static Bitmap loadCheckBoxBitmap() {
        if (DEFAULT_CHECKBOX_BITMAP != null) {
            return DEFAULT_CHECKBOX_BITMAP;
        }
        Context context;
        if ((context = mContext.get()) != null) {
            DEFAULT_CHECKBOX_BITMAP = BitmapFactory.decodeResource(context.getResources(), R.mipmap.todo_list_check);
        }
        return DEFAULT_CHECKBOX_BITMAP;
    }

    public static Bitmap loadUnCheckBoxBitmap() {
        if (DEFAULT_UNCHECKBOX_BITMAP != null) {
            return DEFAULT_UNCHECKBOX_BITMAP;
        }
        Context context;
        if ((context = mContext.get()) != null) {
            DEFAULT_UNCHECKBOX_BITMAP = BitmapFactory.decodeResource(context.getResources(), R.mipmap.todo_list_uncheck);
        }
        return DEFAULT_UNCHECKBOX_BITMAP;
    }

}
