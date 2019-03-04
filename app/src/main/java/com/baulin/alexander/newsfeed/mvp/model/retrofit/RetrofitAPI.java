package com.baulin.alexander.newsfeed.mvp.model.retrofit;




import com.baulin.alexander.newsfeed.mvp.model.fromJSON.RootNewsObject;



import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitAPI {

    @GET("feeds/newsdefaultfeeds.cms")
    Observable<RootNewsObject> getPostsFromJSON(@Query("feedtype") String feedtype);

}
