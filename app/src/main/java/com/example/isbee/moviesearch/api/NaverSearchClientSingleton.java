package com.example.isbee.moviesearch.api;

import com.example.isbee.moviesearch.util.ClientUtility;

import okhttp3.OkHttpClient;
import okhttp3.Request;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public enum NaverSearchClientSingleton {

    INSTANCE("https://openapi.naver.com/");

    private final String baseURL;
    private Retrofit retrofit;
    private OkHttpClient client;
    private NaverSearchAPI.MovieAPI nsApi;

    NaverSearchClientSingleton(String baseURL) {
        this.baseURL = baseURL;
        buildHttpClient();
        buildRetrofit();
        createService();
    }

    private void buildHttpClient() {
        client = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request request = chain.request().newBuilder()
                            .addHeader("X-Naver-Client-Id", ClientUtility.getClientID())
                            .addHeader("X-Naver-Client-Secret", ClientUtility.getClientSecret())
                            .build();
                    return chain.proceed(request);
                })
                .build();
    }

    private void buildRetrofit() {
        retrofit = new Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseURL)
                .build();
    }

    private void createService() {
        nsApi = retrofit.create(NaverSearchAPI.MovieAPI.class);
    }

    public NaverSearchAPI.MovieAPI getService() {
        return nsApi;
    }

    public static NaverSearchClientSingleton getInstance() {
        return INSTANCE;
    }
}
