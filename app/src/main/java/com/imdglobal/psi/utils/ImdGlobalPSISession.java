package com.imdglobal.psi.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.imdglobal.psi.ImdGlobalPSIApplication;

/**
 * Created by rizkyriadhy on 19/06/17.
 */
public class ImdGlobalPSISession {


    public static final String TAG = "com.android.movieapp.session";

    public static class SessionName {
        public static String ACTIVE_ENV = "com.android.movieapp.session-ENV";
        public static String LANG = "com.android.movieapp.session-lang";
        public static String DEVICE = "com.android.movieapp.session-device";
        public static String VERSION = "com.android.movieapp.session-version";
    }

    public static void setSession(String key, String value) {
        SharedPreferences sharedPref = ImdGlobalPSIApplication.getInstance().getApplicationContext().getSharedPreferences(TAG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getSession(String key) {
        SharedPreferences sharedPref = ImdGlobalPSIApplication.getInstance().getApplicationContext().getSharedPreferences(TAG, Context.MODE_PRIVATE);
        return sharedPref.getString(key, "");
    }

    public static void clearSession() {
        SharedPreferences sharedPref = ImdGlobalPSIApplication.getInstance().getApplicationContext().getSharedPreferences(TAG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove(SessionName.LANG);
        editor.remove(SessionName.DEVICE);
        editor.remove(SessionName.VERSION);
        editor.apply();
    }

    public static void removeSession(String key) {
        SharedPreferences sharedPref = ImdGlobalPSIApplication.getInstance().getApplicationContext().getSharedPreferences(TAG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove(key);
        editor.apply();
    }
}
