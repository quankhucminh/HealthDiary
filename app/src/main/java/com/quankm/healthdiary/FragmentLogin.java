package com.quankm.healthdiary;


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
import com.quankm.healthdiary.pojo.User;
import com.quankm.healthdiary.utils.JSONBuilder;
import com.quankm.healthdiary.utils.PasswordUtil;

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

    public void login(User user){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("userLoginJSON", JSONBuilder.buildUserLoginJSON(user));

        client.post("http://healthdiary.esy.es/login_user.php", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String responseJSON = new String(responseBody,"UTF-8");
                    Log.i(TAG, "Login - onSuccess: "+responseJSON);
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
