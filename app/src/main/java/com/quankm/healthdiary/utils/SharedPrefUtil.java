package com.quankm.healthdiary.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.quankm.healthdiary.pojo.User;

/**
 * Created by Infernocorez on 6/10/2016.
 */

public class SharedPrefUtil {

    private Context context;

    public  SharedPrefUtil(Context context){
        this.context = context;
    }

    public void saveUserPreference(User user){
        // save user account info into sharedprefference
    }

    public SharedPreferences getUserPreference(String email){
        SharedPreferences pref = context.getSharedPreferences(email,Context.MODE_PRIVATE);
        return pref;
    }

    public void clearUserPreference(){
        //clear preference on logout
    }
}
