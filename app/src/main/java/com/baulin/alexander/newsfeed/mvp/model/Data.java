package com.baulin.alexander.newsfeed.mvp.model;

import android.util.Log;

import com.baulin.alexander.newsfeed.MyApplication;
import com.baulin.alexander.newsfeed.dagger2.components.AppComponent;
import com.baulin.alexander.newsfeed.mvp.interfaces.Model;
import com.baulin.alexander.newsfeed.mvp.model.fromDataBase.DataBase;
import com.baulin.alexander.newsfeed.mvp.model.fromJSON.NewsItem;


import java.util.List;

public class Data implements Model {
    private List<NewsItem> posts;
    private DataBase data = new DataBase();

    public Data() {
        AppComponent component = MyApplication.getComponent();
        Log.d("myLogs", "injectData ");
        if(component != null) component.injectData(this);
    }

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
