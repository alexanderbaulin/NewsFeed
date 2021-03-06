package com.baulin.alexander.newsfeed.mvp.model.fromJSON;



import java.util.List;

public class RootNewsObject {

    public Pagination Pagination;
    public List<com.baulin.alexander.newsfeed.mvp.model.fromJSON.NewsItem> NewsItem;

    public RootNewsObject(Pagination pagination, List<com.baulin.alexander.newsfeed.mvp.model.fromJSON.NewsItem> newsItem) {
        Pagination = pagination;
        NewsItem = newsItem;
    }

    public Pagination getPagination() {
        return Pagination;
    }

    public void setPagination(Pagination pagination) {
        Pagination = pagination;
    }

    public List<com.baulin.alexander.newsfeed.mvp.model.fromJSON.NewsItem> getNewsItem() {
        return NewsItem;
    }

    public void setNewsItem(List<com.baulin.alexander.newsfeed.mvp.model.fromJSON.NewsItem> newsItem) {
        NewsItem = newsItem;
    }

}
