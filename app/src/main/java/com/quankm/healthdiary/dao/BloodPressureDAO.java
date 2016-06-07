package com.quankm.healthdiary.dao;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import com.quankm.healthdiary.database.DBHelper;
import com.quankm.healthdiary.database.HealthDataProvider;
import com.quankm.healthdiary.pojo.BloodPressure;

/**
 * Created by Infernocorez on 6/3/2016.
 */
public class BloodPressureDAO {

    private ContentResolver resolver;

    public BloodPressureDAO(ContentResolver resolver) {
        this.resolver = resolver;
    }

    public long insert(ContentValues values) {
        Uri result = resolver.insert(HealthDataProvider.QUERY_BP, values);
        return Long.parseLong(result.getLastPathSegment());
    }

    public int updateByID(ContentValues values, String _id) {
        return resolver.update(HealthDataProvider.QUERY_BP, values, "_id = ?", new String[]{_id});
    }

    public int update(ContentValues values, String selection, String _id) {
        return resolver.update(HealthDataProvider.QUERY_BP, values, selection, new String[]{_id});
    }

    public int deleteByID(String _id) {
        return resolver.delete(HealthDataProvider.QUERY_BP, "_id = ?", new String[]{_id});
    }

    public int delete(String selection, String _id) {
        return resolver.delete(HealthDataProvider.QUERY_BP, selection, new String[]{_id});
    }

    public  Cursor query(String[] projection, String selection, String[] selectionArgs, String sortOrder){
        return resolver.query(HealthDataProvider.QUERY_BP, projection, selection, selectionArgs, sortOrder);
    }

    public Cursor queryAllCursor(String userID, String sortOrder) {
        return resolver.query(HealthDataProvider.QUERY_BP, null, "UserID = ?", new String[]{userID}, sortOrder);
    }

    public BloodPressure queryByID(String _id){
        BloodPressure result = null;
        Cursor c = resolver.query(HealthDataProvider.QUERY_BP,null,"_id=?",new String[]{_id},null);
        if(c!=null && c.moveToFirst()){
            result = new BloodPressure();
            result.set_id(c.getLong(c.getColumnIndex(DBHelper.BLOODPRESSURE_COL_ID)));
            result.setUserID(c.getLong(c.getColumnIndex(DBHelper.BLOODPRESSURE_COL_USERID)));
            result.setSystolic(c.getInt(c.getColumnIndex(DBHelper.BLOODPRESSURE_COL_SYSTOLIC)));
            result.setDiastolic(c.getInt(c.getColumnIndex(DBHelper.BLOODPRESSURE_COL_DIASTOLIC)));
            result.setState((byte) c.getInt(c.getColumnIndex(DBHelper.BLOODPRESSURE_COL_STATE)));
            result.setHeartRate(c.getInt(c.getColumnIndex(DBHelper.BLOODPRESSURE_COL_HEARTRATE)));
            result.setDateTaken(c.getLong(c.getColumnIndex(DBHelper.BLOODPRESSURE_COL_DATETAKEN)));
            result.setActive(c.getInt(c.getColumnIndex(DBHelper.BLOODPRESSURE_COL_ISACTIVE)) == 1);
            result.setUpdated(c.getInt(c.getColumnIndex(DBHelper.BLOODPRESSURE_COL_ISUPDATED)) == 1);
            result.setUpdatedTimeStamp(c.getLong(c.getColumnIndex(DBHelper.BLOODPRESSURE_COL_TIMESTAMP)));
            c.close();
        }
        return result;
    }
}
