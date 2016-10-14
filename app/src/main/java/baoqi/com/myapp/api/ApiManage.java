package baoqi.com.myapp.api;

import baoqi.com.myapp.bean.s.news.News;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hasee on 2016/10/10.
 */

public class ApiManage {


    public static OkHttpClient client = new OkHttpClient.Builder().build();
    public static ApiManage apiManage;
    public static ApiManage getInstence() {
        if (apiManage == null) {
            synchronized (ApiManage.class) {
                if (apiManage == null) {
                    apiManage = new ApiManage();
                }
            }
        }
        return apiManage;
    }
    public GankApi gank;
    public GankApi getGankService(){
        gank = new Retrofit.Builder()
                .baseUrl("http://gank.io")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(GankApi.class);
        return gank;
    }

    public ZhihuApi mZhihuApi;
    public ZhihuApi getZhihuService(){
        mZhihuApi = new Retrofit.Builder()
                .baseUrl("http://news-at.zhihu.com")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ZhihuApi.class);
        return mZhihuApi;
    }

    public NewsApi mNewsApi;
    public NewsApi getNewsService(){
        mNewsApi = new Retrofit.Builder()
                .baseUrl("http://c.m.163.com")
                //增加返回值为Observable<T>的支持
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                //增加返回值为Gson的支持(以实体类返回)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(NewsApi.class);
        return mNewsApi;
    }

    public OneApi mOneApi;
    public OneApi getOneService(){
        mOneApi = new Retrofit.Builder()
                .baseUrl("http://v3.wufazhuce.com:8000/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(OneApi.class);
        return mOneApi;
    }

}
