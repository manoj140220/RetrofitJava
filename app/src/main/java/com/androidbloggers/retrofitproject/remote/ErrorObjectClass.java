package com.androidbloggers.retrofitproject.remote;

/**
 * Created On : 15/1/21
 * Author     : Manoj Basavaraja
 * Name       : Manoj DB
 */
public class ErrorObjectClass<S> {

    String errorMessage;

    public ErrorObjectClass(String error) {
        errorMessage = error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}