package com.jscheng.spluto.view.resource;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.LruCache;
import android.util.Size;
import android.widget.ImageView;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
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
import java.util.HashMap;
import java.util.List;

/**
 * Created By Chengjunsen on 2018/11/20
 */
public class BitmapResource  {
    private static final String TAG = "CJS";
    private static final String DiskLruCacheDirName = "bitmap";
    private static BitmapResource instance;
    private Context mContext;
    /**
     * URL网络失败最大重试次数
     */
    private static final int MaxUrlFailedTime = 2;
    /**
     * 压缩过的图片尺寸缓存
     */
    private HashMap<String, Size> mSizeCache;
    /**
     * 内存图片缓存
     */
    private LruCache<String, Bitmap> mMemoryCache;
    /**
     * 磁盘图片缓存
     */
    private DiskLruCache mDiskCache;
    /**
     * 最大内存缓存空间
     */
    private int mMemoryCacheSize;
    /**
     * 最大磁盘缓存空间
     */
    private int mDiskCacheSize;
    /**
     * 正在请求下载的url集合
     */
    private List<String> mRequestingUrls;
    /**
     * 失败的URL的key和失败次数
     */
    private HashMap<String, Integer> mFailedUrls;
    private BitmapResourceListener mListener;
    private RequestQueue mRequestQueue;

