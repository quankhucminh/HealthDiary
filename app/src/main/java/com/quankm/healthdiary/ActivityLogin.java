package com.quankm.healthdiary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.quankm.healthdiary.database.DBHelper;

public class ActivityLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DBHelper helper = new DBHelper(this);
        helper.getWritableDatabase();
    }
}
