package com.baulin.alexander.newsfeed.model.posts;



import java.util.List;

public class RootObject {

    public Pagination Pagination;
    public List<NewsItem> NewsItem;

    public RootObject(Pagination pagination, List<NewsItem> newsItem) {
        Pagination = pagination;
        NewsItem = newsItem;
    }

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
