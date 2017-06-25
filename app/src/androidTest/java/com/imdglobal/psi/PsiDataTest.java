package com.imdglobal.psi;

import android.support.test.filters.SmallTest;
import android.test.InstrumentationTestCase;

import com.imdglobal.psi.api.rest.EndPoint;
import com.imdglobal.psi.api.rest.RestConnect;
import com.imdglobal.psi.api.rest.data.PsiByDates;
import com.imdglobal.psi.utils.ImdGlobalPSIConst;

import junit.framework.Assert;

import java.lang.annotation.Annotation;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

/**
 * Created by rizkyriadhy on 6/25/17.
 */

public class PsiDataTest extends InstrumentationTestCase {
    private MockRetrofit mockRetrofit;
    private Retrofit retrofit;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        retrofit = new Retrofit.Builder().baseUrl(EndPoint.getApiBaseUri())
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NetworkBehavior behavior = NetworkBehavior.create();

        mockRetrofit = new MockRetrofit.Builder(retrofit)
                .networkBehavior(behavior)
                .build();
    }


    @SmallTest
    public void testRandomPSIRetrieval() throws Exception {
        BehaviorDelegate<RestConnect> delegate = mockRetrofit.create(RestConnect.class);
        RestConnect mockPSIService = new MockPSIDataRestConnect(delegate);

        //Actual Test
        Call<PsiByDates.Response> psi = mockPSIService.psiByDateTimes("2017-06-12T08:00:00", ImdGlobalPSIConst.API_KEY);
        Response<PsiByDates.Response> psiDataResponse = psi.execute();

        //Asserting response
        Assert.assertTrue(psiDataResponse.isSuccessful());
        Assert.assertNotNull(psiDataResponse.body().getRegionMetadata());
        Assert.assertNotNull(psiDataResponse.body().getItems());
        Assert.assertNotNull(psiDataResponse.body().getItems().get(0).getTimestamp());
        Assert.assertEquals(1, psiDataResponse.body().getItems().size());
        Assert.assertEquals("2017-06-12T08:00:00+08:00", psiDataResponse.body().getItems().get(0).getTimestamp());

    }

    @SmallTest
    public void testFailedPSIRetrieval() throws Exception {
        BehaviorDelegate<RestConnect> delegate = mockRetrofit.create(RestConnect.class);
        RestConnect mockPSIService = new MockFailedPSIdataService(delegate);

        //Actual Test
        Call<PsiByDates.Response> psi = mockPSIService.psiByDateTimes("2017-06-12T08:00:0", ImdGlobalPSIConst.API_KEY);
        Response<PsiByDates.Response> psiDataResponse = psi.execute();
        Assert.assertFalse(psiDataResponse.isSuccessful());

        Converter<ResponseBody, PsiByDates.Response> errorConverter = retrofit.responseBodyConverter(PsiByDates.Response.class, new Annotation[0]);
        PsiByDates.Response error = errorConverter.convert(psiDataResponse.errorBody());

        //Asserting response
        Assert.assertEquals(400, psiDataResponse.code());
        Assert.assertEquals("invalid datetime format", error.getMessage());

    }
}