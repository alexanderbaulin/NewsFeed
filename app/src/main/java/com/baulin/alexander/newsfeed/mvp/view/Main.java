package com.baulin.alexander.newsfeed.mvp.view;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.baulin.alexander.newsfeed.R;
import com.baulin.alexander.newsfeed.mvp.interfaces.Presenter;
import com.baulin.alexander.newsfeed.mvp.presenter.retrofit.RetrofitAPI;
import com.baulin.alexander.newsfeed.mvp.presenter.retrofit.RetrofitClient;
import com.baulin.alexander.newsfeed.mvp.view.adapter.PostAdapter;
import com.baulin.alexander.newsfeed.mvp.interfaces.View;
import com.baulin.alexander.newsfeed.mvp.model.fromJSON.RootObject;


import retrofit2.Retrofit;



public class Main extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, View {

    RetrofitAPI myAPI;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    PostAdapter adapter;
    Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Retrofit retrofit = RetrofitClient.getInstance();
        myAPI = retrofit.create(RetrofitAPI.class);

        recyclerView = findViewById(R.id.recView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        swipeRefreshLayout = findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(this);

        adapter = new PostAdapter(this);

        presenter = new com.baulin.alexander.newsfeed.mvp.presenter.Presenter();
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
    public void displayData(RootObject posts) {
        adapter.setPosts(posts);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setRefreshLayout(boolean isRefresh) {
        swipeRefreshLayout.setRefreshing(isRefresh);
    }
}
