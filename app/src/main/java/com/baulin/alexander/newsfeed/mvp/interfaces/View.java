package com.baulin.alexander.newsfeed.mvp.interfaces;

import com.baulin.alexander.newsfeed.mvp.model.posts.NewsItem;
import com.baulin.alexander.newsfeed.mvp.model.posts.RootObject;

import java.util.List;

public interface View {
    void displayData(RootObject posts);
    void setRefreshLayout(boolean isRefresh);
}
