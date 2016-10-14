package baoqi.com.myapp.presenter;

/**
 * Created by hasee on 2016/10/12.
 */

public interface INewsPresenter extends BasePresenter{
    void getNews(String type,int id);
    void getNewsDeatils(String id);
}
