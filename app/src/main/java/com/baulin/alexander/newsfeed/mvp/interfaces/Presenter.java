package com.baulin.alexander.newsfeed.mvp.interfaces;

import com.baulin.alexander.newsfeed.mvp.view.activities.Main;

public interface Presenter {
    void getPosts(boolean fromCache);
    void onDestroyActivity();
    void setActivity(Main activity);
}