    private BitmapResource(Context context) {
        this.mContext = context;
        this.mRequestingUrls = new ArrayList<>();
        this.mFailedUrls = new HashMap<>();
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

    /**
     * 获取Bitmap尺寸
     * 1. 若mSizeCache中，直接返回图片尺寸
     * 2. 若图片在内存中，将尺寸加入mSizeCache，返回图片尺寸
     * 2. 若图片在磁盘中，根据maxwidth计算压缩后的图片尺寸，将尺寸加入mSizeCache，返回图片尺寸
     * 3. 若不存在，加入网络加载任务
     * @param url
     * @param maxWidth
     * @return
     */
    public static Size getBitmapSize(String url, int maxWidth) {
        if (instance != null) {
            return instance.getBitmapSizeImpl(url, maxWidth);
        }
        Log.e(TAG, "getBitmapSize: instance is not registered");
        return new Size(0 , 0);
    }

    /**
     * 获取bitmap
     * 1. 若图片在内存中，返回图片
     * 2. 若图片在磁盘中，根据maxwidth计算压缩后的图片尺寸，并加载压缩图片到内存，返回图片
     * 3. 若不存在，加入网络加载任务，返回null
     * @param url
     * @param maxWidth
     */
    public static Bitmap getBitmap(String url, int maxWidth) {
        if (instance != null) {
            return instance.getBitmapImpl(url, maxWidth);
        }
        Log.e(TAG, "getBitmap: instance is not registered");
        return null;
    }

    private void initCache() {
        mSizeCache = new HashMap<>();
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

    private Size getBitmapSizeImpl(String url, int maxWidth) {
        String key = getKeyFromUrl(url);
        if (mSizeCache.containsKey(key)) {
            return mSizeCache.get(key);
        }

        Bitmap bitmap = getMemoryCacheBitmap(key);
        if (bitmap != null) {
            Size size = new Size(bitmap.getWidth(), bitmap.getHeight());
            mSizeCache.put(key, size);
            return size;
        }

        bitmap = getDiskCacheBitmap(key, maxWidth);
        if (bitmap != null) {
            Size size = new Size(bitmap.getWidth(), bitmap.getHeight());
            mSizeCache.put(key, size);
            return new Size(bitmap.getWidth(), bitmap.getHeight());
        }

        createBitmapTask(url, key, maxWidth);
        return new Size(0, 0);
    }

    private Bitmap getBitmapImpl(String url, int maxWidth) {
        String key = getKeyFromUrl(url);
        Bitmap bitmap = getMemoryCacheBitmap(key);
        if (bitmap != null) {
            return bitmap;
        }
        bitmap = getDiskCacheBitmap(key, maxWidth);
        if (bitmap != null) {
            mMemoryCache.put(key, bitmap);
            mSizeCache.put(key, new Size(bitmap.getWidth(), bitmap.getHeight()));
            return bitmap;
        }
        createBitmapTask(url, key, maxWidth);
        return null;
    }

    private Bitmap getMemoryCacheBitmap(String key) {
        return mMemoryCache.get(key);
    }

    private Bitmap getDiskCacheBitmap(String key, int maxWidth) {
        try {
            DiskLruCache.Snapshot snapshot = mDiskCache.get(key);
            if (snapshot != null) {
                FileInputStream inputStream = (FileInputStream) snapshot.getInputStream(0);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeStream(inputStream, null, options);
                inputStream.close();
                int bitmapWidth = options.outWidth;

                snapshot = mDiskCache.get(key);
                inputStream = (FileInputStream) snapshot.getInputStream(0);
                options.inSampleSize = bitmapWidth > maxWidth ? Math.round((float)bitmapWidth/(float)maxWidth) : 1;
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

    private void createBitmapTask(final String url, final String key, int maxWidth) {
        if (mRequestingUrls.contains(url)) {
            Log.e(TAG, "createBitmapTask: is requesting");
        } else if (mFailedUrls.containsKey(key) && mFailedUrls.get(key) >= MaxUrlFailedTime) {
            Log.e(TAG, "createBitmapTask: is max failed time");
        } else {
            Log.e(TAG, "createBitmapTask: key" + key + " url: " + url );
            mRequestingUrls.add(url);
            BitmapRequest request = new BitmapRequest(url, key, new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap bitmap) {
                    if (mListener != null) {
                        mListener.taskBitmapFinish(url);
                    }
                    mRequestingUrls.remove(url);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if (!mFailedUrls.containsKey(key)) {
                        mFailedUrls.put(key, 1);
                    } else {
                        int failedTime = mFailedUrls.get(key);
                        mFailedUrls.put(key, failedTime + 1);
                    }
                    if (mListener != null) {
                        mListener.taksBitmapFailed(url, error.getMessage());
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
        void taksBitmapFailed(String url, String error);
    }

    private class BitmapRequest extends ImageRequest {
        private String key;
        public BitmapRequest(String url, String key, Response.Listener<Bitmap> listener, Response.ErrorListener errorListener) {
            super(url, listener, 0, 0, ImageView.ScaleType.CENTER_INSIDE, Bitmap.Config.RGB_565, errorListener);
            this.key = key;
        }

        @Override
        protected Response<Bitmap> parseNetworkResponse(NetworkResponse response) {
            Log.e(TAG, "parseNetworkResponse: " + Thread.currentThread());
            byte[] data = response.data;
            try {
                if (data == null || data.length <= 0) {
                    Log.e(TAG, "parseNetworkResponse: data is null");
                    return Response.error(new ParseError(response));
                }
                BitmapFactory.Options decodeOptions = new BitmapFactory.Options();
                decodeOptions.inJustDecodeBounds = true;
                BitmapFactory.decodeByteArray(data, 0, data.length, decodeOptions);
                int actualWidth = decodeOptions.outWidth;
                int actualHeight = decodeOptions.outHeight;
                if (actualWidth <= 0 || actualHeight <= 0) {
                    Log.e(TAG, "parseNetworkResponse: is not image");
                    return Response.error(new ParseError(response));
                }
                DiskLruCache.Editor editor = mDiskCache.edit(key);
                OutputStream outputStream = editor.newOutputStream(0);
                outputStream.write(data);
                outputStream.close();
                editor.commit();
                mDiskCache.flush();
            } catch (IOException e) {
                e.printStackTrace();
                return Response.error(new ParseError(response));
            }
            return Response.success(null, HttpHeaderParser.parseCacheHeaders(response));
        }
    }
}
