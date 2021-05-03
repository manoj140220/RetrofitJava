package com.androidbloggers.retrofitproject.api;

import com.androidbloggers.retrofitproject.api.interfaces.NewsService;
import com.androidbloggers.retrofitproject.api.response.NewsResponseMain;
import com.androidbloggers.retrofitproject.remote.RemoteServiceRequest;

import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created On : 15/1/21
 * Author     : Manoj Basavaraja
 * Name       : Manoj DB
 */
public class NewsRequest extends RemoteServiceRequest<NewsService, NewsResponseMain> {
    String country;
    int page;
    int pageSize;
    transient NewsService newsService;

    public NewsRequest(String country, int page, int pageSze, NewsService newsService){
        this.country = country;
        this.page = page;
        this.pageSize = pageSze;
        this.newsService = newsService;
    }

    @Override
    protected NewsService getService() {
        return newsService;
    }

    @Override
    protected Call<ResponseBody> getCall(NewsService service) {
        return service.getNews(country, page, pageSize);
    }

    @Override
    protected Type getClassType() {
        return NewsResponseMain.class;
    }
}