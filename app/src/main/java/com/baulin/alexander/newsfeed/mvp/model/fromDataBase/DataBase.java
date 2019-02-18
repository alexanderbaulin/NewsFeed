package com.baulin.alexander.newsfeed.mvp.model.fromDataBase;

import android.support.annotation.NonNull;
import android.util.Log;

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

    public void setData(List<NewsItem> posts) {
        Log.d("myLogs", "execute transaction 2");

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                Log.d("myLogs", "execute transaction ");
                RealmResults<NewsItemDB> posts1 = realm.where(NewsItemDB.class).findAll();
                posts1.deleteAllFromRealm();

                for (NewsItem item : posts) {
                    NewsItemDB realmObject = realm.createObject(NewsItemDB.class);
                    realmObject.setTitle(item.getHeadLine());
                    realmObject.setStory(item.getStory());
                    realmObject.setData(item.getDateLine());
                    realmObject.setUrl(item.getWebURL());
                    Log.d("myLogs", "add dataItem " + realmObject.getTitle());
                }
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.d("myLogs", "db write success");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(@NonNull Throwable error) {
                Log.d("myLogs", "db write error");
            }
        });
    }

    public List<NewsItem> getData(boolean executeAsyncTransaction) {

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
                    Log.d("myLogs", "return item " + post.getTitle());
                    list.add(item);
                }
            }
        };
        if(executeAsyncTransaction) {
            realm.executeTransactionAsync(transaction);
        } else {
            realm.executeTransaction(transaction);
        }

        return list;
    }
}
