package com.baulin.alexander.newsfeed.mvp.interfaces;

import com.baulin.alexander.newsfeed.mvp.model.fromJSON.NewsItem;
import com.baulin.alexander.newsfeed.mvp.model.fromJSON.RootNewsObject;

import java.util.List;

import io.reactivex.Observable;

public interface Model {
    List<NewsItem> read();
    boolean rewrite(List<NewsItem> posts);
    Observable<RootNewsObject> getPostsFromJSON(String sjson);
}
