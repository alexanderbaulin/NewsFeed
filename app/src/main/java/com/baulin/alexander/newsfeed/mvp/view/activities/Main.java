package com.baulin.alexander.newsfeed.mvp.view.activities;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.baulin.alexander.newsfeed.MyApplication;
import com.baulin.alexander.newsfeed.R;
import com.baulin.alexander.newsfeed.dagger2.components.AppComponent;
import com.baulin.alexander.newsfeed.dagger2.components.DaggerMainActivityComponent;
import com.baulin.alexander.newsfeed.dagger2.components.MainActivityComponent;
import com.baulin.alexander.newsfeed.dagger2.modules.MainActivityModule;
import com.baulin.alexander.newsfeed.mvp.interfaces.Presenter;
import com.baulin.alexander.newsfeed.mvp.model.fromJSON.NewsItem;
import com.baulin.alexander.newsfeed.mvp.presenter.retrofit.RetrofitAPI;
import com.baulin.alexander.newsfeed.mvp.presenter.retrofit.RetrofitClient;
import com.baulin.alexander.newsfeed.mvp.view.adapter.PostAdapter;
import com.baulin.alexander.newsfeed.mvp.interfaces.View;


import java.util.List;

import javax.inject.Inject;

import dagger.Component;
import retrofit2.Retrofit;



public class Main extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, View {

    RetrofitAPI myAPI;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    PostAdapter adapter;
    @Inject
    Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        MainActivityComponent component = DaggerMainActivityComponent.builder()
                .appComponent(MyApplication.getComponent())
                .build();

        component.injectMainActivity(this);


        Retrofit retrofit = RetrofitClient.getInstance();
        myAPI = retrofit.create(RetrofitAPI.class);

        recyclerView = findViewById(R.id.recView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        swipeRefreshLayout = findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(this);

        adapter = new PostAdapter();

        presenter.setActivity(this);

        if(savedInstanceState == null) {
            Log.d("myLogs", "savedInstanceState == null");
                presenter.getPosts(false);
        } else {
                presenter.getPosts(true);
            Log.d("myLogs", "savedInstanceState != null");
        }
    }


    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        presenter.onStopActivity();
        super.onStop();
    }

    @Override
    public void onRefresh() {
        presenter.getPosts(false);
    }

    @Override
    public void displayData(List<NewsItem> posts) {
        adapter.setPosts(posts);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setRefreshLayout(boolean isRefresh) {
        swipeRefreshLayout.setRefreshing(isRefresh);
    }
}
