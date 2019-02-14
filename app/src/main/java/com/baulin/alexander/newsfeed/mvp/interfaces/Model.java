package com.baulin.alexander.newsfeed.mvp.interfaces;

import com.baulin.alexander.newsfeed.mvp.model.fromJSON.NewsItemJSON;

import java.util.List;

public interface Model {
    List<NewsItemJSON> read();
    void rewrite(List<NewsItemJSON> posts);
}
