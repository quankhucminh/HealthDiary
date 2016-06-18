package com.quankm.healthdiary;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dmitrymalkovich.android.ProgressFloatingActionButton;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.quankm.healthdiary.database.DBHelper;
import com.quankm.healthdiary.pojo.User;
import com.quankm.healthdiary.utils.JSONBuilder;
import com.quankm.healthdiary.utils.NetworkUtil;
import com.quankm.healthdiary.utils.PasswordUtil;
import com.quankm.healthdiary.utils.RegexValidator;
import com.quankm.healthdiary.utils.SharedPrefUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;

public class ActivityLogin extends AppCompatActivity implements Button.OnClickListener {

    private static final String TAG = "quankm";
    private TextView tvSignUp;
    private FloatingActionButton fabSignIn;
    private EditText edtEmail;
    private EditText edtPassword;
    private CoordinatorLayout coordinatorLayout;
    private Context context;
    private LinearLayout form;
    private ProgressDialog progressDialog;
    private TextInputLayout tilEmail;
    private TextInputLayout tilPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = this;

        checkLogin(this);

        getSupportActionBar().hide();
        tvSignUp = (TextView) findViewById(R.id.tvSignUp);
        fabSignIn = (FloatingActionButton) findViewById(R.id.fabSignIn);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        form = (LinearLayout) findViewById(R.id.layoutForm);
        tilEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        tilPassword = (TextInputLayout) findViewById(R.id.input_layout_password);
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Authenticating...");
        progressDialog.setIndeterminate(true);

        fabSignIn.setOnClickListener(this);
        tvSignUp.setOnClickListener(this);
        edtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateEmail(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validatePassword(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void displaySignUp() {

        Intent intent = new Intent(this, ActivitySignUp.class);
        Pair<View, String> pair1 = Pair.create((View) fabSignIn, fabSignIn.getTransitionName());
        Pair<View, String> pair2 = Pair.create((View) form, form.getTransitionName());
        Pair<View, String> pair3 = Pair.create((View) tvSignUp, tvSignUp.getTransitionName());
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, pair1, pair2, pair3);
        startActivity(intent, options.toBundle());
    }

    private void checkLogin(Context context) {
        SharedPrefUtil prefUtil = new SharedPrefUtil(context);
        User user = prefUtil.getUserFromPreference();
        if (user != null && user.get_id() > 0) {
            Intent intent = new Intent(this, ActivityMain.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fabSignIn:
                progressDialog.show();
                if (NetworkUtil.isConnected(context)) {
                    String email = edtEmail.getText().toString();
                    String password = edtPassword.getText().toString();
                    if (validateLogin(email, password)) {
                        User user = new User();
                        user.setEmail(email);
                        password = PasswordUtil.hashPassword(password);
                        user.setPassword(password);
                        login(user);
                    } else
                        progressDialog.hide();
                } else {
                    progressDialog.hide();
                    Snackbar.make(coordinatorLayout, R.string.snack_no_internet, Snackbar.LENGTH_LONG).show();
                }
                break;
            case R.id.tvSignUp:
                displaySignUp();
                break;
        }
    }

    public void login(final User user) {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("userLoginJSON", JSONBuilder.buildUserLoginJSON(user));

        client.post("http://healthdiary.esy.es/login_user.php", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                progressDialog.hide();
                try {
                    String strResponse = new String(responseBody, "UTF-8");
                    try {
                        JSONArray arrayJSON = new JSONArray(strResponse);
                        JSONObject userJSON = (JSONObject) arrayJSON.get(0);
                        long _id = userJSON.getLong(DBHelper.USER_COL_ID);
                        if (_id > 0) {
                            User userTemp = JSONBuilder.parseUserFromJSON(userJSON);
                            if (userTemp.get_id() > 0) {
                                SharedPrefUtil sharedPrefUtil = new SharedPrefUtil(context);
                                sharedPrefUtil.saveUserPreference(userTemp);
                            }

                            Intent intent = new Intent(context, ActivityMain.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        } else {
                            new AlertDialog.Builder(context)
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
                progressDialog.hide();
                Log.i(TAG, "Login - onFailure: " + statusCode);
            }
        });
    }

    private boolean validateEmail(String email) {
        if (email.trim().isEmpty()) {
            String message = getResources().getString(R.string.validate_empty_email);
            tilEmail.setErrorEnabled(true);
            tilEmail.setError(message);
            return false;
        } else if (!RegexValidator.validate(email, RegexValidator.PATTERN_EMAL)) {
            String message = getResources().getString(R.string.validate_invalid_email);
            tilEmail.setErrorEnabled(true);
            tilEmail.setError(message);
            return false;
        }
        tilEmail.setErrorEnabled(false);
        tilEmail.setError(null);
        return true;

    }

    private boolean validatePassword(String password) {
        if (password.trim().isEmpty()) {
            String message = getResources().getString(R.string.validate_empty_password);
            tilPassword.setErrorEnabled(true);
            tilPassword.setError(message);
            return false;
        }
        tilPassword.setErrorEnabled(false);
        tilPassword.setError(null);
        return true;

    }

    private boolean validateLogin(String email, String password) {
        if (!validateEmail(email))
            return false;
        if (!validatePassword(password))
            return false;
        return true;
    }
}
