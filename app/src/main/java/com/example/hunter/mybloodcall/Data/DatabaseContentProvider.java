package com.example.hunter.mybloodcall.Data;

import android.annotation.TargetApi;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Objects;

import static com.example.hunter.mybloodcall.Data.DatabaseContract.DonorsEntry.TABLE_NAME;

public class DatabaseContentProvider extends ContentProvider {

    private static final int Donor = 101;
//    private static final int Donor_Id = 101;
    private DatabaseHelper mDatabaseHelper;


        private static final UriMatcher sUrimatcher = buidUriMatcher();


        public static UriMatcher buidUriMatcher(){
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(DatabaseContract.AUTHORITY, DatabaseContract.PATH_DONORS, Donor);
//        uriMatcher.addURI(DatabaseContract.AUTHORITY, DatabaseContract.PATH_DONORS + "/#", Donor_Id);

//        Log.d("urimatcher", String.valueOf(uriMatcher));
        return uriMatcher;
    }




    @Override
    public boolean onCreate() {
        Context context = getContext();
        mDatabaseHelper = new DatabaseHelper(context);
        return true;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        final SQLiteDatabase db = mDatabaseHelper.getReadableDatabase();
        int match = sUrimatcher.match(uri);
        Cursor retCursor;

        switch (match){
            case Donor:
                retCursor = db.query(TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("Unknow uri: " + uri);

        }
        retCursor.setNotificationUri(Objects.requireNonNull(getContext()).getContentResolver(), uri);
        return retCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        final SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        int match = sUrimatcher.match(uri);
        Uri returnUri;
        switch (match){
            case Donor:
                long id= db.insert(TABLE_NAME,  null, contentValues);

                if (id > 0){
                    returnUri = ContentUris.withAppendedId(DatabaseContract.DonorsEntry.CONTENT_URI, id);

                }else throw new SQLException("Failed to insert row into: " + uri);
                break;
            default:
                throw new UnsupportedOperationException("Unknow uri: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
