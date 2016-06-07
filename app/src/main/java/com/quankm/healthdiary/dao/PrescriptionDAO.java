package com.quankm.healthdiary.dao;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import com.quankm.healthdiary.database.DBHelper;
import com.quankm.healthdiary.database.HealthDataProvider;
import com.quankm.healthdiary.pojo.Prescription;

/**
 * Created by Infernocorez on 6/3/2016.
 */
public class PrescriptionDAO {
    private ContentResolver resolver;

    public PrescriptionDAO(ContentResolver resolver) {
        this.resolver = resolver;
    }

    public long insert(ContentValues values) {
        Uri result = resolver.insert(HealthDataProvider.QUERY_PRESCRIPTION, values);
        return Long.parseLong(result.getLastPathSegment());
    }

    public int updateByID(ContentValues values, String _id) {
        return resolver.update(HealthDataProvider.QUERY_PRESCRIPTION, values, "_id = ?", new String[]{_id});
    }

    public int update(ContentValues values, String selection, String _id) {
        return resolver.update(HealthDataProvider.QUERY_PRESCRIPTION, values, selection, new String[]{_id});
    }

    public int deleteByID(String _id) {
        return resolver.delete(HealthDataProvider.QUERY_PRESCRIPTION, "_id = ?", new String[]{_id});
    }

    public int delete(String selection, String _id) {
        return resolver.delete(HealthDataProvider.QUERY_PRESCRIPTION, selection, new String[]{_id});
    }

    public Cursor query(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return resolver.query(HealthDataProvider.QUERY_PRESCRIPTION, projection, selection, selectionArgs, sortOrder);
    }

    public Cursor queryAllCursor(String userID, String sortOrder) {
        return resolver.query(HealthDataProvider.QUERY_PRESCRIPTION, null, "UserID = ?", new String[]{userID}, sortOrder);
    }

    public Prescription queryByID(String _id) {
        Prescription result = null;
        Cursor c = resolver.query(HealthDataProvider.QUERY_PRESCRIPTION, null, "_id=?", new String[]{_id}, null);
        if (c != null && c.moveToFirst()) {
            result = new Prescription();
            result.set_id(c.getLong(c.getColumnIndex(DBHelper.PRESCRIPTION_COL_ID)));
            result.setUserID(c.getLong(c.getColumnIndex(DBHelper.PRESCRIPTION_COL_USERID)));
            result.setName(c.getString(c.getColumnIndex(DBHelper.PRESCRIPTION_COL_NAME)));
            result.setNameClean(c.getString(c.getColumnIndex(DBHelper.PRESCRIPTION_COL_NAMECLEAN)));
            result.setConditionName(c.getString(c.getColumnIndex(DBHelper.PRESCRIPTION_COL_CONDITIONNAME)));
            result.setConditionNameClean(c.getString(c.getColumnIndex(DBHelper.PRESCRIPTION_COL_CONDITIONNAMECLEAN)));
            result.setDoctorName(c.getString(c.getColumnIndex(DBHelper.PRESCRIPTION_COL_DOCTORNAME)));
            result.setNote(c.getString(c.getColumnIndex(DBHelper.PRESCRIPTION_COL_NOTE)));
            result.setValidFromDate(c.getLong(c.getColumnIndex(DBHelper.PRESCRIPTION_COL_VALIDFROMDATE)));
            result.setValidToDate(c.getLong(c.getColumnIndex(DBHelper.PRESCRIPTION_COL_VALIDTODATE)));
            result.setActive(c.getInt(c.getColumnIndex(DBHelper.PRESCRIPTION_COL_ISACTIVE)) == 1);
            result.setUpdated(c.getInt(c.getColumnIndex(DBHelper.PRESCRIPTION_COL_ISUPDATED)) == 1);
            result.setUpdatedTimeStamp(c.getLong(c.getColumnIndex(DBHelper.PRESCRIPTION_COL_TIMESTAMP)));
            c.close();
        }
        return result;
    }
}
