package com.jscheng.spluto.util;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.IdRes;

import java.io.*;

/**
 * Created by chengjunsen on 2018/10/30.
 */
public class FileUtil {
    public static String readAsset(Context context, String fileName) {
        StringBuilder content = new StringBuilder();
        BufferedReader reader = null;
        try {
            InputStream inputStream = context.getResources().getAssets().open(fileName);
            String line;
            InputStreamReader bufferReader = new InputStreamReader(inputStream);
            reader = new BufferedReader(bufferReader);
            while ((line = reader.readLine()) != null) {
                content.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    public static String readFile(String path) {
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try {
            String line;
            reader = new BufferedReader(new FileReader(path));
            while ((line = reader.readLine()) != null) {
                content.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    public static void overwiteFile(String path, String text) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(path));
            writer.write(text);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getDiskCachePath(Context context){
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return cachePath;
    }
}
