package baoqi.com.myapp.api;

import baoqi.com.myapp.bean.s.news.NewsDeatils;
import baoqi.com.myapp.bean.s.news.NewsList;
import baoqi.com.myapp.bean.s.news.newslist.BaoXueList;
import baoqi.com.myapp.bean.s.news.newslist.BokeList;
import baoqi.com.myapp.bean.s.news.newslist.CBAList;
import baoqi.com.myapp.bean.s.news.newslist.CaiJing;
import baoqi.com.myapp.bean.s.news.newslist.CaiPiaoList;
import baoqi.com.myapp.bean.s.news.newslist.DianTai;
import baoqi.com.myapp.bean.s.news.newslist.DianyingList;
import baoqi.com.myapp.bean.s.news.newslist.FootnewsList;
import baoqi.com.myapp.bean.s.news.newslist.JunShiList;
import baoqi.com.myapp.bean.s.news.newslist.KeJiList;
import baoqi.com.myapp.bean.s.news.newslist.NBAList;
import baoqi.com.myapp.bean.s.news.newslist.QiCheList;
import baoqi.com.myapp.bean.s.news.newslist.QingGan;
import baoqi.com.myapp.bean.s.news.newslist.ShiShangList;
import baoqi.com.myapp.bean.s.news.newslist.ShouJi;
import baoqi.com.myapp.bean.s.news.newslist.XiaoHuaList;
import baoqi.com.myapp.bean.s.news.newslist.YouXiList;
import baoqi.com.myapp.bean.s.news.newslist.YuleList;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by hasee on 2016/10/12.
 */

public interface NewsApi {

    @GET("/nc/article/list/{type}/{id}-20.html")
    Observable<NewsList> getNews(@Path("type")String type,@Path("id")int id);









    @GET("/nc/article/list/T1348647853363/{id}-20.html")
    Observable<NewsList> getTopNews(int id);

    @GET("/nc/article/list/T1399700447917/{id}-20.html")
    Observable<FootnewsList> getFootNews(int id);

    @GET("/nc/article/list/T1348648141035/{id}-20.html")
    Observable<JunShiList> getJunShi(int id);
    @GET("/nc/article/list/T1348648517839/{id}-20.html")
    Observable<YuleList> getYule(int id);
    @GET("/nc/article/list/T1348649580692/{id}-20.html")
    Observable<CaiJing> getCaiJing(int id);
    @GET("/nc/article/list/T1348648650048/{id}-20.html")
    Observable<KeJiList> getKeji(int id);
    @GET("/nc/article/list/T1348654060988/{id}-20.html")
    Observable<DianyingList> getDianYing(int id);
    @GET("/nc/article/list/T1350383429665/{id}-20.html")
    Observable<QiCheList> getQiChe(int id);
    @GET("/nc/article/list/T1348654151579/{id}-20.html")
    Observable<XiaoHuaList> getXiaoHua(int id);
    @GET("/nc/article/list/T1348650593803/{id}-20.html")
    Observable<YouXiList> getYouXi(int id);
    @GET("/nc/article/list/T1348650839000/{id}-20.html")
    Observable<ShiShangList> getShiShang(int id);
    @GET("/nc/article/list/T1370583240249/{id}-20.html")
    Observable<QingGan> getQingGan(int id);
    @GET("/nc/article/list/T1348648517839/{id}-20.html")
    Observable<DianTai> getDianTai(int id);
    @GET("/nc/article/list/T1348649145984/{id}-20.html")
    Observable<NBAList> getNBA(int id);
    @GET("/nc/article/list/T1356600029035/{id}-20.html")
    Observable<CaiPiaoList> getCaiPiao(int id);
    @GET("/nc/article/list/T1348649654285/{id}-20.html")
    Observable<ShouJi> getShouJi(int id);
    @GET("/nc/article/list/T1349837698345/{id}-20.html")
    Observable<BokeList> getBoKe(int id);
    @GET("/nc/article/list/T1349837698345/{id}-20.html")
    Observable<BaoXueList> getBaoXue(int id);
    @GET("/nc/article/list/T1348649475931/{id}-20.html")
    Observable<CBAList> getCBA(int id);




















    @GET("/nc/article/{id}/full.html")
    Observable<NewsDeatils> getNewsDeatils(@Path("id") String id);

}
