package baoqi.com.myapp.presenter.impPresenter;

import android.content.Context;
import android.util.Log;

import baoqi.com.myapp.api.ApiManage;
import baoqi.com.myapp.bean.s.news.NewsDeatils;
import baoqi.com.myapp.bean.s.news.NewsList;
import baoqi.com.myapp.presenter.INewsPresenter;
import baoqi.com.myapp.presenter.impView.INewsFragment;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hasee on 2016/10/12.
 */

public class NewsPresenter extends BasePresenterImpl implements INewsPresenter {
    private INewsFragment mNewsFragment;
    private Subscription subscription;
    private Subscription mSubscription1;
    private Observable mObservable;

    public NewsPresenter(Context context,INewsFragment newsFragment) {
        mNewsFragment = newsFragment;
    }


    @Override
    public void getNews(String type, int id) {
        subscription = ApiManage.getInstence().getNewsService().getNews(type,id)
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

                        mNewsFragment.updateNews(newsList);
                    }
                });
        addSubscription(subscription);
    }





    @Override
    public void getNewsDeatils(String id) {
        mSubscription1 = ApiManage.getInstence().getNewsService().getNewsDeatils(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsDeatils>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mNewsFragment.hidProgressDialog();
                        mNewsFragment.showError(e.toString());
                    }

                    @Override
                    public void onNext(NewsDeatils deatils) {
                        mNewsFragment.getNewsDeatils(deatils);
                    }
                });
        addSubscription(mSubscription1);
    }

}
