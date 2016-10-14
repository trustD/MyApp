package baoqi.com.myapp.bean.s.news.newslist;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import baoqi.com.myapp.bean.s.news.News;

/**
 * Created by hasee on 2016/10/13.
 */

public class QingGan {
    @SerializedName("T1370583240249")
    ArrayList<News> mList;

    public QingGan(ArrayList<News> list) {
        mList = list;
    }

    public ArrayList<News> getList() {
        return mList;

    }
}
