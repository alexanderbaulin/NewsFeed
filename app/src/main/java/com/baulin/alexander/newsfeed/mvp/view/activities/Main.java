package com.baulin.alexander.newsfeed.mvp.view.activities;

import android.content.pm.ActivityInfo;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.baulin.alexander.newsfeed.MyApplication;
import com.baulin.alexander.newsfeed.R;
import com.baulin.alexander.newsfeed.dagger2.components.DaggerMainActivityComponent;
import com.baulin.alexander.newsfeed.dagger2.components.MainActivityComponent;
import com.baulin.alexander.newsfeed.mvp.interfaces.Presenter;
import com.baulin.alexander.newsfeed.mvp.model.fromJSON.NewsItem;
import com.baulin.alexander.newsfeed.mvp.view.adapter.PostAdapter;
import com.baulin.alexander.newsfeed.mvp.interfaces.View;


import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class  Main extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, View {


    @BindView(R.id.recView)
    RecyclerView recyclerView;
    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swipeRefreshLayout;

    @Inject
    Presenter presenter;

    PostAdapter adapter;
    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        MainActivityComponent component = DaggerMainActivityComponent.builder()
                .appComponent(MyApplication.getComponent())
                .build();

        component.injectMainActivity(this);

        ButterKnife.bind(this);

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(this);

        adapter = new PostAdapter();

        presenter.setActivity(this);

        if(savedInstanceState == null) {
            fixScreenOrientation(true);
            setRefreshLayout(true);
            presenter.getPosts(false);
        } else {
            fixScreenOrientation(true);
            presenter.getPosts(true);
        }


        recyclerView.setOnScrollChangeListener(new android.view.View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(android.view.View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                Log.d("scroll", "getItemCount " + layoutManager.getItemCount());
                Log.d("scroll", "findLastVisibleItemPosition " + layoutManager.findLastVisibleItemPosition());

            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onDestroy() {
        presenter.onDestroyActivity();
        super.onDestroy();
    }

    @Override
    public void onRefresh() {
        fixScreenOrientation(true);
        presenter.getPosts(false);
    }

    @Override
    public void displayData(List<NewsItem> posts) {
        adapter.setPosts(posts);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        setRefreshLayout(false);
        fixScreenOrientation(false);
    }

    @Override
    public void showToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    public void setRefreshLayout(boolean isRefresh) {
        swipeRefreshLayout.setRefreshing(isRefresh);
    }

    public void fixScreenOrientation(boolean fixScreen) {
        if(fixScreen) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        }
    }
}
