package baoqi.com.myapp.bean.s.news.newslist;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import baoqi.com.myapp.bean.s.news.News;

/**
 * Created by hasee on 2016/10/13.
 */

public class KeJiList {
    @SerializedName("T1348648650048")
    ArrayList<News> mList;

    public KeJiList(ArrayList<News> list) {
        mList = list;
    }

    public ArrayList<News> getList() {
        return mList;
    }
}
