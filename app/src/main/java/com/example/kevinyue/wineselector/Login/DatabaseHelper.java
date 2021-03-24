package com.example.kevinyue.wineselector.Login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Variables for database
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "users.db";
    private static final String TABLE_NAME = "users";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    //private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";

    SQLiteDatabase db;

    // Set the variable for create table method
    private static final String CREATE_TABLE =
            "create table " +  TABLE_NAME + "(" +
                   COLUMN_ID + " integer primary key not null, " +
                   COLUMN_NAME + " text not null, " +
                   COLUMN_USERNAME + " text not null, " +
                   COLUMN_PASSWORD + " text not null" + ");";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertUser(User user) {
        db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        String query = "select * from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        contentValues.put(COLUMN_ID, count);
        contentValues.put(COLUMN_NAME, user.getName());
        contentValues.put(COLUMN_USERNAME, user.getUsername());
        contentValues.put(COLUMN_PASSWORD, user.getPassword());
        //contentValues.put(COLUMN_PASSWORD, user.getConfirmPassword());
        // insert values on the database
        db.insert(TABLE_NAME, null, contentValues);
        // Close the database
        db.close();
    }

    public String findPassword(String username) {
        // Read the database
        db = this.getReadableDatabase();
        String query = "select username, password from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        // Username and password
        String user;
        String pass;

        pass = "Not Found";

        if(cursor.moveToFirst()) {
            do {
                user = cursor.getString(0);

                if(user.equals(username)) {
                    pass = cursor.getString(1);
                    break;
                }
            }
            while(cursor.moveToNext());

        }
        return pass;

    }
}
