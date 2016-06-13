package com.quankm.healthdiary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.quankm.healthdiary.pojo.User;
import com.quankm.healthdiary.utils.SharedPrefUtil;

public class ActivityLogin extends AppCompatActivity {

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        checkLogin(this);

        getSupportActionBar().hide();
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer,new FragmentLogin(),"frgLogin").commit();

    }

    public void displaySignUp(){
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer,new FragmentCreateAccount(), "frgCreateAccount").addToBackStack(null).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
    }

    private void checkLogin(Context context){
        SharedPrefUtil prefUtil = new SharedPrefUtil(context);
        User user = prefUtil.getUserFromPreference();
        if(user != null && user.get_id() > 0){
            Intent intent = new Intent(this,ActivityMain.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}
