package com.example.hunter.mybloodcall.Data;

import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {

    static final String AUTHORITY = "com.example.hunter.mybloodcall";
    static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    static final String PATH_DONORS = "donors";

    public static final class DonorsEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon()
                        .appendPath(PATH_DONORS)
                        .build();
        public static final String TABLE_NAME = "Donors_table";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_CITY = "city";
        public static final String COLUMN_BlOODGROUP = "bloodgroup";

    }
}
