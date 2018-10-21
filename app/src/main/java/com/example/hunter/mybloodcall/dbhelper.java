package com.example.hunter.mybloodcall;

import android.content.ContentValues;
import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by HuNter on 4/4/2018.
 */

public class dbhelper extends SQLiteOpenHelper {

    static final String Database_Name = "donorName.db";
    static final String table_name = "Donor_Name";
    static final int Database_version = 1;
    static final String Col_0 = "ID";
    static final String Col_1 = "NAME";
    static final String Col_2 = "SEX";
    static final String Col_3 = "CITY";
    static final String Col_4 = "BLOOD_GROUP";
    static final String Col_5 = "PHONE_NUMBER";


    public dbhelper(Context context) {
        super(context,Database_Name,null,Database_version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Create_table = "CREATE TABLE " + table_name + "(" + Col_0 + " INTEGER PRIMARY KEY AUTOINCREMENT," +
        Col_1 + " TEXT," +
        Col_2 + " TEXT," +
        Col_3 + " TEXT," +
        Col_4 + " TEXT," +
        Col_5 + " TEXT" + ");";
        db.execSQL(Create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(" DROP TABLE IF EXISTS " + table_name);
        onCreate(db);
    }

    public boolean insertDonor(String Name, String Sex, String City , String Bloodgroup , String Phoneno){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Col_1,Name);
        cv.put(Col_2,Sex);
        cv.put(Col_3,City);
        cv.put(Col_4,Bloodgroup);
        cv.put(Col_5,Phoneno);

        long result = db.insert(table_name,null,cv);
            if(result == -1){
                return false;
            }
            else{
                return true;
            }
    }


}
