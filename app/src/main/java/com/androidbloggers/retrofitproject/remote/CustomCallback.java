package com.androidbloggers.retrofitproject.remote;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

/**
 * Created On : 15/1/21
 * Author     : Manoj Basavaraja
 * Name       : Manoj DB
 */
public abstract class CustomCallback<T> implements Callback<ResponseBody> {

    abstract void onSuccess(T response);
    abstract void onError(String response);

    Type type;

    /**
     * Type : response is the class type of response model
     * */
    public CustomCallback(Type type){
        this.type = type;
    }

    @EverythingIsNonNull
    @Override
    public void onResponse(Call<ResponseBody> call,
                           Response<ResponseBody> response) {
        String responseData;
        if(call.isExecuted() && response.isSuccessful()){
            try {
                responseData = response.body().string();
                T successResponse = new Gson().fromJson(responseData, type);
                onSuccess(successResponse);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try {
                responseData = response.errorBody().string();
                T errorResponse = new Gson().fromJson(responseData, type);
                onError(response.message());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onFailure(Call<ResponseBody> call,
                          Throwable t) {
        if(call.isCanceled()){
            onError(null); //When User cancels the call
            return;
        }

        if(t instanceof SocketTimeoutException){
            onError("SocketTimeOut");
        } else if(t instanceof UnknownHostException ||
                t instanceof UnknownServiceException || t instanceof SocketException){
            onError("Please check internet connection");
        }else {
            //get throwable value
            String error = t.getLocalizedMessage() != null ? t.getLocalizedMessage() : t.getMessage();
            onError(error);
        }
    }
}