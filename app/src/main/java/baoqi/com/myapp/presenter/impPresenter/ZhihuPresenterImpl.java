package baoqi.com.myapp.presenter.impPresenter;

import com.google.gson.Gson;

import baoqi.com.myapp.api.ApiManage;
import baoqi.com.myapp.bean.s.zhihu.ZhihuDaily;
import baoqi.com.myapp.bean.s.zhihu.ZhihuDailyItem;
import baoqi.com.myapp.presenter.IZhihuPresenter;
import baoqi.com.myapp.presenter.impView.IZhihuFragment;
import rx.Observer;
import rx.Scheduler;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by hasee on 2016/10/11.
 */

public class ZhihuPresenterImpl extends BasePresenterImpl implements IZhihuPresenter{
   private IZhihuFragment mZhihuFragment;
    private Gson gson = new Gson();

    @Override
    public void getLastZhihuNews() {
        Subscription subscription = ApiManage.getInstence().getZhihuService()
                .getLastDaily()
                .map(new Func1<ZhihuDaily, ZhihuDaily>() {
                    @Override
                    public ZhihuDaily call(ZhihuDaily zhihuDaily) {
                        String date = zhihuDaily.getDate();
                        for (ZhihuDailyItem zhihuDailyItem : zhihuDaily.getStories()){
                            zhihuDailyItem.setDate(date);
                        }
                        return zhihuDaily;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZhihuDaily>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mZhihuFragment.hidProgressDialog();
                        mZhihuFragment.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(ZhihuDaily zhihuDaily) {
                        mZhihuFragment.hidProgressDialog();
                        mZhihuFragment.updateList(zhihuDaily);
                    }
                });
        addSubscription(subscription);

    }

    public ZhihuPresenterImpl(IZhihuFragment zhihuFragment) {
        mZhihuFragment = zhihuFragment;
    }





    @Override
    public void getTheDaily(String date) {
        mZhihuFragment.showProgressDialog();
        Subscription x = ApiManage.getInstence().getZhihuService().getTheDaily(date)
                .map(new Func1<ZhihuDaily, ZhihuDaily>() {
                    @Override
                    public ZhihuDaily call(ZhihuDaily zhihuDaily) {
                        //将请求下来的对象中的日期 设置给 详情页面
                        String date = zhihuDaily.getDate();
                        for (ZhihuDailyItem zhihuDailyItem : zhihuDaily.getStories()){
                            zhihuDailyItem.setDate(date);
                        }
                        return zhihuDaily;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZhihuDaily>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mZhihuFragment.hidProgressDialog();
                        mZhihuFragment.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(ZhihuDaily zhihuDaily) {
                        mZhihuFragment.hidProgressDialog();
                        mZhihuFragment.updateList(zhihuDaily);
                    }
                });
        addSubscription(x);

    }

}
