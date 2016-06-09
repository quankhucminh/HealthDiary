package com.quankm.healthdiary.dao;

import android.content.ContentValues;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.quankm.healthdiary.database.DBHelper;
import com.quankm.healthdiary.pojo.User;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Infernocorez on 6/3/2016.
 */
public class UserDAO {

    private static final String TAG = "quankm";

    public void insert(User user) {

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("userCreateJSON",buildUserCreateJSON(user));

        client.post("http://healthdiary.esy.es/insert_user.php", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String responseJSON = new String(responseBody,"UTF-8");
                    Log.i(TAG, "onSuccess: "+responseJSON);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.i(TAG, "onFailure: "+statusCode);
            }
        });

    }

    private String buildUserCreateJSON(User user) {

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
            list.add(map);
            Gson gson = new GsonBuilder().create();
            resultJSON = gson.toJson(list);
        }

        return resultJSON;
    }
}
