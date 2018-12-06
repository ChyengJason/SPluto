package com.jscheng.spluto;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import com.jscheng.spluto.dao.MarkdownTextDao;

import java.io.File;

public class MarkDownProvider extends ContentProvider {
    private static final String Authority = "com.jscheng.spluto";
    private static final String MarkdownPath = "markdown";
    private static final String MarkdownIdPath = "markdown/#";
    private static final Uri ContentUri = Uri.parse("content://" + Authority + "/" + MarkdownPath);
    private static final UriMatcher uriMatcher;
    private static final int MarkdownCode = 1;
    private static final int MarkdownIdCode = 2;
    private MarkdownTextDao mMarkdronwDao;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(Authority, MarkdownPath, MarkdownCode);
        uriMatcher.addURI(Authority, MarkdownIdPath, MarkdownIdCode);
    }

    @Override
    public boolean onCreate() {
        mMarkdronwDao = new MarkdownTextDao(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor = null;
        int flag = uriMatcher.match(uri);
        switch (flag) {
            case MarkdownCode:
                cursor = mMarkdronwDao.query(selection, selectionArgs, sortOrder);
                break;
            case MarkdownIdCode:
                String id = uri.getPathSegments().get(1);
                cursor = mMarkdronwDao.query(Integer.valueOf(id), sortOrder);
                break;
            default:
                break;
        }
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        int flag = uriMatcher.match(uri);
        switch (flag) {
            case MarkdownCode:
                return Authority + File.separator + MarkdownPath;
            case MarkdownIdCode:
                return Authority + File.separator + MarkdownIdPath;
            default:
                return null;
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int flag = uriMatcher.match(uri);
        if (flag == MarkdownCode || flag == MarkdownIdCode) {
            long rowId = mMarkdronwDao.add(values);
            if (rowId > 0) {
                Uri insertUri = ContentUris.withAppendedId(ContentUri, rowId);
                return insertUri;
            }
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = -1;
        int flag = uriMatcher.match(uri);
        switch (flag) {
            case MarkdownCode:
                count = mMarkdronwDao.delete(selection, selectionArgs);
                break;
            case MarkdownIdCode:
                String id = uri.getPathSegments().get(1);
                count = mMarkdronwDao.delete(Integer.valueOf(id));
                break;
            default:
                break;
        }
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int count = 0;
        int flag = uriMatcher.match(uri);
        switch (flag) {
            case MarkdownCode:
                count = mMarkdronwDao.update(selection, selectionArgs, values);
                break;
            case MarkdownIdCode:
                String id = uri.getPathSegments().get(1);
                count = mMarkdronwDao.update(Integer.valueOf(id), values);
                break;
            default:
                break;
        }
        return count;
    }
}
