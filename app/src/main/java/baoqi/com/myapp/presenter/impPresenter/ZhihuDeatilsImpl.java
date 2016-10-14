package baoqi.com.myapp.presenter.impPresenter;

import baoqi.com.myapp.api.ApiManage;
import baoqi.com.myapp.bean.s.zhihu.ZhihuStory;
import baoqi.com.myapp.presenter.IZhihuDeatilsPresenter;
import baoqi.com.myapp.presenter.impView.ZhIhuDeatils;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hasee on 2016/10/12.
 */

public class ZhihuDeatilsImpl extends BasePresenterImpl implements IZhihuDeatilsPresenter{
    public ZhihuDeatilsImpl(ZhIhuDeatils zhIhuDeatils) {
        mZhIhuDeatils = zhIhuDeatils;
    }

    //View层
    private ZhIhuDeatils mZhIhuDeatils;

    @Override
    public void getZhiStory(String id) {
        Subscription subscription = ApiManage.getInstence().getZhihuService().getZhiStory(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZhihuStory>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ZhihuStory zhihuStory) {
                        mZhIhuDeatils.showZhihuStory(zhihuStory);
                    }
                });
    }


}
