package baoqi.com.myapp.presenter.impView;

import java.util.ArrayList;
import java.util.List;

import baoqi.com.myapp.bean.s.news.News;
import baoqi.com.myapp.bean.s.news.NewsDeatils;
import baoqi.com.myapp.bean.s.news.NewsList;

/**
 * Created by hasee on 2016/10/12.
 */

public interface INewsFragment extends IBaseFragment{
    void updateNews(NewsList list);





    void getNewsDeatils(NewsDeatils deatils);
}
