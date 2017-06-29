package com.imdglobal.psi.api.rest;

import android.content.Context;

import com.imdglobal.psi.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rizkyriadhy on 19/06/17.
 */
public class RestService {

    private static RestService instance;
    private static Retrofit retrofit;
    private static RestConnect restConnect;
    private static RestConnect restConnectWithRetry;
    private static OkHttpClient client;
    private static HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();

    private RestService() {
    }

    public static synchronized RestService getInstance(Context context) {
        if (instance == null) {
            instance = new RestService();
            loggingInterceptor.setLevel(getEnvironmentLevel(BuildConfig.ENVIRONMENT));
        }

        return instance;
    }

    private static HttpLoggingInterceptor.Level getEnvironmentLevel(int env) {
        return env == 0 ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE;
    }

    /**
     * method to get connection without retry
     *
     * @return
     */
    public RestConnect getConnections() {
        if (restConnect == null) {
            client = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .retryOnConnectionFailure(false)
                    .connectTimeout(120000, TimeUnit.MILLISECONDS)
                    .readTimeout(120000, TimeUnit.MILLISECONDS)
                    .writeTimeout(120000, TimeUnit.MILLISECONDS)
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(EndPoint.getApiBaseUri())
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            restConnect = retrofit.create(RestConnect.class);
        }
        return restConnect;
    }

    /**
     * method to get connection with retry
     *
     * @return
     */
    public RestConnect getConnectionsWithRetry() {
        if (restConnectWithRetry == null) {
            client = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .retryOnConnectionFailure(true)
                    .connectTimeout(120000, TimeUnit.MILLISECONDS)
                    .readTimeout(120000, TimeUnit.MILLISECONDS)
                    .writeTimeout(120000, TimeUnit.MILLISECONDS)
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(EndPoint.getApiBaseUri())
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            restConnectWithRetry = retrofit.create(RestConnect.class);
        }
        return restConnectWithRetry;
    }
}
