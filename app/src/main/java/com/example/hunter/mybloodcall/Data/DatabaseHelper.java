package com.example.hunter.mybloodcall.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import javax.annotation.Nullable;


public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "DonorsSearched.db";
    private static final String TABLE_NAME = "DonorsSearched_table";
    private static final  int VERSION = 1;
//    private static final String Col_Id= "Id";
//    private static final String Col_Name= "Name";
//    private static final String Col_City = "City";
//    private static final String Col_BloodGrp = "BloodGroup";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_TABLE ="CREATE TABLE " + DatabaseContract.DonorsEntry.TABLE_NAME + " ("+
                DatabaseContract.DonorsEntry._ID + " INTEGER PRIMARY KEY, " +
                DatabaseContract.DonorsEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                DatabaseContract.DonorsEntry.COLUMN_CITY + " TEXT NOT NULL, " +
                DatabaseContract.DonorsEntry.COLUMN_BlOODGROUP + " TEXT NOT NULL);";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }
}
