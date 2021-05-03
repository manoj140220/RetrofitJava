package com.androidbloggers.retrofitproject.api.interfaces;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created On : 15/1/21
 * Author     : Manoj Basavaraja
 * Name       : Manoj DB
 */
public interface NewsService {
    @GET("top-headlines")
    Call<ResponseBody> getNews(@Query("country") String country, @Query("page") int page,
                               @Query("pageSize") int pageSize);
}