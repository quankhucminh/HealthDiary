package com.quankm.healthdiary.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by Infernocorez on 6/1/2016.
 */
public class HealthDataProvider extends ContentProvider {

    private DBHelper helper;
    private SQLiteDatabase db;

    private static final String AUTHORITY = "com.quankm.healthdiary";

    public static final Uri QUERY_BP = Uri.parse("content://" + AUTHORITY + "/" + DBHelper.TBL_BLOODPRESSURE);
    public static final Uri QUERY_BS = Uri.parse("content://" + AUTHORITY + "/" + DBHelper.TBL_BLOODSUGAR);
    public static final Uri QUERY_DISEASE = Uri.parse("content://" + AUTHORITY + "/" + DBHelper.TBL_CHRONICDISEASE);
    public static final Uri QUERY_MEDICINE = Uri.parse("content://" + AUTHORITY + "/" + DBHelper.TBL_MEDICINE);
    public static final Uri QUERY_PRESCRIPTION = Uri.parse("content://" + AUTHORITY + "/" + DBHelper.TBL_PRESCRIPTION);
    public static final Uri QUERY_PRESMED = Uri.parse("content://" + AUTHORITY + "/" + DBHelper.TBL_PRESMED);
    public static final Uri QUERY_REMIND = Uri.parse("content://" + AUTHORITY + "/" + DBHelper.TBL_REMIND);
    public static final Uri QUERY_WEIGHT = Uri.parse("content://" + AUTHORITY + "/" + DBHelper.TBL_WEIGHT);


    @Override
    public boolean onCreate() {
        helper = new DBHelper(getContext());
        db = helper.getWritableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return db.query(uri.getLastPathSegment(),projection,selection,selectionArgs,null,null,sortOrder);
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long id = db.insert(uri.getLastPathSegment(),null,values);
        return Uri.parse(uri.toString()+id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        return db.delete(uri.getLastPathSegment(),selection,selectionArgs);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return db.update(uri.getLastPathSegment(),values,selection,selectionArgs);
    }
}
