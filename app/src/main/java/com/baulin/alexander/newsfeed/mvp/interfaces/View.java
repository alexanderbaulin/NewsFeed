package com.baulin.alexander.newsfeed.mvp.interfaces;

import com.baulin.alexander.newsfeed.mvp.model.fromJSON.RootObject;

public interface View {
    void displayData(RootObject posts);
    void setRefreshLayout(boolean isRefresh);
}
