package com.qf.chenhao.mr_chenlibrary.retrofitclient;



import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Administrator on 2017/3/27.
 */

public interface ApiService {

    public String BASE_URL = "https://www.baidu.com/";

    @GET
    Observable<String> getJSON(@Url String url);

//    @GET("{url}")
//    Observable<ResponseBody> executeGet(@Path("url") String url, @QueryMap Map<String ,String> maps);
//
//    @POST("{url}")
//    Observable<ResponseBody> executePost(@Path("url") String url, @FieldMap Map<String ,String> maps);

   /* @Multipart
    @POST("{url}")
    Observable<ResponseBody> upLoadFile(
            @Path("url") String url,
            @Part("image\\"; filename=\\"image.jpg") RequestBody avatar);*/

   /* @POST("{url}")
    Call<ResponseBody> uploadFiles(
            @Url("url") String url,
            @Part("filename") String description,
            @PartMap()  Map<String, RequestBody> maps);*/


}
