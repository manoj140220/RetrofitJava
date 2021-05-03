package com.androidbloggers.retrofitproject.api.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created On : 15/1/21
 * Author     : Manoj Basavaraja
 * Name       : Manoj DB
 */
public class NewsResponseMain {
    @SerializedName("status")
    String status;
    @SerializedName("totalResults")
    int totalResults;
    @SerializedName("articles")
    List<NewsArticles> articles;

    public String getStatus() {
        return status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public List<NewsArticles> getArticles() {
        return articles;
    }
}