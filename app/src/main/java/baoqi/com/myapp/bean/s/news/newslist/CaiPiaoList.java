package baoqi.com.myapp.bean.s.news.newslist;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import baoqi.com.myapp.bean.s.news.News;

/**
 * Created by hasee on 2016/10/13.
 */

public class CaiPiaoList {
    public CaiPiaoList(ArrayList<News> list) {
        mList = list;
    }

    @SerializedName("T1356600029035")
    ArrayList<News> mList;
    public ArrayList<News> getList() {
        return mList;
    }
}
