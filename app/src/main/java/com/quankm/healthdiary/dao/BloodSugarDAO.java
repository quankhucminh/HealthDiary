package com.quankm.healthdiary.dao;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import com.quankm.healthdiary.database.DBHelper;
import com.quankm.healthdiary.database.HealthDataProvider;
import com.quankm.healthdiary.pojo.BloodPressure;
import com.quankm.healthdiary.pojo.BloodSugar;

/**
 * Created by Infernocorez on 6/3/2016.
 */
public class BloodSugarDAO {
    private ContentResolver resolver;

    public BloodSugarDAO(ContentResolver resolver) {
        this.resolver = resolver;
    }

    public long insert(ContentValues values) {
        Uri result = resolver.insert(HealthDataProvider.QUERY_BS, values);
        return Long.parseLong(result.getLastPathSegment());
    }

    public int updateByID(ContentValues values, String _id) {
        return resolver.update(HealthDataProvider.QUERY_BS, values, "_id = ?", new String[]{_id});
    }

    public int update(ContentValues values, String selection, String _id) {
        return resolver.update(HealthDataProvider.QUERY_BS, values, selection, new String[]{_id});
    }

    public int deleteByID(String _id) {
        return resolver.delete(HealthDataProvider.QUERY_BS, "_id = ?", new String[]{_id});
    }

    public int delete(String selection, String _id) {
        return resolver.delete(HealthDataProvider.QUERY_BS, selection, new String[]{_id});
    }

    public Cursor query(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return resolver.query(HealthDataProvider.QUERY_BS, projection, selection, selectionArgs, sortOrder);
    }

    public Cursor queryAllCursor(String userID, String sortOrder) {
        return resolver.query(HealthDataProvider.QUERY_BS, null, "UserID = ?", new String[]{userID}, sortOrder);
    }

    public BloodSugar queryByID(String _id) {
        BloodSugar result = null;
        Cursor c = resolver.query(HealthDataProvider.QUERY_BS, null, "_id=?", new String[]{_id}, null);
        if (c != null && c.moveToFirst()) {
            result = new BloodSugar();
            result.set_id(c.getLong(c.getColumnIndex(DBHelper.BLOODSUGAR_COL_ID)));
            result.setUserID(c.getLong(c.getColumnIndex(DBHelper.BLOODSUGAR_COL_USERID)));
            result.setRecordType((byte) c.getInt(c.getColumnIndex(DBHelper.BLOODSUGAR_COL_RECORDTYPE)));
            result.setSugarLevel(c.getFloat(c.getColumnIndex(DBHelper.BLOODSUGAR_COL_SUGARLEVEL)));
            result.setDateTaken(c.getLong(c.getColumnIndex(DBHelper.BLOODSUGAR_COL_DATETAKEN)));
            result.setActive(c.getInt(c.getColumnIndex(DBHelper.BLOODSUGAR_COL_ISACTIVE)) == 1);
            result.setUpdated(c.getInt(c.getColumnIndex(DBHelper.BLOODSUGAR_COL_ISUPDATED)) == 1);
            result.setUpdatedTimeStamp(c.getLong(c.getColumnIndex(DBHelper.BLOODSUGAR_COL_TIMESTAMP)));
            c.close();
        }
        return result;
    }
}
