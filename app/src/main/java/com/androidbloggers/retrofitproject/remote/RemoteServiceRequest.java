package com.androidbloggers.retrofitproject.remote;

import com.androidbloggers.retrofitproject.remote.interfaces.OnError;
import com.androidbloggers.retrofitproject.remote.interfaces.OnSuccess;

import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created On : 15/1/21
 * Author     : Manoj Basavaraja
 * Name       : Manoj DB
 */
public abstract class RemoteServiceRequest<T, S> {

    protected abstract T getService();

    protected abstract Call<ResponseBody> getCall(T service);

    protected abstract Type getClassType();

    public Call getServiceApi(OnSuccess<S> onSuccess, OnError onError){
        Call<ResponseBody> call = getCall(getService());
        call.enqueue(new CustomCallback<S>(getClassType()) {
            @Override
            void onSuccess(S response) {
                if(onSuccess != null)
                    onSuccess.onSuccess(response);
            }

            @Override
            void onError(String response) {
                if(onError != null)
                    onError.onError(response);
            }
        });
        return call;
    }
}
