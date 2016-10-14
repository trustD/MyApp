package baoqi.com.myapp.presenter.impPresenter;

import baoqi.com.myapp.adapter.TuWenAdapter;
import baoqi.com.myapp.api.ApiManage;
import baoqi.com.myapp.bean.s.one.OneList;
import baoqi.com.myapp.presenter.impView.IOneFragment;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hasee on 2016/10/13.
 */

public class OnePresenterlmpl {
    IOneFragment mIOneFragment;
    Subscription mSubscription;
    public OnePresenterlmpl(IOneFragment IOneFragment) {
        mIOneFragment = IOneFragment;
    }

    public void loadWebData(String date){
        mSubscription = ApiManage.getInstence().getOneService().getOnesData(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<OneList>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(OneList list) {
                       mIOneFragment.updateData(list);

                    }
                });
    }


}
