package com.quankm.healthdiary;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.quankm.healthdiary.database.DBHelper;
import com.quankm.healthdiary.pojo.User;
import com.quankm.healthdiary.utils.JSONBuilder;
import com.quankm.healthdiary.utils.PasswordUtil;
import com.quankm.healthdiary.utils.SharedPrefUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLogin extends Fragment implements Button.OnClickListener {

    private static final String TAG = "quankm";
    private Button btnSignUp;
    private Button btnSignIn;
    private EditText edtEmail;
    private EditText edtPassword;
    private ActivityLogin activity;


    public FragmentLogin() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_login, container, false);

        activity = (ActivityLogin) getActivity();
        btnSignUp = (Button) root.findViewById(R.id.btnSignUp);
        btnSignIn = (Button) root.findViewById(R.id.btnSignIn);
        edtEmail = (EditText) root.findViewById(R.id.edtEmail);
        edtPassword = (EditText) root.findViewById(R.id.edtPassword);

        btnSignIn.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSignIn:
                String email = edtEmail.getText().toString();
                String password = PasswordUtil.hashPassword(edtPassword.getText().toString());
                User user = new User();
                user.setEmail(email);
                user.setPassword(password);
                login(user);
                break;
            case R.id.btnSignUp:
                activity.displaySignUp();
                break;
        }
    }

    public void login(final User user){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("userLoginJSON", JSONBuilder.buildUserLoginJSON(user));

        client.post("http://healthdiary.esy.es/login_user.php", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String strResponse = new String(responseBody,"UTF-8");
                    try {
                        JSONArray arrayJSON = new JSONArray(strResponse);
                        JSONObject userJSON = (JSONObject) arrayJSON.get(0);
                        long _id = userJSON.getLong(DBHelper.USER_COL_ID);
                        if(_id>0){
                            User userTemp = JSONBuilder.parseUserFromJSON(userJSON);
                            if(userTemp.get_id() > 0){
                                SharedPrefUtil sharedPrefUtil = new SharedPrefUtil(getActivity());
                                sharedPrefUtil.saveUserPreference(userTemp);
                            }

                            Intent intent = new Intent(getActivity(),ActivityMain.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            getActivity().finish();
                        }
                        else {
                            new AlertDialog.Builder(getActivity())
                                    .setTitle(R.string.alertTitle_Invalid_Login)
                                    .setMessage(R.string.alertMessage_Invalid_Login)
                                    .setPositiveButton(R.string.alertButton_Close, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    }).show();
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
                Log.i(TAG, "Login - onFailure: "+statusCode);
            }
        });
    }


}
