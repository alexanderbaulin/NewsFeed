package com.baulin.alexander.newsfeed.mvp.interfaces;

import com.baulin.alexander.newsfeed.mvp.view.Main;

public interface Presenter {
    void refreshPosts();
    void onStopActivity();
    void setActivity(Main activity);
}
