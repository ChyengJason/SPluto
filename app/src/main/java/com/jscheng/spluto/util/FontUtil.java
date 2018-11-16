package com.jscheng.spluto.util;

import android.graphics.Paint;

/**
 * Created by chengjunsen on 2018/11/16.
 */
public class FontUtil {

    public static double getFontWidth(Paint paint, String text) {
        return paint.measureText(text);
    }

    public static double getFontHeight(Paint paint) {
        Paint.FontMetrics fm = paint.getFontMetrics();
        return fm.descent - fm.ascent;
    }
}
