package com.jscheng.spluto;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ImageActivity extends AppCompatActivity {
    private static final String TAG = "CJS";
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        mImageView = findViewById(R.id.image_view);
        mImageView.post(new Runnable() {
            @Override
            public void run() {
                loadImage(getIntent());
            }
        });
    }

    private void loadImage(Intent intent) {
        if (intent == null) {
            return;
        }
        String path = intent.getStringExtra("path");
        if (path == null || path.isEmpty()) {
            return;
        }
        Bitmap bitmap = loadBitmap(path);
        if (bitmap != null) {
            mImageView.setImageBitmap(bitmap);
        }
    }

    private Bitmap loadBitmap(String path) {
        try {
            int width = mImageView.getWidth();
            Log.e(TAG, "loadBitmap: " + path + " " + width);
            FileInputStream inputStream = new FileInputStream(path);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(inputStream, null, options);
            inputStream.close();
            int bitmapWidth = options.outWidth;
            inputStream = new FileInputStream(path);
            options.inSampleSize = bitmapWidth > width ? Math.round((float)bitmapWidth/(float)width) : 1;
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            options.inJustDecodeBounds = false;
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, options);
            inputStream.close();
            return bitmap;
        } catch (FileNotFoundException e) {
            Toast.makeText(this, "图片不存在", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
