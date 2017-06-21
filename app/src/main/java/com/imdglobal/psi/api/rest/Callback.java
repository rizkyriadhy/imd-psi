package com.imdglobal.psi.api.rest;

import com.imdglobal.psi.ImdGlobalPSIApplication;
import com.imdglobal.psi.R;
import com.imdglobal.psi.api.rest.data.RestResponseBase;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by rizkyriadhy on 22/04/17.
 */
public abstract class Callback<T extends RestResponseBase> implements retrofit2.Callback<T> {
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            if (response.body() != null) {
                if (response.body().getMessage() == null) {
                    onSuccess(response.code(), response.body());
                } else {
                    onFailed(response.code(), response.body().getMessage());
                }
            } else {
                onFailed(response.code(), ImdGlobalPSIApplication.context.getString(R.string.error_no_data_received));
            }
        } else {
            Gson gson = new Gson();
            try {
                RestResponseBase body = gson.fromJson(response.errorBody().string(), RestResponseBase.class);
                if (body != null) {
                    onFailed(response.code(), body.getMessage());
                } else {
                    onFailed(response.code(), ImdGlobalPSIApplication.context.getString(R.string.error_something));
                }
            } catch (Exception e) {
                onFailed(response.code(), ImdGlobalPSIApplication.context.getString(R.string.error_failed_data));
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onFailed(600, t.getMessage());
    }

    public abstract void onSuccess(int code, T body);

    public abstract void onFailed(int code, String message);
}
