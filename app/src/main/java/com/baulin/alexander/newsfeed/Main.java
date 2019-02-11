package com.baulin.alexander.newsfeed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.baulin.alexander.newsfeed.adapter.PostAdapter;
import com.baulin.alexander.newsfeed.model.Post;
import com.baulin.alexander.newsfeed.retrofit.IMyAPI;
import com.baulin.alexander.newsfeed.retrofit.RetrofitClient;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class Main extends AppCompatActivity {

    IMyAPI myAPI;
    RecyclerView recyclerView;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Retrofit retrofit = RetrofitClient.getInstance();
        myAPI = retrofit.create(IMyAPI.class);
        recyclerView = findViewById(R.id.recView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        fetch();

    }

    private void fetch() {
        compositeDisposable.add(myAPI.getPosts()
        .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Post>>() {
                    @Override
                    public void accept(List<Post> posts) throws Exception {
                        displayData(posts);
                    }
                })
        );
    }

    private void displayData(List<Post> posts) {
        PostAdapter adapter = new PostAdapter(this, posts);
        recyclerView.setAdapter(adapter);
    }


    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }
}
