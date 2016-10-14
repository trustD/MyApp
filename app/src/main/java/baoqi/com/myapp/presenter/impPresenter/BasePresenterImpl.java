package baoqi.com.myapp.presenter.impPresenter;

import baoqi.com.myapp.presenter.BasePresenter;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by hasee on 2016/10/10.
 */

public class BasePresenterImpl implements BasePresenter {
    private CompositeSubscription mCompositeSubscription;

    protected void addSubscription(Subscription s){
        if (this.mCompositeSubscription ==null){
            this.mCompositeSubscription = new CompositeSubscription();
        }
        this.mCompositeSubscription.add(s);
    }

    @Override
    public void unsubscrible() {
        if (this.mCompositeSubscription != null){
            this.mCompositeSubscription.unsubscribe();
        }

    }
}
