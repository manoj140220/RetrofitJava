package com.androidbloggers.retrofitproject;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.androidbloggers.retrofitproject.api.NewsRequest;
import com.androidbloggers.retrofitproject.api.interfaces.NewsService;
import com.androidbloggers.retrofitproject.api.response.NewsResponseMain;
import com.google.gson.Gson;

/**
 * @Background
 *
 * ExecutorService -> we can use the same to create pool of thread
 *
 * We can specify how many thread pool has to be created like below
 * ExecutorService executorService = Executors.newFixedThreadPool(int numberOfThreadPool);
 *
 * If we just want one thread then we can use the below
 * ExecutorService executorService = Executors.newSingleThreadExecutor();
 *
 * There are many more options available using ExecutorService
 * */
public class MainActivity extends AppCompatActivity {

    private String country = "in";
    private int page = 1;
    private int pageCount = 10;
    private TextView out_put;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        out_put = findViewById(R.id.out_put);

        BaseApplication baseApplication = (BaseApplication)getApplication();

        NewsRequest newsRequest = new NewsRequest(country, page, pageCount,
                baseApplication.getRetrofitInit().create(NewsService.class));
        newsRequest.getServiceApi(this::OnSuccess, this::OnError);
    }

    private void OnError(String newsResponseMain) {
        Log.e("TAG_DATA", new Gson().toJson(newsResponseMain));
    }

    private void OnSuccess(NewsResponseMain newsResponseMain) {
        Log.e("TAG_DATA", new Gson().toJson(newsResponseMain));
        String value = new Gson().toJson(newsResponseMain).toString();
        out_put.setText(value);
    }
}