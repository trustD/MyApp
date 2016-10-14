package baoqi.com.myapp.presenter.impView;

import java.util.ArrayList;

import baoqi.com.myapp.bean.s.news.News;

/**
 * Created by hasee on 2016/10/14.
 */

public interface ICommonNewsFragment {
    //Fragment回调获得装有新闻item的arrayList
    void updateData(ArrayList<News> list);
}
