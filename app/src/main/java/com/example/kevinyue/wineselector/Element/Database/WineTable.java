package com.example.kevinyue.wineselector.Element.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.kevinyue.wineselector.Element.Wine;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Inspiration from Lecture examples:
 * http://www.myandroidsolutions.com/2013/08/04/android-listview-with-searchview/
 */
public class WineTable {
    public static final String TABLE_WINE = "wine";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_COUNTRY = "country";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_SEARCH = "searchData";

    private static final String TAG = "WineTable";

    private SQLiteHelper sqLiteHelper;
    private SQLiteDatabase sqLiteDatabase;

    Context mCtx;

    // Create a FTS3 (it'a full text search, a part of a SQLite Extension) Virtual Table for fast searches
    public static final String DATABASE_CREATE_SEARCH =
            "CREATE VIRTUAL TABLE " + TABLE_WINE + " USING fts3(" +
                    COLUMN_ID + "integer PRIMARY KEY autoincrement," +
                    COLUMN_TYPE + "," +
                    COLUMN_NAME + "," +
                    COLUMN_COUNTRY + "," +
                    COLUMN_PRICE + ");";



    public WineTable(Context ctx) {
        this.mCtx = ctx;
    }

    public WineTable() {

    }


    public WineTable open() throws SQLException {
        sqLiteHelper = new SQLiteHelper(mCtx);
        sqLiteDatabase = sqLiteHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if(sqLiteHelper != null) {
            sqLiteHelper.close();
        }
    }

    /*public long createWinesList(String type, String name, String country, int dice, String description, String price) {
        ContentValues contentValues = new ContentValues();
        String searchValue = type + " " +
                name + " " +
                country + " " +
                dice + " " +
                description + " " +
                price;
        contentValues.put(COLUMN_TYPE, type);
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_COUNTRY, country);
        contentValues.put(COLUMN_DICE, dice);
        contentValues.put(COLUMN_DESCRIPTION, description);
        contentValues.put(COLUMN_PRICE, price);

        return sqLiteDatabase.insert(TABLE_WINE, null, contentValues);
    }*/

    public void createWinesList(ArrayList<Wine> tempList) {


        for (int i=0; i < tempList.size(); i++){
            ContentValues contentValues = new ContentValues();

            contentValues.put(COLUMN_TYPE, String.valueOf(tempList.get(i).getType()));
            contentValues.put(COLUMN_NAME, String.valueOf(tempList.get(i).getName()));
            contentValues.put(COLUMN_COUNTRY, String.valueOf(tempList.get(i).getCountry()));
            contentValues.put(COLUMN_PRICE, String.valueOf(tempList.get(i).getPrice()));

            sqLiteDatabase.insert(TABLE_WINE, null, contentValues);
        }
    }

    public Cursor searchWines(String inputText) throws SQLException {
        Log.w(TAG, inputText);
        String query = "SELECT id as _id, " +
                COLUMN_TYPE + "," +
                COLUMN_NAME + "," +
                COLUMN_COUNTRY + "," +
                COLUMN_PRICE +
                " from " + TABLE_WINE +
                " where " + COLUMN_SEARCH + " MATCH '" + inputText + "';";
        Log.w(TAG, query);
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if(cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;

    }

    public boolean deleteAllWines() {
        int deleteCompleted = 0;
        deleteCompleted = sqLiteDatabase.delete(TABLE_WINE, null, null);
        Log.w(TAG, Integer.toString(deleteCompleted));
        return deleteCompleted > 0;
    }

}
