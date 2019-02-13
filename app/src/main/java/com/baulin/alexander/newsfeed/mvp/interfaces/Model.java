package com.baulin.alexander.newsfeed.mvp.interfaces;

import com.baulin.alexander.newsfeed.mvp.model.posts.NewsItem;

import java.util.List;

public interface Model {
    List<NewsItem>  refreshPosts();
}
