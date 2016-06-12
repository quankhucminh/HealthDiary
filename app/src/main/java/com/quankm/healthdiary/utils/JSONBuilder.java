package com.quankm.healthdiary.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.quankm.healthdiary.database.DBHelper;
import com.quankm.healthdiary.pojo.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Infernocorez on 6/10/2016.
 */

public class JSONBuilder {

    public static String buildUserCreateJSON(User user) {

        String resultJSON = "";

        if (user != null) {
            ArrayList<HashMap<String, String>> list = new ArrayList<>();
            HashMap<String, String> map = new HashMap<>();
            map.put(DBHelper.USER_COL_EMAIL, user.getEmail());
            map.put(DBHelper.USER_COL_PASSWORD, user.getPassword());
            map.put(DBHelper.USER_COL_FIRSTNAME, user.getFirstName());
            map.put(DBHelper.USER_COL_LASTNAME, user.getLastName());
            map.put(DBHelper.USER_COL_DOB, user.getDateOfBirth() + "");
            map.put(DBHelper.USER_COL_SEX, user.getSex()+"");
            map.put(DBHelper.USER_COL_DATEJOINED,user.getDateJoined()+"");
            list.add(map);
            Gson gson = new GsonBuilder().create();
            resultJSON = gson.toJson(list);
        }

        return resultJSON;
    }

    public static String buildUserLoginJSON(User user){
        String resultJSON = "";
        if (user != null) {
            ArrayList<HashMap<String, String>> list = new ArrayList<>();
            HashMap<String, String> map = new HashMap<>();
            map.put(DBHelper.USER_COL_EMAIL, user.getEmail());
            map.put(DBHelper.USER_COL_PASSWORD, user.getPassword());
            list.add(map);
            Gson gson = new GsonBuilder().create();
            resultJSON = gson.toJson(list);
        }

        return resultJSON;
    }

    public static User parseUserFromJSON(JSONObject userJSON){
        User user = new User();
        try {
            user.set_id(userJSON.getLong(DBHelper.USER_COL_ID));
            user.setEmail(userJSON.getString(DBHelper.USER_COL_EMAIL));
            user.setFirstName(userJSON.getString(DBHelper.USER_COL_FIRSTNAME));
            user.setLastName(userJSON.getString(DBHelper.USER_COL_LASTNAME));
            user.setDateOfBirth(userJSON.getLong(DBHelper.USER_COL_DOB));
            user.setDateJoined(userJSON.getLong(DBHelper.USER_COL_DATEJOINED));
            user.setSex((byte) userJSON.getInt(DBHelper.USER_COL_SEX));
            user.setHeight(userJSON.getInt(DBHelper.USER_COL_HEIGHT));
            user.setWeight((float) userJSON.getDouble(DBHelper.USER_COL_WEIGHT));
            user.setCloudID(userJSON.getString(DBHelper.USER_COL_CLOUDID));
            user.setReferenceCode(userJSON.getString(DBHelper.USER_COL_REFERENCECODE));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }
}
