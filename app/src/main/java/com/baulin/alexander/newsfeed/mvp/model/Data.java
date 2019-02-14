package com.baulin.alexander.newsfeed.mvp.model;

import android.util.Log;

import com.baulin.alexander.newsfeed.mvp.interfaces.Model;
import com.baulin.alexander.newsfeed.mvp.model.fromDataBase.DataBase;
import com.baulin.alexander.newsfeed.mvp.model.fromJSON.NewsItemJSON;


import java.util.List;

public class Data implements Model {
    private List<NewsItemJSON> posts;
    private DataBase dataBase = new DataBase();

    @Override
    public List<NewsItemJSON> read() {
        return dataBase.getData();
    }

    @Override
    public void rewrite(List<NewsItemJSON> posts) {
        Log.d("myLogs", "execute transaction ");
        dataBase.setData(posts);
    }

}
