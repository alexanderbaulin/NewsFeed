package com.baulin.alexander.newsfeed.mvp.presenter;

import android.annotation.SuppressLint;

import com.baulin.alexander.newsfeed.MyApplication;
import com.baulin.alexander.newsfeed.dagger2.components.AppComponent;
import com.baulin.alexander.newsfeed.mvp.interfaces.Model;
import com.baulin.alexander.newsfeed.mvp.model.fromJSON.NewsItem;
import com.baulin.alexander.newsfeed.mvp.model.fromJSON.RootNewsObject;
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

public class Presenter implements com.baulin.alexander.newsfeed.mvp.interfaces.Presenter {

    @Inject
    Model data;
    private CompositeDisposable compositeDisposable;
    private WeakReference<View> view;

    public void setActivity(Main activity) {
        view = new WeakReference<>(activity);
    }

    public Presenter() {
        AppComponent component = MyApplication.getComponent();
        if(component != null) component.injectPresenter(this);
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
                        }
                    });
        } else {
            compositeDisposable = new CompositeDisposable();
            compositeDisposable.add(data.getPostsFromJSON("sjson")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<RootNewsObject>() {
                        @Override
                        public void accept(RootNewsObject posts) throws Exception {
                            rewrite(posts);
                            view.get().displayData(posts.getNewsItem());
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            view.get().showToast("Error: " + throwable.getMessage() + ". Check Internet connection");
                            getPosts(true);
                        }
                    })
            );
        }
    }

    @SuppressLint("CheckResult")
    private void rewrite(RootNewsObject posts) {
        Observable.just(data.rewrite(posts.getNewsItem()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {}
                })
       ;
    }

    @Override
    public void onDestroyActivity() {
        if(compositeDisposable != null)
        compositeDisposable.dispose();
    }
}
