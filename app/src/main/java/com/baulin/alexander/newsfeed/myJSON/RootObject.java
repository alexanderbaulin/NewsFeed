package com.baulin.alexander.newsfeed.myJSON;

import com.baulin.alexander.newsfeed.myJSON.NewsItem;
import com.baulin.alexander.newsfeed.myJSON.Pagination;

import java.util.List;

public class RootObject {

    private com.baulin.alexander.newsfeed.myJSON.Pagination Pagination;
    private List<com.baulin.alexander.newsfeed.myJSON.NewsItem> NewsItem;

    public Pagination getPagination() {
        return Pagination;
    }

    public void setPagination(Pagination pagination) {
        Pagination = pagination;
    }

    public List<NewsItem> getNewsItem() {
        return NewsItem;
    }

    public void setNewsItem(List<NewsItem> newsItem) {
        NewsItem = newsItem;
    }

}
