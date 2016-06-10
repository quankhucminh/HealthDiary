package com.quankm.healthdiary;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class ActivityLogin extends AppCompatActivity {

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer,new FragmentLogin(),"frgLogin").commit();

    }

    public void displaySignUp(){
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer,new FragmentCreateAccount(), "frgCreateAccount").addToBackStack(null).commit();
    }

    public void finishSignUp(){
        fragmentManager.popBackStack();
    }
}
