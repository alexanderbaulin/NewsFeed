package com.baulin.alexander.newsfeed.dagger2.modules;

import android.content.Context;

import com.baulin.alexander.newsfeed.mvp.interfaces.Model;
import com.baulin.alexander.newsfeed.mvp.model.Data;
import com.baulin.alexander.newsfeed.mvp.presenter.retrofit.RetrofitAPI;
import com.baulin.alexander.newsfeed.mvp.presenter.retrofit.RetrofitClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class AppModule {
    private final Context context;

    public AppModule(Context ctx) {
        context = ctx;
    }

    @Provides
    @Singleton
    Context context() {
        return context;
    }

    @Provides
    @Singleton
    Model getData() {
        return new Data();
    }

    @Provides
    @Singleton
    RetrofitAPI getRetrofitClient() {
        return RetrofitClient.getInstance().create(RetrofitAPI.class);
    }
}