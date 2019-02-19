package com.baulin.alexander.newsfeed.mvp.presenter;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;

import com.baulin.alexander.newsfeed.MyApplication;
import com.baulin.alexander.newsfeed.dagger2.components.AppComponent;
import com.baulin.alexander.newsfeed.mvp.model.Data;
import com.baulin.alexander.newsfeed.mvp.interfaces.Model;
import com.baulin.alexander.newsfeed.mvp.model.fromJSON.NewsItem;
import com.baulin.alexander.newsfeed.mvp.model.fromJSON.RootNewsObject;
import com.baulin.alexander.newsfeed.mvp.presenter.retrofit.RetrofitAPI;
import com.baulin.alexander.newsfeed.mvp.presenter.retrofit.RetrofitClient;
import com.baulin.alexander.newsfeed.mvp.view.activities.Main;
import com.baulin.alexander.newsfeed.mvp.interfaces.View;

import java.lang.ref.WeakReference;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class Presenter implements com.baulin.alexander.newsfeed.mvp.interfaces.Presenter {

    @Inject
    Model data;

    RetrofitAPI myAPI;
    CompositeDisposable compositeDisposable;
    private WeakReference<View> view;

    public void setActivity(Main activity) {
        view = new WeakReference<>(activity);
    }

    public Presenter() {
        AppComponent component = MyApplication.getComponent();
        if(component != null) component.injectPresenter(this);

        Retrofit retrofit = RetrofitClient.getInstance();
        myAPI = retrofit.create(RetrofitAPI.class);
    }

    @SuppressLint("CheckResult")
    @Override
    public void getPosts(boolean fromCache) {
        if(fromCache) {
            Observable.just(data.read())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<List<NewsItem>>() {
                        @Override
                        public void accept(List<NewsItem> newsItemJSONS) throws Exception {
                            view.get().displayData(newsItemJSONS);
                            view.get().setRefreshLayout(false);
                        }
                    });
        } else {
            compositeDisposable = new CompositeDisposable();
            compositeDisposable.add(myAPI.getPostsFromJSON()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError(new Consumer<Throwable>() {
                        @SuppressLint("CheckResult")
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            Log.d("error", "error accept ");
                            getPosts(true);
                            compositeDisposable.dispose();
                        }
                    })
                    .subscribe(new Consumer<RootNewsObject>() {
                        @Override
                        public void accept(RootNewsObject posts) throws Exception {
                            Log.d("error", "subcribe " + posts.getNewsItem().get(1).getHeadLine());
                            data.rewrite(posts.getNewsItem());
                            view.get().displayData(posts.getNewsItem());
                            view.get().setRefreshLayout(false);
                        }
                    })
            );
        }
    }

    @Override
    public void onStopActivity() {
        if(compositeDisposable != null)
        compositeDisposable.dispose();
    }
}
