package com.baulin.alexander.newsfeed.mvp.presenter;

import android.util.Log;

import com.baulin.alexander.newsfeed.mvp.model.Data;
import com.baulin.alexander.newsfeed.mvp.interfaces.Model;
import com.baulin.alexander.newsfeed.mvp.model.fromJSON.RootObject;
import com.baulin.alexander.newsfeed.mvp.presenter.retrofit.RetrofitAPI;
import com.baulin.alexander.newsfeed.mvp.presenter.retrofit.RetrofitClient;
import com.baulin.alexander.newsfeed.mvp.view.Main;
import com.baulin.alexander.newsfeed.mvp.interfaces.View;

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

    public Presenter() {
        Retrofit retrofit = RetrofitClient.getInstance();
        myAPI = retrofit.create(RetrofitAPI.class);
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void getPosts(boolean fromCache) {
        if(fromCache) {
            main.displayData(new RootObject(null, dataBase.read()));
        } else {
            compositeDisposable.add(myAPI.getPostsFromJSON()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<RootObject>() {
                        @Override
                        public void accept(RootObject posts) throws Exception {
                            dataBase.rewrite(posts.getNewsItem());
                            main.displayData(posts);
                            main.setRefreshLayout(false);
                            Log.d("myLogs", "pageNumber " + posts.getNewsItem().get(1).getHeadLine());
                        }
                    })
            );
        }
    }

    @Override
    public void onStopActivity() {
        compositeDisposable.dispose();
    }
}
