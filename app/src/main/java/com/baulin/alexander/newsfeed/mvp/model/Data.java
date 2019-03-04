package com.baulin.alexander.newsfeed.mvp.model;


import com.baulin.alexander.newsfeed.mvp.interfaces.Model;
import com.baulin.alexander.newsfeed.mvp.model.dataBase.DataBase;
import com.baulin.alexander.newsfeed.mvp.model.fromJSON.NewsItem;
import com.baulin.alexander.newsfeed.mvp.model.fromJSON.RootNewsObject;
import com.baulin.alexander.newsfeed.mvp.model.retrofit.RetrofitAPI;
import com.baulin.alexander.newsfeed.mvp.model.retrofit.RetrofitClient;


import java.util.List;

import io.reactivex.Observable;

public class Data implements Model {
    private DataBase data = new DataBase();
    private RetrofitAPI client = RetrofitClient.getInstance().create(RetrofitAPI.class);


    public Data() {

    }

    @Override
    public List<NewsItem> read() {
        return data.getData();
    }

    @Override
    public boolean rewrite(List<NewsItem> posts) {
       return data.setData(posts);
    }

    @Override
    public Observable<RootNewsObject> getPostsFromJSON(String sjson) {
        return client.getPostsFromJSON(sjson);
    }

}
