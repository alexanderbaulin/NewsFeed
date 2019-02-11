package com.baulin.alexander.newsfeed.retrofit;



import com.baulin.alexander.newsfeed.model.Post;

import java.util.List;


import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IMyAPI {

    @GET("posts")
    Observable<List<Post>> getPosts();

}
