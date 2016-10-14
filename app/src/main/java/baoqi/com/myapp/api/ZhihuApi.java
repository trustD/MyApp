package baoqi.com.myapp.api;

import baoqi.com.myapp.bean.s.zhihu.ZhihuDaily;
import baoqi.com.myapp.bean.s.zhihu.ZhihuStory;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by hasee on 2016/10/11.
 */

public interface ZhihuApi {
    @GET("/api/4/news/latest")
    Observable<ZhihuDaily> getLastDaily();

    @GET("/api/4/news/before/{date}")
    Observable<ZhihuDaily> getTheDaily(@Path("date") String date);

    @GET("/api/4/news/{id}")
    Observable<ZhihuStory> getZhiStory(@Path("id") String id);
}
