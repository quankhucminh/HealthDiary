package com.quankm.healthdiary.dao;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.quankm.healthdiary.FragmentLogin;
import com.quankm.healthdiary.pojo.User;
import com.quankm.healthdiary.utils.JSONBuilder;
import com.quankm.healthdiary.utils.SharedPrefUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Infernocorez on 6/3/2016.
 */
public class UserDAO {

    private static final String TAG = "quankm";

    public void insert(final User user) {

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("userCreateJSON", JSONBuilder.buildUserCreateJSON(user));

        client.post("http://healthdiary.esy.es/insert_user.php", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String strResponse = new String(responseBody,"UTF-8");
                    try {
                        JSONArray arrayJSON = new JSONArray(strResponse);
                        JSONObject userJSON = (JSONObject) arrayJSON.get(0);
                        long insertedID = userJSON.getLong("_id");
                        int status = userJSON.getInt("status");
                        if(insertedID > -1 && status > -1){
                            User temp = user;
                            temp.set_id(insertedID);
                            //save pref
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.i(TAG, "Insert User onFailure: "+statusCode);
            }
        });

    }


}
