package com.imdglobal.psi.api;

import android.content.Context;

import com.imdglobal.psi.api.rest.Callback;
import com.imdglobal.psi.api.rest.RestConnect;
import com.imdglobal.psi.api.rest.RestService;
import com.imdglobal.psi.api.rest.data.PsiByDates;
import com.imdglobal.psi.utils.ImdGlobalPSIConst;
import com.imdglobal.psi.utils.ImdGlobalPSISession;

import retrofit2.Call;

/**
 * Created by rizkyriadhy on 19/06/17.
 */

public class ImdGlobalPSI {

    private static ImdGlobalPSI instance;
    private Context context;
    private static String TAG = "ImdGlobalPSI Connection";

    private ImdGlobalPSI(Context context) {
        this.context = context;
    }

    public static synchronized ImdGlobalPSI getInstance(Context context) {
        if (instance == null) {
            instance = new ImdGlobalPSI(context);
            ImdGlobalPSISession.setSession(
                    ImdGlobalPSISession.SessionName.DEVICE, android.provider.Settings.Secure.getString(instance.context.getContentResolver(),
                            android.provider.Settings.Secure.ANDROID_ID));
        }
        return instance;
    }

    public void getPsiByDates(final PsiByDates.Request request, final Callback<PsiByDates.Response> callback) {
        RestConnect conn = getRestConnect(true);
        Call<PsiByDates.Response> call = conn.psiByDates(
                request.date,
                ImdGlobalPSIConst.API_KEY
        );
        call.enqueue(callback);
    }

    public void getPsiByDateTimes(final PsiByDates.Request request, final Callback<PsiByDates.Response> callback) {
        RestConnect conn = getRestConnect(true);
        Call<PsiByDates.Response> call = conn.psiByDateTimes(
                request.date,
                ImdGlobalPSIConst.API_KEY
        );
        call.enqueue(callback);
    }

    private RestConnect getRestConnect(boolean withRetry) {
        if (withRetry)
            return RestService.getInstance(this.context).getConnectionsWithRetry();
        else
            return RestService.getInstance(this.context).getConnections();
    }
}
