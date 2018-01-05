package com.qf.chenhao.mr_chenlibrary.retrofitclient;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/3/27.
 */

public class RetrofitClient {


    private static Context context;

    private static RetrofitClient instance = new RetrofitClient();
    private Retrofit retrofit;
    private OkHttpClient okHttpClient;
    private ApiService apiService;

    /**
     * 单列模式
     * @param context
     * @return
     */
    public static RetrofitClient getInstance(Context context){
        if (RetrofitClient.instance == null){
            RetrofitClient.context = context;
        }
        return instance;
    }

    /**
     * 初始化retrofit
     */
    public RetrofitClient(){
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    /**
     * 创建retrofit对象
     */
    public RetrofitClient create(){
        if (apiService == null){
            apiService = retrofit.create(ApiService.class);
        }
        return this;
    }

    /**
     *使用自定义接口
     * @param <T>
     * @return
     */
    public <T> T create(Class<T> tClass){
        T apiService = retrofit.create(tClass);
        return apiService;
    }

    /**
     * 下载JSON
     * @param jsonUrl
     * @param action1
     */
    public void getDatas(String jsonUrl, Action1<String> action1){
        Observable<String> json = apiService.getJSON(jsonUrl);
                json
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);

    }
}
