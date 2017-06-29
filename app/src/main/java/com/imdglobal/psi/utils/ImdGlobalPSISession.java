package com.imdglobal.psi.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.imdglobal.psi.ImdGlobalPSIApplication;

/**
 * Created by rizkyriadhy on 19/06/17.
 */
public class ImdGlobalPSISession {


    public static final String TAG = "com.imdglobal.psi.session";

    public static class SessionName {
        public static String ACTIVE_ENV = "com.imdglobal.psi.session-ENV";
        public static String LANG = "com.imdglobal.psi.session-lang";
        public static String DEVICE = "com.imdglobal.psi.session-device";
        public static String VERSION = "com.imdglobal.psi.session-version";
    }

    /**
     * method to save session
     *
     * @param key
     * @param value
     */
    public static void setSession(String key, String value) {
        SharedPreferences sharedPref = ImdGlobalPSIApplication.getInstance().getApplicationContext().getSharedPreferences(TAG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * method to get session
     *
     * @param key
     * @return
     */
    public static String getSession(String key) {
        SharedPreferences sharedPref = ImdGlobalPSIApplication.getInstance().getApplicationContext().getSharedPreferences(TAG, Context.MODE_PRIVATE);
        return sharedPref.getString(key, "");
    }

    /**
     * method to clear session
     *
     */
    public static void clearSession() {
        SharedPreferences sharedPref = ImdGlobalPSIApplication.getInstance().getApplicationContext().getSharedPreferences(TAG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove(SessionName.LANG);
        editor.remove(SessionName.DEVICE);
        editor.remove(SessionName.VERSION);
        editor.apply();
    }

    /**
     * method to remove some session
     *
     * @param key
     */
    public static void removeSession(String key) {
        SharedPreferences sharedPref = ImdGlobalPSIApplication.getInstance().getApplicationContext().getSharedPreferences(TAG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove(key);
        editor.apply();
    }
}
