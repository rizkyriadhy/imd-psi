package com.imdglobal.psi.api.rest;

import com.imdglobal.psi.api.rest.data.PsiByDates;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by rizkyriadhy on 19/06/17.
 */
public interface RestConnect {

    @GET(EndPoint.psi)
    Call<PsiByDates.Response> psiByDates(
            @Query("date") String date,
            @Header("api-key") String apikey
    );

    @GET(EndPoint.psi)
    Call<PsiByDates.Response> psiByDateTimes(
            @Query("date_time") String date,
            @Header("api-key") String apikey
    );
}