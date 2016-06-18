package com.quankm.healthdiary.dao;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.quankm.healthdiary.ActivityMain;
import com.quankm.healthdiary.R;
import com.quankm.healthdiary.database.DBHelper;
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

    private Context context;

    public UserDAO(Context context){
        this.context = context;
    }

    public void insert(final User user, final ProgressDialog pDialog) {

        AsyncHttpClient client = new AsyncHttpClient();
        final RequestParams params = new RequestParams();
        params.put("userCreateJSON", JSONBuilder.buildUserCreateJSON(user));

        client.post("http://healthdiary.esy.es/insert_user.php", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                pDialog.hide();
                try {
                    String strResponse = new String(responseBody,"UTF-8");
                    try {
                        Log.i(TAG, "Insert User onSuccess: "+strResponse);
                        JSONArray arrayJSON = new JSONArray(strResponse);
                        JSONObject userJSON = (JSONObject) arrayJSON.get(0);
                        long insertedID = userJSON.getLong(DBHelper.USER_COL_ID);
                        int status = userJSON.getInt("Status");
                        if(insertedID > -1 && status > -1){
                            User temp = user;
                            temp.set_id(insertedID);
                            temp.setReferenceCode(userJSON.getString(DBHelper.USER_COL_REFERENCECODE));
                            SharedPrefUtil prefUtil = new SharedPrefUtil(context);
                            prefUtil.saveUserPreference(temp);
                            Intent intent = new Intent(context, ActivityMain.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                        } else {
                            int errorCode = userJSON.getInt("ErrNo");
                            //create dialog
                            if(errorCode == 1062) {
                                new AlertDialog.Builder(context).setTitle(R.string.alertTitle_Duplicated_Email)
                                        .setMessage(R.string.alertMessage_Duplicated_Email)
                                        .setPositiveButton(R.string.alertButton_Close, new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                            }
                                        }).show();
                            } else {
                                new AlertDialog.Builder(context).setTitle(R.string.alertTitle_Unknow_Error)
                                        .setMessage("Error Code: "+errorCode)
                                        .setPositiveButton(R.string.alertButton_Close, new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                            }
                                        }).show();
                            }
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
                pDialog.hide();
                Log.i(TAG, "Insert User onFailure: "+statusCode);
            }
        });

    }


}
