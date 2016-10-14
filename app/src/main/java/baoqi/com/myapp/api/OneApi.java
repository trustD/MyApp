package baoqi.com.myapp.api;

import baoqi.com.myapp.bean.s.one.OneList;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by hasee on 2016/10/13.
 */

public interface OneApi {
    @GET("http://v3.wufazhuce.com:8000/api/hp/bymonth/{date}")
    Observable<OneList> getOnesData(@Path("date") String date);
}
