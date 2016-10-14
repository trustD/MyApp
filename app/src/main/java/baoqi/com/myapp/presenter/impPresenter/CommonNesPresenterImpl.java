package baoqi.com.myapp.presenter.impPresenter;

import baoqi.com.myapp.api.ApiManage;
import baoqi.com.myapp.api.NewsApi;
import baoqi.com.myapp.bean.s.news.NewsList;
import baoqi.com.myapp.bean.s.news.newslist.BokeList;
import baoqi.com.myapp.bean.s.news.newslist.CaiJing;
import baoqi.com.myapp.bean.s.news.newslist.DianyingList;
import baoqi.com.myapp.bean.s.news.newslist.FootnewsList;
import baoqi.com.myapp.bean.s.news.newslist.JunShiList;
import baoqi.com.myapp.bean.s.news.newslist.KeJiList;
import baoqi.com.myapp.bean.s.news.newslist.QiCheList;
import baoqi.com.myapp.bean.s.news.newslist.XiaoHuaList;
import baoqi.com.myapp.bean.s.news.newslist.YuleList;
import baoqi.com.myapp.presenter.impView.ICommonNewsFragment;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hasee on 2016/10/14.
 */

public class CommonNesPresenterImpl extends BasePresenterImpl {
    ICommonNewsFragment mCommonNewsFragment;

    private Subscription mSubscription;

    public CommonNesPresenterImpl(ICommonNewsFragment commonNewsFragment) {
        mCommonNewsFragment = commonNewsFragment;
    }

    public void getWebNews(String type, int id) {

        NewsApi newsApi = ApiManage.getInstence().getNewsService();
        switch (type) {
            //头条
            case "T1348647909107":
                mSubscription = newsApi.getTopNews(id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<NewsList>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(NewsList newsList) {
                                mCommonNewsFragment.updateData(newsList.getList());
                            }
                        });
                break;
            //足球
            case "T1399700447917":
                mSubscription = newsApi.getFootNews(id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<FootnewsList>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(FootnewsList list) {
                                mCommonNewsFragment.updateData(list.getFootnews());
                            }
                        });
                break;
            //军事
            case "T1348648141035":
                mSubscription = newsApi.getJunShi(id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<JunShiList>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(JunShiList list) {
                                mCommonNewsFragment.updateData(list.getListl());
                            }
                        });
                break;
            //娱乐
            case "T1348648517839":
                mSubscription = newsApi.getYule(id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<YuleList>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(YuleList list) {
                                mCommonNewsFragment.updateData(list.getList());
                            }
                        });
                break;
            //财经
            case "T1348648756099":
                mSubscription = newsApi.getCaiJing(id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<CaiJing>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(CaiJing list) {
                                mCommonNewsFragment.updateData(list.getList());
                            }
                        });
                break;
            //科技
            case "T1348649580692":
                mSubscription = newsApi.getKeji(id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<KeJiList>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(KeJiList list) {
                                mCommonNewsFragment.updateData(list.getList());
                            }
                        });
                break;
            //电影
            case "T1348648650048":
                mSubscription = newsApi.getDianYing(id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<DianyingList>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(DianyingList list) {
                                mCommonNewsFragment.updateData(list.getList());
                            }
                        });
                break;
            //汽车
            case "T1348654060988":
                mSubscription = newsApi.getQiChe(id).
                        subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<QiCheList>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(QiCheList list) {
                            mCommonNewsFragment.updateData(list.getList());
                        }
                    });
                 break;
            //笑话
            case "T1350383429665":
                mSubscription = newsApi.getXiaoHua(id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<XiaoHuaList>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(XiaoHuaList list) {
                                mCommonNewsFragment.updateData(list.getList());
                            }
                        });
                break;
            //博客
            case "T1348654151579":
                mSubscription = newsApi.getBoKe(id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<BokeList>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(BokeList list) {
                                mCommonNewsFragment.updateData(list.getList());
                            }
                        });
                break;


        }
        addSubscription(mSubscription);

    }


}
