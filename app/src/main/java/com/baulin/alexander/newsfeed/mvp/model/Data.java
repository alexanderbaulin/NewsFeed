package com.baulin.alexander.newsfeed.mvp.model;

import android.util.Log;

import com.baulin.alexander.newsfeed.mvp.interfaces.Model;
import com.baulin.alexander.newsfeed.mvp.model.fromDataBase.DataBase;
import com.baulin.alexander.newsfeed.mvp.model.fromJSON.NewsItem;


import java.util.List;

public class Data implements Model {
    private List<NewsItem> posts;
    private DataBase data = new DataBase();

    @Override
    public List<NewsItem> read() {
        return data.getData();
    }

    @Override
    public void rewrite(List<NewsItem> posts) {
        Log.d("myLogs", "execute transaction ");
        data.setData(posts);
    }

}
