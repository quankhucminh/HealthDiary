package com.quankm.healthdiary.dao;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import com.quankm.healthdiary.database.DBHelper;
import com.quankm.healthdiary.database.HealthDataProvider;
import com.quankm.healthdiary.pojo.PresMed;

import java.util.ArrayList;

/**
 * Created by Infernocorez on 6/3/2016.
 */
public class PresMedDAO {

    private ContentResolver resolver;

    public PresMedDAO(ContentResolver resolver) {
        this.resolver = resolver;
    }

    public long insert(ContentValues values) {
        Uri result = resolver.insert(HealthDataProvider.QUERY_PRESMED, values);
        return Long.parseLong(result.getLastPathSegment());
    }

    public int updateByID(ContentValues values, String _id) {
        return resolver.update(HealthDataProvider.QUERY_PRESMED, values, "_id = ?", new String[]{_id});
    }

    public int update(ContentValues values, String selection, String _id) {
        return resolver.update(HealthDataProvider.QUERY_PRESMED, values, selection, new String[]{_id});
    }

    public int deleteByID(String _id) {
        return resolver.delete(HealthDataProvider.QUERY_PRESMED, "_id = ?", new String[]{_id});
    }

    public int delete(String selection, String _id) {
        return resolver.delete(HealthDataProvider.QUERY_PRESMED, selection, new String[]{_id});
    }

    public Cursor query(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return resolver.query(HealthDataProvider.QUERY_PRESMED, projection, selection, selectionArgs, sortOrder);
    }

    public Cursor queryByPresIDCursor(String presID, String sortOrder) {
        return resolver.query(HealthDataProvider.QUERY_PRESMED, null, "PresID = ?", new String[]{presID}, sortOrder);
    }

    public ArrayList<PresMed> queryByPresIDArray(String presID, String sortOrder){
        ArrayList<PresMed> list = null;

        Cursor c = resolver.query(HealthDataProvider.QUERY_PRESMED, null, "PresID = ?", new String[]{presID}, sortOrder);
        PresMed item;
        if(c!=null && c.moveToFirst()){
            list = new ArrayList<>();
            while(!c.isAfterLast()){
                item = new PresMed();
                item.set_id(c.getLong(c.getColumnIndex(DBHelper.PRESMED_COL_ID)));
                item.setPresID(c.getLong(c.getColumnIndex(DBHelper.PRESMED_COL_PRESID)));
                item.setMedID(c.getLong(c.getColumnIndex(DBHelper.PRESMED_COL_MEDID)));
                item.setActive(c.getInt(c.getColumnIndex(DBHelper.PRESMED_COL_ISACTIVE))==1);
                item.setUpdated(c.getInt(c.getColumnIndex(DBHelper.PRESMED_COL_ISUPDATED))==1);
                item.setUpdatedTimeStamp(c.getLong(c.getColumnIndex(DBHelper.PRESMED_COL_TIMESTAMP)));

                list.add(item);
                c.moveToNext();
            }
            c.close();
        }

        return list;
    }
}
