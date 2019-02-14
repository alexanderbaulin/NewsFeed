package com.baulin.alexander.newsfeed.mvp.presenter;

import android.util.Log;

import com.baulin.alexander.newsfeed.mvp.model.Data;
import com.baulin.alexander.newsfeed.mvp.interfaces.Model;
import com.baulin.alexander.newsfeed.mvp.model.fromJSON.RootObject;
import com.baulin.alexander.newsfeed.mvp.presenter.retrofit.RetrofitAPI;
import com.baulin.alexander.newsfeed.mvp.presenter.retrofit.RetrofitClient;
import com.baulin.alexander.newsfeed.mvp.view.Main;
import com.baulin.alexander.newsfeed.mvp.interfaces.View;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class Presenter implements com.baulin.alexander.newsfeed.mvp.interfaces.Presenter {

    Model dataBase = new Data();
    View main;
    RetrofitAPI myAPI;
    CompositeDisposable compositeDisposable;

    public void setActivity(Main activity) {
        main = activity;
    }

    @Override
    public void refreshPosts2() {
        main.displayData(new RootObject(null, dataBase.refreshPosts()));
    }

    public Presenter() {
        Retrofit retrofit = RetrofitClient.getInstance();
        myAPI = retrofit.create(RetrofitAPI.class);
        compositeDisposable = new CompositeDisposable();
    }


    @Override
    public void refreshPosts() {
        compositeDisposable.add(myAPI.getPostsFromJSON()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RootObject>() {
                    @Override
                    public void accept(RootObject posts) throws Exception {
                        dataBase.set(posts.getNewsItem());
                        Log.d("myLogs", "after ==========================");
                        main.displayData(posts);
                        main.setRefreshLayout(false);
                        Log.d("myLogs", "pageNumber " + posts.getNewsItem().get(1).getHeadLine());
                    }
                })
        );
    }

    @Override
    public void onStopActivity() {
        compositeDisposable.dispose();
    }
}
