package com.baulin.alexander.newsfeed.mvp.view.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.Component;
import retrofit2.Retrofit;



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
            //Log.d("myLogs", "savedInstanceState == null");
                presenter.getPosts(false);
        } else {
                presenter.getPosts(true);
            //Log.d("myLogs", "savedInstanceState != null");
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
