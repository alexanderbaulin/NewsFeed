package com.baulin.alexander.newsfeed.mvp.model.fromJSON;



import java.util.List;

public class RootObject {

    public Pagination Pagination;
    public List<NewsItemJSON> NewsItem;

    public RootObject(Pagination pagination, List<NewsItemJSON> newsItem) {
        Pagination = pagination;
        NewsItem = newsItem;
    }

    public Pagination getPagination() {
        return Pagination;
    }

    public void setPagination(Pagination pagination) {
        Pagination = pagination;
    }

    public List<NewsItemJSON> getNewsItem() {
        return NewsItem;
    }

    public void setNewsItem(List<NewsItemJSON> newsItem) {
        NewsItem = newsItem;
    }

}
