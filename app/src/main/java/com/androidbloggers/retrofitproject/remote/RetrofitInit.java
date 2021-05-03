package com.androidbloggers.retrofitproject.remote;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created On : 15/1/21
 * Author     : Manoj Basavaraja
 * Name       : Manoj DB
 */
public class RetrofitInit {

    private Retrofit retrofit;
    private static String applicationJson = "application/json";

    public RetrofitInit(Context context){
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(getOkHttpClient(context))
                .build();
    }

    public Retrofit getRetrofitObject(){
        return retrofit;
    }

    private OkHttpClient getOkHttpClient(Context context) {
        try {

            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            final X509TrustManager trustManager = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            };

            final SSLContext sslContext;
            sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new SecureRandom());

            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory,trustManager);
            builder.hostnameVerifier((hostname, session) -> true);

            return builder
                    .connectTimeout(1, TimeUnit.MINUTES)
                    .writeTimeout(1, TimeUnit.MINUTES)
                    .readTimeout(1, TimeUnit.MINUTES)
                    .addInterceptor(getInspector(context))
                    .build();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private Interceptor getInspector(Context context) {
        Interceptor interceptor = null;
        interceptor = chain -> {
            Request original = chain.request();
            Request request;

            request = original.newBuilder()
                    .header("Accept", applicationJson)
                    .header("Authorization", "Bearer c4285fe8f70d4b16b140f850bd83771c")
                    .method(original.method(), original.body())
                    .build();
            return chain.proceed(request);
        };
        return interceptor;
    }
}