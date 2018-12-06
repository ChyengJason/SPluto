package com.jscheng.spluto.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {
    public static final String DataBaseName = "SPluto.db";
    public static final int DataBaseVersion = 1;
    public static final String MarkDownTextTable = "markdown";

    private static final String MarkDownTextCreateTableSQL = "create table " + MarkDownTextTable + " (" +
            "id integer primary key autoincrement," +
            "name varchar(200) not null, " +
            "path varchar(200) not null, " +
            "summary varchar(200) ," +
            "exist integer" +
            ");";

    public DBOpenHelper(Context context) {
        super(context, DataBaseName, null, DataBaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MarkDownTextCreateTableSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
