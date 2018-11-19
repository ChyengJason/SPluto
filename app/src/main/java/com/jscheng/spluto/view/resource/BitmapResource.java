package com.jscheng.spluto.view.resource;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Size;

import com.jscheng.spluto.R;

import java.lang.ref.WeakReference;

public class BitmapResource {
    public static WeakReference<Context> mContext;

    public static void register(Context context) {
        mContext = new WeakReference<>(context);
    }

    public static Bitmap loadDefaultBitmap(int sampleSize) {
        Bitmap bitmap = null;
        Context context = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inSampleSize = sampleSize;
        if ((context = mContext.get()) != null) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher, options);
        }
        return bitmap;
    }

    public static Size loadDefaultBitmapSize() {
        Context context = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        if ((context = mContext.get()) != null) {
            BitmapFactory.decodeResource(context.getResources() ,R.mipmap.ic_launcher, options);
            int imageHeight = options.outHeight;
            int imageWidth = options.outWidth;
            String imageType = options.outMimeType;
            return new Size(imageWidth, imageHeight);
        }
        return new Size(0, 0);
    }
}
