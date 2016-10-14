package baoqi.com.myapp.presenter.impPresenter;

import android.util.Log;

import com.google.gson.Gson;

import baoqi.com.myapp.api.ApiManage;
import baoqi.com.myapp.bean.s.MeiziData;
import baoqi.com.myapp.presenter.IMeiziPresenter;
import baoqi.com.myapp.presenter.impView.IMeiziFragment;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hasee on 2016/10/10.
 */

public class MeiziPresenterlmpl extends BasePresenterImpl implements IMeiziPresenter{
    private IMeiziFragment mMeiziFragment;
    private Gson gson = new Gson();

    public MeiziPresenterlmpl(IMeiziFragment meiziFragment) {
        mMeiziFragment = meiziFragment;
    }

    @Override
    public void getMeiziData(int t) {
        mMeiziFragment.showProgressDialog();
        Subscription subscription = ApiManage.getInstence().getGankService()
                .getMeizhiData(t)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MeiziData>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mMeiziFragment.hidProgressDialog();
                        mMeiziFragment.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(MeiziData data) {
                        Log.w("==0",data.getResults().get(0).toString());
                        mMeiziFragment.hidProgressDialog();
                        mMeiziFragment.updateMeiziData(data.getResults());
                    }
                });
        addSubscription(subscription);

    }

    @Override
    public void getVedioData(int t) {

    }
}
