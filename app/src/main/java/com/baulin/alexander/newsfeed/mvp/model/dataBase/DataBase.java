package com.baulin.alexander.newsfeed.mvp.model.dataBase;

import android.support.annotation.NonNull;

import com.baulin.alexander.newsfeed.mvp.model.fromJSON.Image;
import com.baulin.alexander.newsfeed.mvp.model.fromJSON.NewsItem;

import java.util.LinkedList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class DataBase {

    private Realm realm;

    public DataBase() {
        realm = Realm.getDefaultInstance();
    }

    public boolean setData(List<NewsItem> posts) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                RealmResults<NewsItemDB> posts1 = realm.where(NewsItemDB.class).findAll();
                posts1.deleteAllFromRealm();

                for (NewsItem item : posts) {
                    NewsItemDB realmObject = realm.createObject(NewsItemDB.class);
                    realmObject.setTitle(item.getHeadLine());
                    realmObject.setStory(item.getStory());
                    realmObject.setData(item.getDateLine());
                    realmObject.setUrl(item.getWebURL());
                    realmObject.setPhoto(item.getImage().Photo);
                }
            }
        });
        return true;
    }

    public List<NewsItem> getData() {

        final List<NewsItem> list = new LinkedList<>();

        Realm.Transaction transaction = new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                RealmResults<NewsItemDB> posts = realm.where(NewsItemDB.class).findAll();
                for (NewsItemDB post : posts) {
                    NewsItem item = new NewsItem();
                    item.setHeadLine(post.getTitle());
                    item.setStory(post.getStory());
                    item.setDateLine(post.getData());
                    item.setWebURL(post.getUrl());
                    Image image = new Image();
                    image.setPhoto(post.getPhoto());
                    item.setImage(image);
                  //  Log.d("myLogs", "return item " + post.getTitle());
                    list.add(item);
                }
            }
        };

        realm.executeTransaction(transaction);

        return list;
    }
}
