package com.baulin.alexander.newsfeed.mvp.presenter.retrofit;




import com.baulin.alexander.newsfeed.mvp.model.posts.RootObject;



import io.reactivex.Observable;
import retrofit2.http.GET;

public interface RetrofitAPI {

    @GET("feeds/newsdefaultfeeds.cms?feedtype=sjson")
    Observable<RootObject> getPosts();

}
