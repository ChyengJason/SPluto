package com.jscheng.spluto.view.resource;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.util.LruCache;
import android.util.Size;
import android.widget.ImageView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.jakewharton.disklrucache.DiskLruCache;
import com.jscheng.spluto.util.FileUtil;
import com.jscheng.spluto.util.MdUtil;
import com.jscheng.spluto.util.VersionUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created By Chengjunsen on 2018/11/20
 */
public class BitmapResource  {
    private static final String TAG = "CJS";
    private static final String DiskLruCacheDirName = "bitmap";
    private static BitmapResource instance;
    private LruCache<String, Bitmap> mMemoryCache;
    private List<String> mRequestingUrls;
    private DiskLruCache mDiskCache;
    private int mMemoryCacheSize;
    private int mDiskCacheSize;
    private Context mContext;
    private BitmapResourceListener mListener;
    private RequestQueue mRequestQueue;

    private BitmapResource(Context context) {
        this.mContext = context;
        this.mRequestingUrls = new ArrayList<>();
        initCache();
        initVolley();
    }

    public static void register(Context context) {
        instance = new BitmapResource(context);
    }

    public static void unRegister() {
        try {
            instance.mContext = null;
            if (instance.mRequestQueue != null) {
                instance.mRequestQueue.cancelAll(null);
                instance.mRequestQueue = null;
            }
            if (instance.mDiskCache != null && !instance.mDiskCache.isClosed()) {
                instance.mDiskCache.close();
            }
            instance = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setTaskListener(BitmapResourceListener listener) {
        if (instance != null) {
            instance.mListener = listener;
        } else {
            Log.e(TAG, "setTaskListener: instance is not registered");
        }
    }

    public static Size getBitmapSize(String url, int maxWidth) {
        if (instance != null) {
            return instance.getBitmapSizeImpl(url, maxWidth);
        }
        Log.e(TAG, "getBitmapSize: instance is not registered");
        return new Size(0 , 0);
    }

    public static Bitmap getBitmap(String url, int maxWidth) {
        if (instance != null) {
            return instance.getBitmapImpl(url, maxWidth);
        }
        Log.e(TAG, "getBitmap: instance is not registered");
        return null;
    }

    private void initCache() {
        mMemoryCacheSize = (int)Runtime.getRuntime().maxMemory() / 8;
        mMemoryCache = new LruCache<String, Bitmap>(mMemoryCacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };
        mDiskCacheSize = 10 * 1024 * 1024;
        File diskCacheFile = getDiskCacheDir(mContext, DiskLruCacheDirName);
        try {
            mDiskCache = DiskLruCache.open(diskCacheFile, VersionUtil.getVersionCode(mContext), 1, mDiskCacheSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initVolley() {
        mRequestQueue = Volley.newRequestQueue(mContext);
    }

    /**
     * 获取Bitmap尺寸
     * 1. 若在内存中，返回图片尺寸
     * 2. 若在磁盘中，根据maxwidth计算压缩后的图片尺寸，并加载压缩图片到内存，返回图片尺寸
     * 3. 若不存在，加入网络加载任务
     * @param url
     * @param maxWidth
     * @return
     */
    private Size getBitmapSizeImpl(String url, int maxWidth) {
        Log.e(TAG, "getBitmapSizeImpl: " );
        Bitmap bitmap = null;
        if ((bitmap = getMemoryCacheBitmap(url)) != null) {
            return new Size(bitmap.getWidth(), bitmap.getHeight());
        }
        if ((bitmap = getDiskCacheBitmap(url, maxWidth)) != null) {
            mMemoryCache.put(url, bitmap);
            return new Size(bitmap.getWidth(), bitmap.getHeight());
        }
        createBitmapTask(url, maxWidth);
        return new Size(0, 0);
    }

    /**
     * 获取bitmap
     * 1. 若在内存中，返回图片
     * 2. 若在磁盘中，根据maxwidth计算压缩后的图片尺寸，并加载压缩图片到内存，返回图片
     * 3. 若不存在，加入网络加载任务，返回null
     * @param url
     * @param maxWidth
     */
    private Bitmap getBitmapImpl(String url, int maxWidth) {
        Log.e(TAG, "getBitmapImpl: " );
        Bitmap bitmap = null;
        if ((bitmap = getMemoryCacheBitmap(url)) != null) {
            return bitmap;
        }
        if ((bitmap = getDiskCacheBitmap(url, maxWidth)) != null) {
            mMemoryCache.put(url, bitmap);
            return bitmap;
        }
        createBitmapTask(url, maxWidth);
        return null;
    }

    private Bitmap getMemoryCacheBitmap(String url) {
        Log.e(TAG, "getMemoryCacheBitmap: ");
        String key = getKeyFromUrl(url);
        return mMemoryCache.get(key);
    }

    private Bitmap getDiskCacheBitmap(String url, int maxWidth) {
        Log.e(TAG, "getDiskCacheBitmap: ");
        try {
            String key = getKeyFromUrl(url);
            DiskLruCache.Snapshot snapshot = mDiskCache.get(key);
            if (snapshot != null) {
                FileInputStream inputStream = (FileInputStream) snapshot.getInputStream(0);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeStream(inputStream, null, options);
                inputStream.close();

                snapshot = mDiskCache.get(key);
                inputStream = (FileInputStream) snapshot.getInputStream(0);
                options.inSampleSize = options.outWidth > maxWidth ? Math.round((float)options.outWidth/(float)maxWidth) : 1;
                options.inPreferredConfig = Bitmap.Config.RGB_565;
                options.inJustDecodeBounds = false;
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, options);
                if (bitmap != null) {
                    mMemoryCache.put(key, bitmap);
                }
                return bitmap;
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void createBitmapTask(final String url, int maxWidth) {
        if (mRequestingUrls.contains(url)) {
            Log.e(TAG, "createBitmapTask: is requesting");
        } else {
            Log.e(TAG, "createBitmapTask: key" + getKeyFromUrl(url) + " url: " + url );
            mRequestingUrls.add(url);
            ImageRequest request = new ImageRequest(url, new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap bitmap) {
                    try {
                        if (bitmap == null || bitmap.isRecycled()) {
                            Log.e(TAG, "onResponse: bitmap is null or recycled");
                            return;
                        }
                        String key = getKeyFromUrl(url);
                        DiskLruCache.Editor editor = mDiskCache.edit(key);
                        OutputStream outputStream = editor.newOutputStream(0);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                        outputStream.close();
                        editor.commit();
                        mDiskCache.flush();
                        if (mListener != null) {
                            mListener.taskBitmapFinish(url);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        if (mListener != null) {
                            mListener.taksBitmapFailed(e.getMessage());
                        }
                    } finally {
                        mRequestingUrls.remove(url);
                    }
                }
            }, 0, 0, ImageView.ScaleType.CENTER_INSIDE, Bitmap.Config.RGB_565,
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            if (mListener != null) {
                                mListener.taksBitmapFailed(error.getMessage());
                            }
                        }
                    });
            mRequestQueue.add(request);
        }
    }

    private File getDiskCacheDir(Context context, String uniqueName){
        String cachePath = FileUtil.getDiskCachePath(context);
        Log.e(TAG, "getDiskCacheDir: " + cachePath + File.separator +uniqueName);
        return new File(cachePath + File.separator +uniqueName);
    }

    private String getKeyFromUrl(String url) {
        return MdUtil.encode(url);
    }

    public interface BitmapResourceListener {
        void taskBitmapFinish(String url);
        void taksBitmapFailed(String error);
    }
}
