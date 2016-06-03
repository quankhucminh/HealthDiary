package com.quankm.healthdiary.dao;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import com.quankm.healthdiary.database.DBHelper;
import com.quankm.healthdiary.database.HealthDataProvider;
import com.quankm.healthdiary.pojo.Medicine;

/**
 * Created by Infernocorez on 6/3/2016.
 */
public class MedicineDAO {
    private ContentResolver resolver;

    public MedicineDAO(ContentResolver resolver) {
        this.resolver = resolver;
    }

    public long insert(ContentValues values) {
        Uri result = resolver.insert(HealthDataProvider.QUERY_MEDICINE, values);
        return Long.parseLong(result.getLastPathSegment());
    }

    public int updateByID(ContentValues values, String _id) {
        return resolver.update(HealthDataProvider.QUERY_MEDICINE, values, "_id = ?", new String[]{_id});
    }

    public int update(ContentValues values, String selection, String _id) {
        return resolver.update(HealthDataProvider.QUERY_MEDICINE, values, selection, new String[]{_id});
    }

    public int deleteByID(String _id) {
        return resolver.delete(HealthDataProvider.QUERY_MEDICINE, "_id = ?", new String[]{_id});
    }

    public int delete(String selection, String _id) {
        return resolver.delete(HealthDataProvider.QUERY_MEDICINE, selection, new String[]{_id});
    }

    public Cursor query(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return resolver.query(HealthDataProvider.QUERY_MEDICINE, projection, selection, selectionArgs, sortOrder);
    }

    public Cursor queryAllCursor(String userID, String sortOrder) {
        return resolver.query(HealthDataProvider.QUERY_MEDICINE, null, "UserID = ?", new String[]{userID}, sortOrder);
    }

    public Medicine queryByID(String _id) {
        Medicine result = null;
        Cursor c = resolver.query(HealthDataProvider.QUERY_MEDICINE, null, "_id=?", new String[]{_id}, null);
        if (c != null && c.moveToFirst()) {
            result = new Medicine();
            result.set_id(c.getLong(c.getColumnIndex(DBHelper.MEDICINE_COL_ID)));
            result.setUserID(c.getLong(c.getColumnIndex(DBHelper.MEDICINE_COL_USERID)));
            result.setMedicineName(c.getString(c.getColumnIndex(DBHelper.MEDICINE_COL_MEDICINENAME)));
            result.setMedicineNameClean(c.getString(c.getColumnIndex(DBHelper.MEDICINE_COL_MEDICINENAMECLEAN)));
            result.setActiveIngredient(c.getString(c.getColumnIndex(DBHelper.MEDICINE_COL_ACTIVEINGREDIENT)));
            result.setActiveIngredientClean(c.getString(c.getColumnIndex(DBHelper.MEDICINE_COL_ACTIVEINGREDIENTCLEAN)));
            result.setDosage(c.getString(c.getColumnIndex(DBHelper.MEDICINE_COL_DOSAGE)));
            result.setAllergic(c.getInt(c.getColumnIndex(DBHelper.MEDICINE_COL_ISALLERGIC)) == 1);
            result.setActive(c.getInt(c.getColumnIndex(DBHelper.MEDICINE_COL_ISACTIVE)) == 1);
            result.setUpdated(c.getInt(c.getColumnIndex(DBHelper.MEDICINE_COL_ISUPDATED)) == 1);
            result.setUpdatedTimeStamp(c.getLong(c.getColumnIndex(DBHelper.MEDICINE_COL_TIMESTAMP)));
            c.close();
        }
        return result;
    }
}
