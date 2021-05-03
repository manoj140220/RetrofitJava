package com.androidbloggers.retrofitproject.api.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created On : 15/1/21
 * Author     : Manoj Basavaraja
 * Name       : Manoj DB
 */
public class NewsArticles {
    @SerializedName("title")
    String title;
    @SerializedName("description")
    String description;
    @SerializedName("urlToImage")
    String urlToImage;
    @SerializedName("content")
    String content;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getContent() {
        return content;
    }
}
