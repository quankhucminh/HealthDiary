package com.quankm.healthdiary.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.quankm.healthdiary.database.DBHelper;
import com.quankm.healthdiary.pojo.User;

/**
 * Created by Infernocorez on 6/10/2016.
 */

public class SharedPrefUtil {

    private static final String PREF_NAME = "HDPreference";
    private Context context;
    private SharedPreferences pref;

    public  SharedPrefUtil(Context context){
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
    }

    public void saveUserPreference(User user){
        // save user account info into sharedprefference
        if(user != null && user.get_id() > 0){
            SharedPreferences.Editor editor = pref.edit();
            editor.putLong(DBHelper.USER_COL_ID,user.get_id());
            editor.putString(DBHelper.USER_COL_EMAIL,user.getEmail());
            editor.putString(DBHelper.USER_COL_FIRSTNAME,user.getFirstName());
            editor.putString(DBHelper.USER_COL_LASTNAME,user.getLastName());
            editor.putLong(DBHelper.USER_COL_DOB,user.getDateOfBirth());
            editor.putLong(DBHelper.USER_COL_DATEJOINED,user.getDateJoined());
            editor.putInt(DBHelper.USER_COL_SEX,user.getSex());
            editor.putInt(DBHelper.USER_COL_HEIGHT,user.getHeight());
            editor.putFloat(DBHelper.USER_COL_WEIGHT,user.getWeight());
            editor.putString(DBHelper.USER_COL_CLOUDID,user.getCloudID());
            editor.putString(DBHelper.USER_COL_REFERENCECODE,user.getReferenceCode());
            editor.apply();
        }
    }

    public User getUserFromPreference(){
        User user = null;
        long _id = pref.getLong(DBHelper.USER_COL_ID,-1);
        String email = pref.getString(DBHelper.USER_COL_EMAIL,"");
        if(_id>0 && !email.equals("")){
            user = new User();
            user.set_id(_id);
            user.setEmail(email);
            user.setFirstName(pref.getString(DBHelper.USER_COL_FIRSTNAME,""));
            user.setLastName(pref.getString(DBHelper.USER_COL_LASTNAME,""));
            user.setDateOfBirth(pref.getLong(DBHelper.USER_COL_DOB,-1));
            user.setSex((byte) pref.getInt(DBHelper.USER_COL_SEX,-1));
            user.setDateJoined(pref.getLong(DBHelper.USER_COL_DATEJOINED,-1));
            user.setReferenceCode(pref.getString(DBHelper.USER_COL_REFERENCECODE,""));
            user.setHeight(pref.getInt(DBHelper.USER_COL_HEIGHT,-1));
            user.setWeight(pref.getFloat(DBHelper.USER_COL_WEIGHT,-1));
            user.setCloudID(pref.getString(DBHelper.USER_COL_CLOUDID,""));
        }

        return user;
    }

    public void clearUserPreference(){
        pref.edit().clear().apply();
    }
}
