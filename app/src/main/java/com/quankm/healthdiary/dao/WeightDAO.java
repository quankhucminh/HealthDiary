package com.quankm.healthdiary.dao;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import com.quankm.healthdiary.database.DBHelper;
import com.quankm.healthdiary.database.HealthDataProvider;
import com.quankm.healthdiary.pojo.Weigth;

/**
 * Created by Infernocorez on 6/3/2016.
 */
public class WeightDAO {
    private ContentResolver resolver;

    public WeightDAO(ContentResolver resolver) {
        this.resolver = resolver;
    }

    public long insert(ContentValues values) {
        Uri result = resolver.insert(HealthDataProvider.QUERY_WEIGHT, values);
        return Long.parseLong(result.getLastPathSegment());
    }

    public int updateByID(ContentValues values, String _id) {
        return resolver.update(HealthDataProvider.QUERY_WEIGHT, values, "_id = ?", new String[]{_id});
    }

    public int update(ContentValues values, String selection, String _id) {
        return resolver.update(HealthDataProvider.QUERY_WEIGHT, values, selection, new String[]{_id});
    }

    public int deleteByID(String _id) {
        return resolver.delete(HealthDataProvider.QUERY_WEIGHT, "_id = ?", new String[]{_id});
    }

    public int delete(String selection, String _id) {
        return resolver.delete(HealthDataProvider.QUERY_WEIGHT, selection, new String[]{_id});
    }

    public Cursor query(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return resolver.query(HealthDataProvider.QUERY_WEIGHT, projection, selection, selectionArgs, sortOrder);
    }

    public Cursor queryAllCursor(String userID, String sortOrder) {
        return resolver.query(HealthDataProvider.QUERY_WEIGHT, null, "UserID = ?", new String[]{userID}, sortOrder);
    }

    public Weigth queryByID(String _id) {
        Weigth result = null;
        Cursor c = resolver.query(HealthDataProvider.QUERY_WEIGHT, null, "_id=?", new String[]{_id}, null);
        if (c != null && c.moveToFirst()) {
            result = new Weigth();
            result.set_id(c.getLong(c.getColumnIndex(DBHelper.WEIGHT_COL_ID)));
            result.setUserID(c.getLong(c.getColumnIndex(DBHelper.WEIGHT_COL_USERID)));
            result.setBodyWeight(c.getFloat(c.getColumnIndex(DBHelper.WEIGHT_COL_BODYWEIGHT)));
            result.setBMI(c.getFloat(c.getColumnIndex(DBHelper.WEIGHT_COL_BMI)));
            result.setActive(c.getInt(c.getColumnIndex(DBHelper.WEIGHT_COL_ISACTIVE)) == 1);
            result.setUpdated(c.getInt(c.getColumnIndex(DBHelper.WEIGHT_COL_ISUPDATED)) == 1);
            result.setUpdatedTimeStamp(c.getLong(c.getColumnIndex(DBHelper.WEIGHT_COL_TIMESTAMP)));
            c.close();
        }
        return result;
    }
}
