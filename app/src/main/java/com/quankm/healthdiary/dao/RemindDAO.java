package com.quankm.healthdiary.dao;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import com.quankm.healthdiary.database.DBHelper;
import com.quankm.healthdiary.database.HealthDataProvider;
import com.quankm.healthdiary.pojo.Remind;

import java.util.ArrayList;

/**
 * Created by Infernocorez on 6/3/2016.
 */
public class RemindDAO {
    private ContentResolver resolver;

    public RemindDAO(ContentResolver resolver) {
        this.resolver = resolver;
    }

    public long insert(ContentValues values) {
        Uri result = resolver.insert(HealthDataProvider.QUERY_REMIND, values);
        return Long.parseLong(result.getLastPathSegment());
    }

    public int updateByID(ContentValues values, String _id) {
        return resolver.update(HealthDataProvider.QUERY_REMIND, values, "_id = ?", new String[]{_id});
    }

    public int update(ContentValues values, String selection, String _id) {
        return resolver.update(HealthDataProvider.QUERY_REMIND, values, selection, new String[]{_id});
    }

    public int deleteByID(String _id) {
        return resolver.delete(HealthDataProvider.QUERY_REMIND, "_id = ?", new String[]{_id});
    }

    public int delete(String selection, String _id) {
        return resolver.delete(HealthDataProvider.QUERY_REMIND, selection, new String[]{_id});
    }

    public Cursor query(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return resolver.query(HealthDataProvider.QUERY_REMIND, projection, selection, selectionArgs, sortOrder);
    }

    public Cursor queryByPresMedIDCursor(String presMedID, String sortOrder) {
        return resolver.query(HealthDataProvider.QUERY_REMIND, null, "PresMedID = ?", new String[]{presMedID}, sortOrder);
    }

    public ArrayList<Remind> queryByPresMedIDArray(String presMedID, String sortOrder) {
        ArrayList<Remind> list = null;

        Cursor c = resolver.query(HealthDataProvider.QUERY_REMIND, null, "PresMedID = ?", new String[]{presMedID}, sortOrder);
        Remind item;
        if (c != null && c.moveToFirst()) {
            while (!c.isAfterLast()){
                item = new Remind();
                item.set_id(c.getLong(c.getColumnIndex(DBHelper.REMIND_COL_ID)));
                item.setPresMedID(c.getLong(c.getColumnIndex(DBHelper.REMIND_COL_PRESMEDID)));
                item.setTimeRemind(c.getString(c.getColumnIndex(DBHelper.REMIND_COL_TIMEREMIND)));

                list.add(item);
                c.moveToNext();
            }

            c.close();
        }

        return list;
    }
}
