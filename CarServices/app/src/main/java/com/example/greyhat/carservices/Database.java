package com.example.greyhat.carservices;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by greyhat on 6/10/17.
 */

public class Database extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "users.db";
    private static final String TABLE_NAME = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASS = "pass";
    private static final String COLUMN_CAR = "car";

    SQLiteDatabase db;

    private static final String TABLE_CREATE = "create table users (id integer primary key not null, " +
            "name text not null, username text not null, pass text not null, car text not null);";

    public Database(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    public void insertContact(Contact c)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from users";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(COLUMN_ID, count);

        values.put(COLUMN_NAME, c.getName());
        values.put(COLUMN_USERNAME, c.getUsername());
        values.put(COLUMN_PASS, c.getPassword());
        values.put(COLUMN_CAR, c.getCar());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public String retrieveInfo()
    {
        String a = "";

        db = this.getReadableDatabase();
        String query = "select * from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        a = cursor.getString(2) + " , " + cursor.getString(4);

        return a;
    }

    public String searchPass(String username)
    {
        db = this.getReadableDatabase();
        String query = "select * from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a, b;

        b = "Not found";

        if(cursor.moveToFirst())
        {
            do {

                a = cursor.getString(2);

                if (a.equals(username))
                {
                    b = cursor.getString(4);
                    break;
                }
            }

            while(cursor.moveToNext());
        }

        return b;
    }


    /*public void deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,null,null);
        db.execSQL("delete * from "+ TABLE_NAME);
        db.execSQL("TRUNCATE table" + TABLE_NAME);
        db.close();
    }*/

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}
