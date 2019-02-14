package com.baulin.alexander.newsfeed.mvp.model.fromDataBase;

import android.support.annotation.NonNull;
import android.util.Log;

import com.baulin.alexander.newsfeed.mvp.model.fromJSON.NewsItemJSON;

import java.util.LinkedList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class DataBase {

   private Realm realm;

    public DataBase() {
        realm = Realm.getDefaultInstance();
    }

    public void setData(List<NewsItemJSON> posts) {
        Log.d("myLogs", "execute transaction 2");
      //  realm.beginTransaction();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                Log.d("myLogs", "execute transaction ");
                RealmResults<NewsItemDB> posts1 = realm.where(NewsItemDB.class).findAll();
                posts1.deleteAllFromRealm();

                for (NewsItemJSON item : posts) {
                    NewsItemDB realmObject = realm.createObject(NewsItemDB.class);
                    realmObject.setTitle(item.getHeadLine());
                    realmObject.setStory(item.getStory());
                    realmObject.setData(item.getDateLine());
                    Log.d("myLogs", "add dataItem " + realmObject.getTitle());
                }
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.d("myLogs", "db write success");
               // realm.commitTransaction();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(@NonNull Throwable error) {
                Log.d("myLogs", "db write error");
               // realm.cancelTransaction();
            }
        });
    }

    public List<NewsItemJSON> getData() {

        final List<NewsItemJSON> list = new LinkedList<>();;

        realm.executeTransactionAsync(new Realm.Transaction() {
                 @Override
                  public void execute(@NonNull Realm realm) {
                  RealmResults<NewsItemDB> posts = realm.where(NewsItemDB.class).findAll();
                                              for (NewsItemDB post : posts) {
                                                  NewsItemJSON item = new NewsItemJSON();
                                                  item.setHeadLine(post.getTitle());
                                                  item.setStory(post.getStory());
                                                  item.setDateLine(post.getData());
                                                  Log.d("myLogs", "return item " + post.getTitle());
                                                  list.add(item);
                                              }
                                          }
                                      });
        return list;
    }
}
