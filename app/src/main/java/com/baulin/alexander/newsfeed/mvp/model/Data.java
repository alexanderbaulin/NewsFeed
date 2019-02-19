package com.baulin.alexander.newsfeed.mvp.model;


import com.baulin.alexander.newsfeed.mvp.interfaces.Model;
import com.baulin.alexander.newsfeed.mvp.model.dataBase.DataBase;
import com.baulin.alexander.newsfeed.mvp.model.fromJSON.NewsItem;


import java.util.List;

public class Data implements Model {
    private DataBase data = new DataBase();

    public Data() {
    }

    @Override
    public List<NewsItem> read() {
        return data.getData();
    }

    @Override
    public void rewrite(List<NewsItem> posts) {
        data.setData(posts);
    }

}
