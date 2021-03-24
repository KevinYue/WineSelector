package com.example.kevinyue.wineselector.Element.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by kevinyue on 23.04.16.
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "show.db";
    private static final int DATABASE_VERSION = 2;

    private static final String TAG = "WineTable";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.w(TAG, WineTable.DATABASE_CREATE_SEARCH);
        db.execSQL(WineTable.DATABASE_CREATE_SEARCH);
        //WineTable.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgrading database from version" + oldVersion + " to " + newVersion +
        ", delete all old data");
        db.execSQL("DROP TABLE IF EXISTS " + WineTable.TABLE_WINE);
        onCreate(db);
        //WineTable.onUpgrade(db, oldVersion, newVersion);
    }

}
