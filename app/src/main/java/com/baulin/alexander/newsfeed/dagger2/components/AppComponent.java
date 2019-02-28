package com.baulin.alexander.newsfeed.dagger2.components;

import android.content.Context;

import com.baulin.alexander.newsfeed.dagger2.modules.AppModule;
import com.baulin.alexander.newsfeed.mvp.interfaces.Model;
import com.baulin.alexander.newsfeed.mvp.model.Data;
import com.baulin.alexander.newsfeed.mvp.presenter.Presenter;
import com.baulin.alexander.newsfeed.mvp.presenter.retrofit.RetrofitAPI;
import com.baulin.alexander.newsfeed.mvp.view.adapter.PostAdapter;

import javax.inject.Singleton;

import dagger.Component;
import io.reactivex.disposables.CompositeDisposable;

@Singleton
@Component(modules = { AppModule.class})
public interface AppComponent {
    Context getContext();
    Model getData();
    void injectPresenter(Presenter presenter);
    void injectPostAdapter(PostAdapter adapter);

}
