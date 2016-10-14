package baoqi.com.myapp.api;

import baoqi.com.myapp.bean.s.MeiziData;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by hasee on 2016/10/10.
 */

public interface GankApi {
    @GET("/api/data/福利/10/{page}")
    Observable<MeiziData> getMeizhiData(@Path("page") int page);

}
