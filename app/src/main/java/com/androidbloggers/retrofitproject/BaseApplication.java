package com.androidbloggers.retrofitproject;

import android.app.Application;

import com.androidbloggers.retrofitproject.remote.RetrofitInit;

import retrofit2.Retrofit;

/**
 * Created On : 15/1/21
 * Author     : Manoj Basavaraja
 * Name       : Manoj DB
 */
public class BaseApplication extends Application {

    private RetrofitInit retrofitInit;

    @Override
    public void onCreate() {
        super.onCreate();
        retrofitInit = new RetrofitInit(getApplicationContext());
    }

    public Retrofit getRetrofitInit() {
        return retrofitInit.getRetrofitObject();
    }
}