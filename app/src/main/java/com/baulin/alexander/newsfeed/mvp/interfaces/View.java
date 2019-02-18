package com.baulin.alexander.newsfeed.mvp.interfaces;

import com.baulin.alexander.newsfeed.mvp.model.fromJSON.NewsItem;

import java.util.List;

public interface View {
    void displayData(List<NewsItem> posts);
    void setRefreshLayout(boolean isRefresh);
}
