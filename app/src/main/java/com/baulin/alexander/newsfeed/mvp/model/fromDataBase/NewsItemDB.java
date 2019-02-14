package com.baulin.alexander.newsfeed.mvp.model.fromDataBase;

import io.realm.RealmObject;

public class NewsItemDB extends RealmObject {
    private String title;
    private String story;
    private String data;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
