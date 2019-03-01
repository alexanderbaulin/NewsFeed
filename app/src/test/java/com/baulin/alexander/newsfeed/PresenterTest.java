package com.baulin.alexander.newsfeed;


import com.baulin.alexander.newsfeed.mvp.model.Data;
import com.baulin.alexander.newsfeed.mvp.model.fromJSON.Image;
import com.baulin.alexander.newsfeed.mvp.model.fromJSON.NewsItem;
import com.baulin.alexander.newsfeed.mvp.model.fromJSON.Pagination;
import com.baulin.alexander.newsfeed.mvp.model.fromJSON.RootNewsObject;
import com.baulin.alexander.newsfeed.mvp.presenter.Presenter;
import com.baulin.alexander.newsfeed.mvp.presenter.retrofit.RetrofitAPI;
import com.baulin.alexander.newsfeed.mvp.view.activities.Main;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import java.util.LinkedList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PresenterTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private Data data;
    @Mock
    private Main activity;
    @Mock
    private RetrofitAPI client;

    private @InjectMocks Presenter presenter;
    private Observable<RootNewsObject> testDataFromWeb;

    @Before
    public void init() {
        presenter.setActivity(activity);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> Schedulers.trampoline());

        List<NewsItem> list = new LinkedList<>();

        NewsItem item = new NewsItem();
        item.setHeadLine("TestHeadLine");
        item.setStory("TestStory");
        item.setDateLine("TestDateLine");
        Image image = new Image();
        image.setPhoto("TestPhoto");
        item.setImage(image);

        list.add(item);

        RootNewsObject rootNewsObject = new RootNewsObject(new Pagination("1", "1", "1", "test"), list);
        testDataFromWeb = Observable.just(rootNewsObject);
    }

    @Test
    public void testGetPostsFromCache() {
        presenter.getPosts(true);
        verify(data).read();
    }

    @Test
    public void testGetPostsFromWeb() {
        when(client.getPostsFromJSON("sjson")).thenReturn(testDataFromWeb);

        presenter.getPosts(false);

        verify(client).getPostsFromJSON("sjson");

    }

    @Test
    public void testGetPostsFromWebError() {
        when(client.getPostsFromJSON("sjson")).thenReturn(testDataFromWeb);

        presenter.getPosts(false);

        verify(data).read();
    }





}
