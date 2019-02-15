package com.baulin.alexander.newsfeed.mvp.interfaces;

import com.baulin.alexander.newsfeed.mvp.model.fromJSON.RootNewsObject;

public interface View {
    void displayData(RootNewsObject posts);
    void setRefreshLayout(boolean isRefresh);
}
