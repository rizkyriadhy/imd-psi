package com.imdglobal.psi;

import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.imdglobal.psi.api.rest.RestConnect;
import com.imdglobal.psi.api.rest.data.PsiByDates;
import com.imdglobal.psi.api.rest.data.RestResponseBase;
import com.imdglobal.psi.utils.ImdGlobalPSIConst;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Header;
import retrofit2.http.Query;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.Calls;

/**
 * Created by rizkyriadhy on 6/25/17.
 */

public class MockFailedPSIdataService implements RestConnect {
    private static final String TAG = "MockFailedPSIData";

    private final BehaviorDelegate<RestConnect> delegate;

    public MockFailedPSIdataService(BehaviorDelegate<RestConnect> service) {
        this.delegate = service;
    }

    @Override
    public Call<PsiByDates.Response> psiByDates(@Query("date") String date, @Header("api-key") String apikey) {
        RestResponseBase restResponseBase = new RestResponseBase();
        restResponseBase.setCode(400);
        restResponseBase.setMessage("invalid datetime format");

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = "";
        try {
            json = ow.writeValueAsString(restResponseBase);
            Response response = Response.error(400, ResponseBody.create(MediaType.parse("application/json") ,json));
            return delegate.returning(Calls.response(response)).psiByDates("2017-06-", ImdGlobalPSIConst.API_KEY);
        } catch (JsonProcessingException e) {
            Log.e(TAG, "JSON Processing exception:",e);
            return Calls.failure(e);
        }
    }

    @Override
    public Call<PsiByDates.Response> psiByDateTimes(@Query("date_time") String date, @Header("api-key") String apikey) {
        RestResponseBase restResponseBase = new RestResponseBase();
        restResponseBase.setCode(400);
        restResponseBase.setMessage("invalid datetime format");

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = "";
        try {
            json = ow.writeValueAsString(restResponseBase);
            Response response = Response.error(400, ResponseBody.create(MediaType.parse("application/json") ,json));
            return delegate.returning(Calls.response(response)).psiByDates("2017-06-12T08:00:0", ImdGlobalPSIConst.API_KEY);
        } catch (JsonProcessingException e) {
            Log.e(TAG, "JSON Processing exception:",e);
            return Calls.failure(e);
        }
    }
}
