package com.baulin.alexander.newsfeed.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.baulin.alexander.newsfeed.R;
import com.baulin.alexander.newsfeed.adapter.PostAdapter;
import com.baulin.alexander.newsfeed.model.posts.RootObject;
import com.baulin.alexander.newsfeed.model.retrofit.RetrofitAPI;
import com.baulin.alexander.newsfeed.model.retrofit.RetrofitClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class Main extends AppCompatActivity {

    RetrofitAPI myAPI;
    RecyclerView recyclerView;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Retrofit retrofit = RetrofitClient.getInstance();
        myAPI = retrofit.create(RetrofitAPI.class);
        recyclerView = findViewById(R.id.recView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetch();


    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void fetch() {
        compositeDisposable.add(myAPI.getPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<RootObject>() {
                @Override
                public void accept(RootObject posts) throws Exception {
                    displayData(posts);
                    Log.d("myLogs", "pageNumber " + posts.getNewsItem().get(1).getHeadLine());
                }
            })
        );
    }

    private void displayData(RootObject posts) {
        PostAdapter adapter = new PostAdapter(this, posts);
        recyclerView.setAdapter(adapter);
    }


    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }
}
