package com.quankm.healthdiary;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;

import com.quankm.healthdiary.dao.UserDAO;
import com.quankm.healthdiary.pojo.User;
import com.quankm.healthdiary.utils.DateTimeUtil;
import com.quankm.healthdiary.utils.PasswordUtil;
import com.quankm.healthdiary.utils.RegexValidator;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ActivitySignUp extends AppCompatActivity implements Button.OnClickListener{

    private FloatingActionButton fabSignUp;
    private EditText edtEmail;
    private EditText edtPassword;
    private EditText edtFirstName;
    private EditText edtLastName;
    private EditText edtDOB;
    private RadioGroup rdgSex;
    private Context context;
    private ProgressDialog progressDialog;
    private CoordinatorLayout coordinatorLayout;

    private TextInputLayout tilEmail;
    private TextInputLayout tilPassword;
    private TextInputLayout tilFirstName;
    private TextInputLayout tilLastName;
    private TextInputLayout tilDOB;


    private Calendar calendar;
    private SimpleDateFormat formatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();

        context = this;
        fabSignUp = (FloatingActionButton) findViewById(R.id.fabSignUp);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtFirstName = (EditText) findViewById(R.id.edtFirstName);
        edtLastName = (EditText) findViewById(R.id.edtLastName);
        edtDOB = (EditText) findViewById(R.id.edtDOB);
        rdgSex = (RadioGroup) findViewById(R.id.rdgSex);
        tilEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        tilPassword = (TextInputLayout) findViewById(R.id.input_layout_password);
        tilFirstName = (TextInputLayout) findViewById(R.id.input_layout_first_name);
        tilLastName = (TextInputLayout) findViewById(R.id.input_layout_last_name);
        tilDOB = (TextInputLayout) findViewById(R.id.input_layout_dob);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Checking info...");
        progressDialog.setIndeterminate(true);

        fabSignUp.setOnClickListener(this);
        edtDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar selectedDate = Calendar.getInstance();
                        selectedDate.set(year,monthOfYear,dayOfMonth);
                        edtDOB.setText(formatter.format(selectedDate.getTime()));
                    }
                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        calendar = Calendar.getInstance();
        formatter = new SimpleDateFormat("dd/MM/yyyy");

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

        edtFirstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateFirstName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edtLastName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateLastName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edtDOB.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateDOB(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fabSignUp:

                progressDialog.setMessage("Checking info...");
                progressDialog.show();
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                String firstName = edtFirstName.getText().toString();
                String lastName = edtLastName.getText().toString();
                String strDOB = edtDOB.getText().toString();
                byte sex = (byte) rdgSex.getCheckedRadioButtonId();

                if(validateSignUpForm(email,password,firstName,lastName,strDOB,sex)){
                    progressDialog.setMessage("Contacting server...");
                    password = PasswordUtil.hashPassword(password);
                    long dateOfBirth = DateTimeUtil.parseDateStringToMillisecs(strDOB);
                    long dateJoined = System.currentTimeMillis();
                    //generate CloudID
                    User user = new User();
                    user.setEmail(email);
                    user.setPassword(password);
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setDateOfBirth(dateOfBirth);
                    user.setSex(sex);
                    user.setDateJoined(dateJoined);

                    UserDAO userDAO = new UserDAO(context);
                    userDAO.insert(user,progressDialog);
                } else {
                    progressDialog.hide();
                }
                break;
        }
    }

    private boolean validateEmail(String email){
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
        } else if(password.length()<8){
            String message = getResources().getString(R.string.validate_short_password);
            tilPassword.setErrorEnabled(true);
            tilPassword.setError(message);
            return false;
        }
        tilPassword.setErrorEnabled(false);
        tilPassword.setError(null);
        return true;

    }

    private boolean validateFirstName(String firstName){
        if (firstName.trim().isEmpty()) {
            String message = getResources().getString(R.string.validate_empty_first_name);
            tilFirstName.setErrorEnabled(true);
            tilFirstName.setError(message);
            return false;
        } else if (!RegexValidator.validate(firstName, RegexValidator.PATTERN_NAME)) {
            String message = getResources().getString(R.string.validate_invalid_first_name);
            tilFirstName.setErrorEnabled(true);
            tilFirstName.setError(message);
            return false;
        }
        tilFirstName.setErrorEnabled(false);
        tilFirstName.setError(null);
        return true;
    }

    private boolean validateLastName(String lastName){
        if (lastName.trim().isEmpty()) {
            String message = getResources().getString(R.string.validate_empty_last_name);
            tilLastName.setErrorEnabled(true);
            tilLastName.setError(message);
            return false;
        } else if (!RegexValidator.validate(lastName, RegexValidator.PATTERN_NAME)) {
            String message = getResources().getString(R.string.validate_invalid_last_name);
            tilLastName.setErrorEnabled(true);
            tilLastName.setError(message);
            return false;
        }
        tilLastName.setErrorEnabled(false);
        tilLastName.setError(null);
        return true;
    }

    private boolean validateDOB(String strDOB){
        if (strDOB.trim().isEmpty()) {
            String message = getResources().getString(R.string.validate_empty_DOB);
            tilDOB.setErrorEnabled(true);
            tilDOB.setError(message);
            return false;
        } else {
            long dateMillis = DateTimeUtil.parseDateStringToMillisecs(strDOB);
            Calendar calNow = Calendar.getInstance();
            int yearNow = calNow.get(Calendar.YEAR);
            Calendar calDOB = Calendar.getInstance();
            calDOB.setTimeInMillis(dateMillis);
            int yearDOB = calDOB.get(Calendar.YEAR);
            int age = yearNow - yearDOB;
            if(age<0 || age>150){
                String message = getResources().getString(R.string.validate_invalid_DOB);
                tilDOB.setErrorEnabled(true);
                tilDOB.setError(message);
                return false;
            } else if (age == 0){
                int dayNow = calNow.get(Calendar.DAY_OF_YEAR);
                int dayDOB = calDOB.get(Calendar.DAY_OF_YEAR);
                if(dayDOB>dayNow){
                    String message = getResources().getString(R.string.validate_invalid_DOB);
                    tilDOB.setErrorEnabled(true);
                    tilDOB.setError(message);
                    return false;
                }
            }
        }
        tilDOB.setErrorEnabled(false);
        tilDOB.setError(null);
        return true;
    }

    private boolean validateSex(byte sex){
        if (sex<0) {
            Snackbar.make(coordinatorLayout,R.string.validate_empty_sex,Snackbar.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private boolean validateSignUpForm(String email, String password, String firstName, String lastName, String strDOB, byte sex){
        if(!validateEmail(email))
            return false;
        if(!validatePassword(password))
            return false;
        if(!validateFirstName(firstName))
            return false;
        if(!validateLastName(lastName))
            return false;
        if(!validateDOB(strDOB))
            return false;
        if(!validateSex(sex))
            return false;
        return true;
    }
}
