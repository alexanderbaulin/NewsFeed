package com.baulin.alexander.newsfeed.mvp.presenter.retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class  RetrofitClient {
    private static Retrofit instance;

    public static Retrofit getInstance() {
        if(instance == null) instance = new Retrofit.Builder()
                .baseUrl("http://timesofindia.indiatimes.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return instance;
    }

    private RetrofitClient() {
    }
}
