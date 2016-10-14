package baoqi.com.myapp.bean.s.news.newslist;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import baoqi.com.myapp.bean.s.news.News;

/**
 * Created by hasee on 2016/10/13.
 */

public class ShouJi {
    @SerializedName("T1348649654285")
    ArrayList<News> mList;
    public ArrayList<News> getList() {
        return mList;
    }

    public ShouJi(ArrayList<News> list) {
        mList = list;
    }
}
