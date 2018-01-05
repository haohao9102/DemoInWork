package com.example.administrator.helloworld;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2017/3/24.
 */

public interface GitHubApi {

    @GET("repos/{owner}/{repo}/contributors")
    Call<ResponseBody> contributorsBySimpleGetCall(@Path("owner") String owner,@Path("repo") String repo);

    @GET("v2/book/search?q=%E5%B0%8F%E7%8E%8B%E5%AD%90&tag=&start=0&count=3")
    Call<ResponseBody> getDobanUrl();

    @GET("v2/book/search?q=%E5%B0%8F%E7%8E%8B%E5%AD%90&tag=&start=0")
    Call<ResponseBody> getWebView(@Query("count") Integer count);

    @GET("rest/ticket3.0/cinemas")
    Call<ResponseBody> postMaiZuo(@Query("client_id") String client_id,@Query("sign") String sign,@Query("timestamp")String timestamp,
                                  @Query("cityId") Integer cityId);

    @POST("rest/ticket3.0/films")
    Call<ResponseBody> getMoive(@QueryMap Map<String,String> parms);
}
