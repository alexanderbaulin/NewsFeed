package com.baulin.alexander.newsfeed.mvp.interfaces;

import com.baulin.alexander.newsfeed.mvp.model.fromJSON.NewsItem;

import java.util.List;

public interface Model {
    List<NewsItem> read();
    boolean rewrite(List<NewsItem> posts);
}
