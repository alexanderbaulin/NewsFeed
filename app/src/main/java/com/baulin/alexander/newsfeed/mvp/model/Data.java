package com.baulin.alexander.newsfeed.mvp.model;

import com.baulin.alexander.newsfeed.mvp.interfaces.Model;
import com.baulin.alexander.newsfeed.mvp.model.posts.NewsItem;

import java.util.List;

public class Data implements Model {
    private List<NewsItem> posts;

    @Override
    public List<NewsItem> refreshPosts() {

        return null;
    }

}
