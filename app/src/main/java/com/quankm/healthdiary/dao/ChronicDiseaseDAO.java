package com.quankm.healthdiary.dao;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import com.quankm.healthdiary.database.DBHelper;
import com.quankm.healthdiary.database.HealthDataProvider;
import com.quankm.healthdiary.pojo.ChronicDisease;

/**
 * Created by Infernocorez on 6/3/2016.
 */
public class ChronicDiseaseDAO {
    private ContentResolver resolver;

    public ChronicDiseaseDAO(ContentResolver resolver) {
        this.resolver = resolver;
    }

    public long insert(ContentValues values) {
        Uri result = resolver.insert(HealthDataProvider.QUERY_DISEASE, values);
        return Long.parseLong(result.getLastPathSegment());
    }

    public int updateByID(ContentValues values, String _id) {
        return resolver.update(HealthDataProvider.QUERY_DISEASE, values, "_id = ?", new String[]{_id});
    }

    public int update(ContentValues values, String selection, String _id) {
        return resolver.update(HealthDataProvider.QUERY_DISEASE, values, selection, new String[]{_id});
    }

    public int deleteByID(String _id) {
        return resolver.delete(HealthDataProvider.QUERY_DISEASE, "_id = ?", new String[]{_id});
    }

    public int delete(String selection, String _id) {
        return resolver.delete(HealthDataProvider.QUERY_DISEASE, selection, new String[]{_id});
    }

    public Cursor query(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return resolver.query(HealthDataProvider.QUERY_DISEASE, projection, selection, selectionArgs, sortOrder);
    }

    public Cursor queryAllCursor(String userID, String sortOrder) {
        return resolver.query(HealthDataProvider.QUERY_DISEASE, null, "UserID = ?", new String[]{userID}, sortOrder);
    }

    public ChronicDisease queryByID(String _id) {
        ChronicDisease result = null;
        Cursor c = resolver.query(HealthDataProvider.QUERY_DISEASE, null, "_id=?", new String[]{_id}, null);
        if (c != null && c.moveToFirst()) {
            result = new ChronicDisease();
            result.set_id(c.getLong(c.getColumnIndex(DBHelper.DISEASE_COL_ID)));
            result.setUserID(c.getLong(c.getColumnIndex(DBHelper.DISEASE_COL_USERID)));
            result.setConditionName(c.getString(c.getColumnIndex(DBHelper.DISEASE_COL_CONDITIONNAME)));
            result.setNote(c.getString(c.getColumnIndex(DBHelper.DISEASE_COL_NOTE)));
            result.setActive(c.getInt(c.getColumnIndex(DBHelper.DISEASE_COL_ISACTIVE)) == 1);
            result.setUpdated(c.getInt(c.getColumnIndex(DBHelper.DISEASE_COL_ISUPDATED)) == 1);
            result.setUpdatedTimeStamp(c.getLong(c.getColumnIndex(DBHelper.DISEASE_COL_TIMESTAMP)));
            c.close();
        }
        return result;
    }
}
