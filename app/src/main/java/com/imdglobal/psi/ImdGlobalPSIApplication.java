package com.imdglobal.psi;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.imdglobal.psi.utils.ImdGlobalPSIConst;
import com.imdglobal.psi.utils.ImdGlobalPSISession;

import java.util.Locale;

/**
 * Created by rizkyriadhy on 19/06/17.
 */

public class ImdGlobalPSIApplication extends Application {

    private static ImdGlobalPSIApplication instance;
    public static Context context;

    public static ImdGlobalPSIApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        context = getApplicationContext();

        PackageInfo pInfo = null;
        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String version = pInfo.versionName;
        String number = Integer.toString(pInfo.versionCode);

        ImdGlobalPSISession.setSession(ImdGlobalPSISession.SessionName.LANG, Locale.getDefault().toString());
        ImdGlobalPSISession.setSession(ImdGlobalPSISession.SessionName.VERSION, "android-" + number + "-" + version);
        ImdGlobalPSISession.setSession(ImdGlobalPSISession.SessionName.ACTIVE_ENV, ImdGlobalPSIConst.API_ENV_PRODUCTION);

    }
}