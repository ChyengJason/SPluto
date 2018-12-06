package com.jscheng.spluto.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class MarkdownTextDao {

    private DBOpenHelper mDBOpenHelper;

    public MarkdownTextDao(Context context) {
        mDBOpenHelper = new DBOpenHelper(context);
    }

    private ContentValues getContentValues(MarkdownTextInfo info) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", info.getName());
        contentValues.put("path", info.getPath());
        contentValues.put("summary", info.getSummary());
        contentValues.put("exist", info.isExist());
        return contentValues;
    }

    public long add(MarkdownTextInfo info) {
        SQLiteDatabase database = mDBOpenHelper.getWritableDatabase();
        return database.insert(DBOpenHelper.MarkDownTextTable, null, getContentValues(info));
    }

    public long add(ContentValues contentValues) {
        SQLiteDatabase database = mDBOpenHelper.getWritableDatabase();
        return database.insert(DBOpenHelper.MarkDownTextTable, null, contentValues);
    }

    public int delete(int id) {
        SQLiteDatabase database = mDBOpenHelper.getWritableDatabase();
        return database.delete(DBOpenHelper.MarkDownTextTable, "id=?", new String[]{ String.valueOf(id) });
    }

    public int delete(String selection, String[] selectionArgs) {
        SQLiteDatabase database = mDBOpenHelper.getWritableDatabase();
        return database.delete(DBOpenHelper.MarkDownTextTable, selection, selectionArgs);
    }

    public int update(MarkdownTextInfo info) {
        String idString = String.valueOf(info.getId());
        SQLiteDatabase database = mDBOpenHelper.getWritableDatabase();
        return database.update(DBOpenHelper.MarkDownTextTable, getContentValues(info), "id=?", new String[]{ idString });
    }

    public int update(String selection, String[] selectionArgs, ContentValues values) {
        SQLiteDatabase database = mDBOpenHelper.getWritableDatabase();
        return database.update(DBOpenHelper.MarkDownTextTable, values, selection, selectionArgs);
    }

    public int update(Integer id, ContentValues contentValues) {
        String idString = String.valueOf(id);
        SQLiteDatabase database = mDBOpenHelper.getWritableDatabase();
        return database.update(DBOpenHelper.MarkDownTextTable, contentValues, "id=?", new String[]{ idString });
    }

    public MarkdownTextInfo find(int id) {
        SQLiteDatabase database = mDBOpenHelper.getReadableDatabase();
        String[] selectionArgs = { String.valueOf(id) };
        Cursor cursor = database.query(DBOpenHelper.MarkDownTextTable,
                null, "id=?", selectionArgs,
                null, null, null, null);

        MarkdownTextInfo info = null;
        if (cursor.moveToNext()) {
            id = cursor.getInt(0);
            String name = cursor.getString(1);
            String path = cursor.getString(2);
            String summary = cursor.getString(3);
            boolean exist = cursor.getInt(4) > 0;
            info = new MarkdownTextInfo(id, name, path, summary, exist);
        }
        cursor.close();
        return info;
    }

    public Cursor query(int id, String orderSort) {
        SQLiteDatabase database = mDBOpenHelper.getReadableDatabase();
        String[] selectionArgs = { String.valueOf(id) };
        Cursor cursor = database.query(DBOpenHelper.MarkDownTextTable,
                null, "id=?", selectionArgs,
                null, null, orderSort, null);
        return cursor;
    }

    public Cursor query(String selection, String[] selectionArgs, String orderSort) {
        SQLiteDatabase database = mDBOpenHelper.getReadableDatabase();
        Cursor cursor = database.query(DBOpenHelper.MarkDownTextTable,
                null, selection, selectionArgs,
                null, null, orderSort, null);
        return cursor;
    }

    public int getCount() {
        SQLiteDatabase database = mDBOpenHelper.getReadableDatabase();
        Cursor cursor = database.query(DBOpenHelper.MarkDownTextTable,
                null, null, null,
                null, null, null, null);
        return cursor.getCount();
    }

    public List<MarkdownTextInfo> getOffetsData(int offset, int limit) {
        SQLiteDatabase database = mDBOpenHelper.getReadableDatabase();
        String limitString = String.valueOf(offset) + "," + String.valueOf(limit);
        Cursor cursor = database.query(DBOpenHelper.MarkDownTextTable,
                null, null, null,
                null, null, null, limitString);
        List<MarkdownTextInfo> list = new ArrayList<>(cursor.getCount());
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String path = cursor.getString(2);
            String summary = cursor.getString(3);
            boolean exist = cursor.getInt(4) > 0;
            MarkdownTextInfo info = new MarkdownTextInfo(id, name, path, summary, exist);
            list.add(info);
        }
        return list;
    }

}
