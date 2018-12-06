package com.jscheng.spluto;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertEquals;

/**
 * Created By Chengjunsen on 2018/12/6
 */
@RunWith(AndroidJUnit4.class)
public class ResolverTest {

    @Test
    public void insert() {
        ContentResolver contentResolver = InstrumentationRegistry.getContext().getContentResolver();
        Uri uri = Uri.parse("content://com.jscheng.spluto/markdown");
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", "mName");
        contentValues.put("path", "mPath");
        contentValues.put("summary", "...");
        contentValues.put("exist", true);
        Uri resutlUri = contentResolver.insert(uri, contentValues);
        Log.v("CJS", resutlUri.getPath());
        assertEquals( true, resutlUri.getPath().length() > 0);
    }

    @Test
    public void show() {
        ContentResolver contentResolver = InstrumentationRegistry.getContext().getContentResolver();
        Uri uri = Uri.parse("content://com.jscheng.spluto/markdown");
        Cursor cursor = contentResolver.query(uri, null,  null, null, null);
        while(cursor.moveToNext()) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < cursor.getColumnCount(); i++) {
                builder.append(cursor.getString(i) + "  ");
            }
            Log.v("CJS", builder.toString());
        }
    }

    @Test
    public void delete() {
        ContentResolver contentResolver = InstrumentationRegistry.getContext().getContentResolver();
        Uri uri1 = Uri.parse("content://com.jscheng.spluto/markdown/1");
        contentResolver.delete(uri1, null, null);
        Uri uri2 = Uri.parse("content://com.jscheng.spluto/markdown");
        Cursor cursor = contentResolver.query(uri2, null,  null, null, null);
        while(cursor.moveToNext()) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < cursor.getColumnCount(); i++) {
                builder.append(cursor.getString(i) + "  ");
            }
            Log.v("CJS", builder.toString());
        }
    }
}
